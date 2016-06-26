/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 工单管理Entity
 * @author johnKee
 * @version 2016-06-26
 */
public class ZlmtWorksheetAttributes extends DataEntity<ZlmtWorksheetAttributes> {
	
	private static final long serialVersionUID = 1L;
	private String worksheetId;		// 工单id
	private String attributeCode;		// 属性编码
	private String attributeName;		// 属性名称
	private String attributeValue;		// 属性值
	
	public ZlmtWorksheetAttributes() {
		super();
	}

	public ZlmtWorksheetAttributes(String id){
		super(id);
	}

	@Length(min=1, max=64, message="工单id长度必须介于 1 和 64 之间")
	public String getWorksheetId() {
		return worksheetId;
	}

	public void setWorksheetId(String worksheetId) {
		this.worksheetId = worksheetId;
	}
	
	@Length(min=1, max=64, message="属性编码长度必须介于 1 和 64 之间")
	public String getAttributeCode() {
		return attributeCode;
	}

	public void setAttributeCode(String attributeCode) {
		this.attributeCode = attributeCode;
	}
	
	@Length(min=1, max=128, message="属性名称长度必须介于 1 和 128 之间")
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	@Length(min=0, max=256, message="属性值长度必须介于 0 和 256 之间")
	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
}