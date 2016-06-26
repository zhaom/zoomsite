/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheetAttributes;
import com.thinkgem.jeesite.modules.worksheet.dao.ZlmtWorksheetAttributesDao;

/**
 * 工单管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtWorksheetAttributesService extends CrudService<ZlmtWorksheetAttributesDao, ZlmtWorksheetAttributes> {

	public ZlmtWorksheetAttributes get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtWorksheetAttributes> findList(ZlmtWorksheetAttributes zlmtWorksheetAttributes) {
		return super.findList(zlmtWorksheetAttributes);
	}
	
	public Page<ZlmtWorksheetAttributes> findPage(Page<ZlmtWorksheetAttributes> page, ZlmtWorksheetAttributes zlmtWorksheetAttributes) {
		return super.findPage(page, zlmtWorksheetAttributes);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtWorksheetAttributes zlmtWorksheetAttributes) {
		super.save(zlmtWorksheetAttributes);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtWorksheetAttributes zlmtWorksheetAttributes) {
		super.delete(zlmtWorksheetAttributes);
	}
	
}