/**
 *
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Company: www.why.com</p> 
 * @author	why
 * @date	2016年8月9日
 * @version 1.0
 */
package yycg.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import yycg.base.dao.mapper.BasicinfoMapper;
import yycg.base.dao.mapper.DictinfoMapper;
import yycg.base.pojo.po.Basicinfo;
import yycg.base.pojo.po.Dictinfo;
import yycg.base.pojo.po.DictinfoExample;
import yycg.base.service.SystemConfigService;

/**
 * @author fsdfsdsss
 *
 */
public class SystemConfigServiceImpl implements SystemConfigService {

	/* (non-Javadoc)
	 * @see yycg.base.service.SystemConfigService#findDictinfoByType(java.lang.String)
	 */
	@Autowired
	private DictinfoMapper dictinfoMapper;
	@Autowired
	private BasicinfoMapper basicinfoMapper;
	@Override
	public List<Dictinfo> findDictinfoByType(String type) throws Exception {
		
		DictinfoExample example=new DictinfoExample();
		DictinfoExample.Criteria criteria=example.createCriteria();
		criteria.andTypecodeEqualTo(type);
		
		return dictinfoMapper.selectByExample(example);
	}

	// 根据typeocde和dictcode获取单个字典明细
	@Override
	public Dictinfo findDictinfoByDictcode(String typecode, String dictcode) throws Exception {
		DictinfoExample dictinfoExample = new DictinfoExample();
		DictinfoExample.Criteria criteria = dictinfoExample.createCriteria();
		criteria.andDictcodeEqualTo(dictcode);
		criteria.andTypecodeEqualTo(typecode);
		List<Dictinfo> list = dictinfoMapper.selectByExample(dictinfoExample);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;

	}
	/**
	 * 根据id获取系统配置信息
	 */
	@Override
	public Basicinfo findBasicinfoById(String id) throws Exception {
		return basicinfoMapper.selectByPrimaryKey(id);
	}

}
