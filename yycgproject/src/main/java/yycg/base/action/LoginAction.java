/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.why.com</p> 
 * @author	why
 * @date	2016年8月10日
 * @version 1.0
 */
package yycg.base.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.vo.ActiveUser;
import yycg.base.process.context.Config;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.service.UserService;

/**
 * @author fsdfsdsss
 *
 */
@Controller
public class LoginAction {
	@Autowired
	private UserService userService;

	// 用户登录提交
	@RequestMapping("/loginsubmit")
	public @ResponseBody SubmitResultInfo loginsubmit(HttpSession session, String userid, String pwd,
			String validateCode) throws Exception {
		String validateCode_session = (String) session.getAttribute("validateCode");
		if (validateCode_session != null && !validateCode_session.equals(validateCode)) {
			// 验证码输入错误
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 113, null));
		}
		// service，用户认证
		ActiveUser activeUser = userService.checkUserInfo(userid, pwd);

		// 将用户身份信息写入session
		session.setAttribute(Config.ACTIVEUSER_KEY, activeUser);

		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 107, new Object[] { "" }));
	}
	// 用户登录提交
	@RequestMapping("/loginout")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:login.action";
	}
}
