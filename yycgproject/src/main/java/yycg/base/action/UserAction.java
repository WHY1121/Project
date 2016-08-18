/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.why.com</p> 
 * @author	why
 * @date	2016年8月3日
 * @version 1.0
 */
package yycg.base.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.PageQuery;
import yycg.base.pojo.vo.SysuserCustom;
import yycg.base.pojo.vo.SysuserQueryVo;
import yycg.base.process.context.Config;
import yycg.base.process.result.DataGridResultInfo;
import yycg.base.process.result.ExceptionResultInfo;
import yycg.base.process.result.ResultInfo;
import yycg.base.process.result.ResultUtil;
import yycg.base.process.result.SubmitResultInfo;
import yycg.base.service.SystemConfigService;
import yycg.base.service.UserService;

/**
 * @author fsdfsdsss
 *
 */
@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserService userService;
	@Autowired
	private SystemConfigService systemConfigService;

	// 用户查询页面
	@RequestMapping("/queryuser")
	public String queryUser(Model model) throws Exception {

		// 将页面所需要的数据取出，传到页面
		// 用户类型
		List<Dictinfo> groupList = systemConfigService.findDictinfoByType("s01");
		model.addAttribute("groupList", groupList);

		return "/base/user/queryuser";
	}

	// 用户页面结果集
	@RequestMapping("/queryuser_result")
	@ResponseBody
	public DataGridResultInfo queryUser_result(SysuserQueryVo sysuserQueryVo, int page, int rows) throws Exception {

		// 非空校验
		sysuserQueryVo = sysuserQueryVo != null ? sysuserQueryVo : new SysuserQueryVo();
		int total = userService.findSysuserCount(sysuserQueryVo);
		PageQuery pageQuery = new PageQuery();
		pageQuery.setPageParams(total, rows, page);
		sysuserQueryVo.setPageQuery(pageQuery);
		List<SysuserCustom> findSysuserList = userService.findSysuserList(sysuserQueryVo);
		DataGridResultInfo dataGridResultInfo = new DataGridResultInfo();
		dataGridResultInfo.setRows(findSysuserList);
		dataGridResultInfo.setTotal(total);
		return dataGridResultInfo;
	}

	// 添加用户界面
	@RequestMapping("/addsysuser")
	public String addsysUser() throws Exception {

		return "/base/user/addsysuser";
	}

	// 添加用户界面
	@RequestMapping("/addsysusersubmit")
	@ResponseBody
	public SubmitResultInfo addsysusersubmit(SysuserQueryVo sysuserQueryVo) throws Exception {
		// 提示用户信息
		// String message="操作成功！";
		// int type=0;
		// ResultInfo resultInfo=new ResultInfo();
		// resultInfo.setType(ResultInfo.TYPE_RESULT_SUCCESS);
		// resultInfo.setMessage("操作成功！");

		// try {
		// userService.insertSysuser(sysuserQueryVo.getSysuserCustom());
		// } catch (Exception e) {
		// e.printStackTrace();
		// if (e instanceof ExceptionResultInfo) {
		// resultInfo = ((ExceptionResultInfo) e).getResultInfo();
		// }else{
		// resultInfo=new ResultInfo();
		// resultInfo.setType(ResultInfo.TYPE_RESULT_FAIL);
		// resultInfo.setMessage("未知错误！");
		// }
		// }
		// 将执行结果返回页面

		// Map<String, Object> result_map = new HashMap<String, Object>();
		// result_map.put("type", type);
		// result_map.put("message", message);
		userService.insertSysuser(sysuserQueryVo.getSysuserCustom());
		return ResultUtil.createSubmitResult(ResultUtil.createFail(Config.MESSAGE, 906, null));

	}

	// 删除用户
	@RequestMapping("/deletesysuser")
	public @ResponseBody SubmitResultInfo deletesysuser(String id) throws Exception {
		// 调用service
		userService.deleteSysuser(id);
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 906, null));
	}

	// 修改用户提交
	@RequestMapping("edituser")
	public @ResponseBody SubmitResultInfo edituser(String id, SysuserQueryVo queryVo) throws Exception {
		userService.updateSysuser(id, queryVo.getSysuserCustom());
		return ResultUtil.createSubmitResult(ResultUtil.createSuccess(Config.MESSAGE, 906, null));
	}

	
}
