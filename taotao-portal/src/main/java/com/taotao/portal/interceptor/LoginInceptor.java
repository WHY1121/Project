package com.taotao.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.taotao.common.util.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import com.taotao.portal.service.impl.UserServiceImpl;

public class LoginInceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//判断用户是否登录
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		
		TbUser user = userService.getUserByToken(token);
		if(null==user){
			//跳转到登录页面，把用户请求的url作为参数传递给登录页面。
			response.sendRedirect(userService.SSO_BASE_URL + userService.SSO_PAGE_LOGIN
					+ "?redirect=" + request.getRequestURL());
			//返回false
			return false;
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
