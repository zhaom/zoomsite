/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceWorksheet;
import com.thinkgem.jeesite.modules.resource.dao.ZlmtResourceWorksheetDao;

/**
 * 资源管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtResourceWorksheetService extends CrudService<ZlmtResourceWorksheetDao, ZlmtResourceWorksheet> {

	public ZlmtResourceWorksheet get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtResourceWorksheet> findList(ZlmtResourceWorksheet zlmtResourceWorksheet) {
		return super.findList(zlmtResourceWorksheet);
	}
	
	public Page<ZlmtResourceWorksheet> findPage(Page<ZlmtResourceWorksheet> page, ZlmtResourceWorksheet zlmtResourceWorksheet) {
		return super.findPage(page, zlmtResourceWorksheet);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtResourceWorksheet zlmtResourceWorksheet) {
		super.save(zlmtResourceWorksheet);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtResourceWorksheet zlmtResourceWorksheet) {
		super.delete(zlmtResourceWorksheet);
	}
	
}