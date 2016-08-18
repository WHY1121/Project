/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.why.com</p> 
 * @author	why
 * @date	2016年8月9日
 * @version 1.0
 */
package yycg.base.service;

import java.util.List;

import yycg.base.pojo.po.Basicinfo;
import yycg.base.pojo.po.Dictinfo;

/**
 * @author fsdfsdsss
 *
 */
public interface SystemConfigService {
    
	
	List<Dictinfo> findDictinfoByType(String type) throws Exception;

	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Basicinfo findBasicinfoById(String id) throws Exception;

	/**
	 * @param typecode
	 * @param dictcode
	 * @return
	 * @throws Exception
	 */
	Dictinfo findDictinfoByDictcode(String typecode, String dictcode) throws Exception;
}
