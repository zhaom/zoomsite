/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheetNote;
import com.thinkgem.jeesite.modules.worksheet.dao.ZlmtWorksheetNoteDao;

/**
 * 工单管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtWorksheetNoteService extends CrudService<ZlmtWorksheetNoteDao, ZlmtWorksheetNote> {

	public ZlmtWorksheetNote get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtWorksheetNote> findList(ZlmtWorksheetNote zlmtWorksheetNote) {
		return super.findList(zlmtWorksheetNote);
	}
	
	public Page<ZlmtWorksheetNote> findPage(Page<ZlmtWorksheetNote> page, ZlmtWorksheetNote zlmtWorksheetNote) {
		return super.findPage(page, zlmtWorksheetNote);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtWorksheetNote zlmtWorksheetNote) {
		super.save(zlmtWorksheetNote);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtWorksheetNote zlmtWorksheetNote) {
		super.delete(zlmtWorksheetNote);
	}
	
}