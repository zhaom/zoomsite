/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.service;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.resource.dao.ZlmtResourceDao;
import com.thinkgem.jeesite.modules.resource.dao.ZlmtResourceWorksheetDao;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResource;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceWorksheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资源管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtResourceWorksheetService extends CrudService<ZlmtResourceWorksheetDao, ZlmtResourceWorksheet> {

	@Autowired
	private ZlmtResourceDao zlmtResourceDao;

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
	public void batchSave(String id, String resourceCodes) {
		if(StringUtils.isNotBlank(resourceCodes) && StringUtils.isNotBlank(id)){
			String[] codes = StringUtils.splitByWholeSeparator(resourceCodes, ",");
			for(String code:codes){
				ZlmtResource resource = new ZlmtResource();
				resource.setResourceCode(code);
				List<ZlmtResource> resourceList = zlmtResourceDao.findList(resource);
				if(resourceList != null && resourceList.size() > 0){
					ZlmtResourceWorksheet resourceWorksheet = new ZlmtResourceWorksheet();
					resourceWorksheet.setWorksheetId(id);
					resourceWorksheet.setResourceId(resourceList.get(0).getId());
					this.save(resourceWorksheet);
				}
			}
		}
	}
	public void delete(ZlmtResourceWorksheet zlmtResourceWorksheet) {
		super.delete(zlmtResourceWorksheet);
	}


}