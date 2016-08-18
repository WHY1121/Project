/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.why.com</p> 
 * @author	why
 * @date	2016年8月10日
 * @version 1.0
 */
package yycg.base.action.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import yycg.base.pojo.vo.ActiveUser;
import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.util.ResourcesUtil;

/**
 * @author fsdfsdsss
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		ActiveUser activeUser = (ActiveUser) session.getAttribute(Config.ACTIVEUSER_KEY);
		if (activeUser != null) {
			return true;
		}
		List<String> openurls = ResourcesUtil.gekeyList(Config.ANONYMOUS_ACTIONS);
		// 用户访问的url
		String url = request.getRequestURI();
		for (String open_url : openurls) {
			if (url.indexOf(open_url) >= 0) {
				// 如果访问的是公开 地址则放行
				return true;
			}
		}
		ResultUtil.throwExcepion(ResultUtil.createWarning(Config.MESSAGE, 106, null));
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.
	 * servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, java.lang.Exception)
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
