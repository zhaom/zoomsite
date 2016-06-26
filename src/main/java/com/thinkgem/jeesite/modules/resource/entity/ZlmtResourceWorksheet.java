/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资源管理Entity
 * @author johnKee
 * @version 2016-06-26
 */
public class ZlmtResourceWorksheet extends DataEntity<ZlmtResourceWorksheet> {
	
	private static final long serialVersionUID = 1L;
	private String resourceId;		// 资源id
	private String worksheetId;		// 工单id
	
	public ZlmtResourceWorksheet() {
		super();
	}

	public ZlmtResourceWorksheet(String id){
		super(id);
	}

	@Length(min=1, max=64, message="资源id长度必须介于 1 和 64 之间")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	@Length(min=1, max=64, message="工单id长度必须介于 1 和 64 之间")
	public String getWorksheetId() {
		return worksheetId;
	}

	public void setWorksheetId(String worksheetId) {
		this.worksheetId = worksheetId;
	}
	
}