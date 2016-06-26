/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 资源管理Entity
 * @author johnKee
 * @version 2016-06-26
 */
public class ZlmtResource extends DataEntity<ZlmtResource> {
	
	private static final long serialVersionUID = 1L;
	private String resourceCode;		// 资源编码
	private String resourceName;		// 资源名称
	private String locIdc;		// 所在机房
	private String locCabinet;		// 所在机柜
	private String locCabinetPlace;		// 所在机柜位置
	private String resourceSn;		// 资源标准编号
	private String manufacturer;		// 制造商
	private String manufacturerLinkman;		// 制造商联系人
	private String manufacturerTel;		// 联系电话
	private String resourceType;		// 资源类型（主机、路由、交换机等） : 主机、路由器、交换机、ups、空调、门禁、监控摄像头等
	private String resourceModel;		// 型号
	private String resourceLevel;		// 运维等级 : 1-4级
	private String ownerService;		// 所属业务
	private Date purchaseDate;		// 采购时间
	private Date maintDate;		// 上架时间
	private String configDesc;		// 配置信息
	private String resourceStatus;		// 设备状态 : 闲置中、故障中、工作中、已作废
	private Office office;	// 归属部门
	private String ownerOffice;
	private String exAttributeDictType;		// 扩展属性字典类型
	private String remark;		// 备注
	
	public ZlmtResource() {
		super();
	}

	public ZlmtResource(String id){
		super(id);
	}

	@Length(min=1, max=64, message="资源编码长度必须介于 1 和 64 之间")
	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}
	
	@Length(min=1, max=128, message="资源名称长度必须介于 1 和 128 之间")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	@Length(min=0, max=128, message="所在机房长度必须介于 0 和 128 之间")
	public String getLocIdc() {
		return locIdc;
	}

	public void setLocIdc(String locIdc) {
		this.locIdc = locIdc;
	}
	
	@Length(min=0, max=128, message="所在机柜长度必须介于 0 和 128 之间")
	public String getLocCabinet() {
		return locCabinet;
	}

	public void setLocCabinet(String locCabinet) {
		this.locCabinet = locCabinet;
	}
	
	@Length(min=0, max=128, message="所在机柜位置长度必须介于 0 和 128 之间")
	public String getLocCabinetPlace() {
		return locCabinetPlace;
	}

	public void setLocCabinetPlace(String locCabinetPlace) {
		this.locCabinetPlace = locCabinetPlace;
	}
	
	@Length(min=0, max=128, message="资源标准编号长度必须介于 0 和 128 之间")
	public String getResourceSn() {
		return resourceSn;
	}

	public void setResourceSn(String resourceSn) {
		this.resourceSn = resourceSn;
	}
	
	@Length(min=0, max=256, message="制造商长度必须介于 0 和 256 之间")
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	@Length(min=0, max=56, message="制造商联系人长度必须介于 0 和 56 之间")
	public String getManufacturerLinkman() {
		return manufacturerLinkman;
	}

	public void setManufacturerLinkman(String manufacturerLinkman) {
		this.manufacturerLinkman = manufacturerLinkman;
	}
	
	@Length(min=0, max=64, message="联系电话长度必须介于 0 和 64 之间")
	public String getManufacturerTel() {
		return manufacturerTel;
	}

	public void setManufacturerTel(String manufacturerTel) {
		this.manufacturerTel = manufacturerTel;
	}
	
	@Length(min=0, max=64, message="资源类型（主机、路由、交换机等） : 主机、路由器、交换机、ups、空调、门禁、监控摄像头等长度必须介于 0 和 64 之间")
	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	@Length(min=0, max=64, message="型号长度必须介于 0 和 64 之间")
	public String getResourceModel() {
		return resourceModel;
	}

	public void setResourceModel(String resourceModel) {
		this.resourceModel = resourceModel;
	}
	
	@Length(min=0, max=64, message="运维等级 : 1-4级长度必须介于 0 和 64 之间")
	public String getResourceLevel() {
		return resourceLevel;
	}

	public void setResourceLevel(String resourceLevel) {
		this.resourceLevel = resourceLevel;
	}
	
	@Length(min=0, max=64, message="所属业务长度必须介于 0 和 64 之间")
	public String getOwnerService() {
		return ownerService;
	}

	public void setOwnerService(String ownerService) {
		this.ownerService = ownerService;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getMaintDate() {
		return maintDate;
	}

	public void setMaintDate(Date maintDate) {
		this.maintDate = maintDate;
	}
	
	@Length(min=0, max=2048, message="配置信息长度必须介于 0 和 2048 之间")
	public String getConfigDesc() {
		return configDesc;
	}

	public void setConfigDesc(String configDesc) {
		this.configDesc = configDesc;
	}
	
	@Length(min=0, max=64, message="设备状态 : 闲置中、故障中、工作中、已作废长度必须介于 0 和 64 之间")
	public String getResourceStatus() {
		return resourceStatus;
	}

	public void setResourceStatus(String resourceStatus) {
		this.resourceStatus = resourceStatus;
	}

	@JsonIgnore
	@NotNull(message="归属部门不能为空")
	@ExcelField(title="归属部门", align=2, sort=25)
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public String getOwnerOffice() {
		return ownerOffice;
	}

	public void setOwnerOffice(String ownerOffice) {
		this.ownerOffice = ownerOffice;
	}

	@Length(min=0, max=64, message="扩展属性字典类型长度必须介于 0 和 64 之间")
	public String getExAttributeDictType() {
		return exAttributeDictType;
	}

	public void setExAttributeDictType(String exAttributeDictType) {
		this.exAttributeDictType = exAttributeDictType;
	}
	
	@Length(min=0, max=512, message="备注长度必须介于 0 和 512 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}