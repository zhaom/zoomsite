/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtMaintenanceRecord;
import com.thinkgem.jeesite.modules.resource.dao.ZlmtMaintenanceRecordDao;

/**
 * 资源管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtMaintenanceRecordService extends CrudService<ZlmtMaintenanceRecordDao, ZlmtMaintenanceRecord> {

	public ZlmtMaintenanceRecord get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtMaintenanceRecord> findList(ZlmtMaintenanceRecord zlmtMaintenanceRecord) {
		return super.findList(zlmtMaintenanceRecord);
	}
	
	public Page<ZlmtMaintenanceRecord> findPage(Page<ZlmtMaintenanceRecord> page, ZlmtMaintenanceRecord zlmtMaintenanceRecord) {
		return super.findPage(page, zlmtMaintenanceRecord);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtMaintenanceRecord zlmtMaintenanceRecord) {
		super.save(zlmtMaintenanceRecord);
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtMaintenanceRecord zlmtMaintenanceRecord) {
		super.delete(zlmtMaintenanceRecord);
	}
	
}