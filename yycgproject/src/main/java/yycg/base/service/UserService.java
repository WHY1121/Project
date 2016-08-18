package yycg.base.service;

import java.util.List;

import yycg.base.pojo.po.Sysuser;
import yycg.base.pojo.vo.ActiveUser;
import yycg.base.pojo.vo.SysuserCustom;
import yycg.base.pojo.vo.SysuserQueryVo;

public interface UserService {
	
	//查询所有用户
    List<SysuserCustom> findSysuserList(SysuserQueryVo sysuserQueryVo) throws Exception;
    
    int findSysuserCount(SysuserQueryVo sysuserQueryVo) throws Exception;
    
    void insertSysuser(SysuserCustom sysuserCustom) throws Exception;
    
    void deleteSysuser(String id) throws Exception;
    
    SysuserCustom findSysuserById(String id) throws Exception;
    
    void updateSysuser(String id, SysuserCustom sysuserCustom) throws Exception;

	/**
	 * @param userid
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	ActiveUser checkUserInfo(String userid, String pwd) throws Exception; 
}
