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
public class ZlmtResourceAttributes extends DataEntity<ZlmtResourceAttributes> {
	
	private static final long serialVersionUID = 1L;
	private String resourceId;		// 资源id
	private String attributeCode;		// 属性编码
	private String attributeName;		// 属性名称
	private String attributeValue;		// 属性值
	
	public ZlmtResourceAttributes() {
		super();
	}

	public ZlmtResourceAttributes(String id){
		super(id);
	}

	@Length(min=1, max=64, message="资源id长度必须介于 1 和 64 之间")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
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