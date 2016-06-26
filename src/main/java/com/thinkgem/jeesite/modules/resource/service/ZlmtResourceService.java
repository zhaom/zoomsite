/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResource;
import com.thinkgem.jeesite.modules.resource.dao.ZlmtResourceDao;

/**
 * 资源管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtResourceService extends CrudService<ZlmtResourceDao, ZlmtResource> {

	public ZlmtResource get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtResource> findList(ZlmtResource zlmtResource) {
		return super.findList(zlmtResource);
	}
	
	public Page<ZlmtResource> findPage(Page<ZlmtResource> page, ZlmtResource zlmtResource) {
		return super.findPage(page, zlmtResource);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtResource zlmtResource) {
		super.save(zlmtResource);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtResource zlmtResource) {
		super.delete(zlmtResource);
	}
	
}