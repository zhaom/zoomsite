/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceUser;
import com.thinkgem.jeesite.modules.resource.dao.ZlmtResourceUserDao;

/**
 * 资源管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtResourceUserService extends CrudService<ZlmtResourceUserDao, ZlmtResourceUser> {

	public ZlmtResourceUser get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtResourceUser> findList(ZlmtResourceUser zlmtResourceUser) {
		return super.findList(zlmtResourceUser);
	}
	
	public Page<ZlmtResourceUser> findPage(Page<ZlmtResourceUser> page, ZlmtResourceUser zlmtResourceUser) {
		return super.findPage(page, zlmtResourceUser);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtResourceUser zlmtResourceUser) {
		super.save(zlmtResourceUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtResourceUser zlmtResourceUser) {
		super.delete(zlmtResourceUser);
	}
	
}