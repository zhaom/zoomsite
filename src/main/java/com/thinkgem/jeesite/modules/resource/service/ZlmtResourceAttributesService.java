/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceAttributes;
import com.thinkgem.jeesite.modules.resource.dao.ZlmtResourceAttributesDao;

/**
 * 资源管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtResourceAttributesService extends CrudService<ZlmtResourceAttributesDao, ZlmtResourceAttributes> {

	public ZlmtResourceAttributes get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtResourceAttributes> findList(ZlmtResourceAttributes zlmtResourceAttributes) {
		return super.findList(zlmtResourceAttributes);
	}
	
	public Page<ZlmtResourceAttributes> findPage(Page<ZlmtResourceAttributes> page, ZlmtResourceAttributes zlmtResourceAttributes) {
		return super.findPage(page, zlmtResourceAttributes);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtResourceAttributes zlmtResourceAttributes) {
		super.save(zlmtResourceAttributes);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtResourceAttributes zlmtResourceAttributes) {
		super.delete(zlmtResourceAttributes);
	}
	
}