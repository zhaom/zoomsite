/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 资源管理Entity
 * @author johnKee
 * @version 2016-06-26
 */
public class ZlmtMaintenanceRecord extends DataEntity<ZlmtMaintenanceRecord> {
	
	private static final long serialVersionUID = 1L;
	private ZlmtResource resource; //资源
	private String resourceId;		// 资源id
	private String resourceName;		// 资源名称
	private User user;		// 用户id
	private String userName;		// 姓名
	private String userTel;		// 用户电话
	private String maintenanceReason;		// 运维原因
	private String maintenanceOperation;		// 运维操作记录
	private String maintenanceResult;		// 运维结果
	private Date fromTime;		// 开始时间
	private Date thruTime;		// 结束时间
	private String remark;		// 备注

	private String resourceCode;   //资源编码

	private Date beginDate;		// 开始日期
	private Date endDate;		// 结束日期
	
	public ZlmtMaintenanceRecord() {
		super();
	}

	public ZlmtMaintenanceRecord(String id){
		super(id);
	}



	@JsonIgnore
	@NotNull(message="资源不能为空")
	@ExcelField(title="资源", align=2, sort=25)
	public ZlmtResource getResource() {
		return resource;
	}

	public void setResource(ZlmtResource resource) {
		this.resource = resource;
	}

	@Length(min=1, max=64, message="资源id长度必须介于 1 和 64 之间")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	@Length(min=1, max=128, message="资源名称长度必须介于 1 和 128 之间")
	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=0, max=64, message="姓名长度必须介于 0 和 64 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=64, message="用户电话长度必须介于 0 和 64 之间")
	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	
	@Length(min=0, max=256, message="运维原因长度必须介于 0 和 256 之间")
	public String getMaintenanceReason() {
		return maintenanceReason;
	}

	public void setMaintenanceReason(String maintenanceReason) {
		this.maintenanceReason = maintenanceReason;
	}
	
	@Length(min=0, max=256, message="运维操作记录长度必须介于 0 和 256 之间")
	public String getMaintenanceOperation() {
		return maintenanceOperation;
	}

	public void setMaintenanceOperation(String maintenanceOperation) {
		this.maintenanceOperation = maintenanceOperation;
	}
	
	@Length(min=0, max=64, message="运维结果长度必须介于 0 和 64 之间")
	public String getMaintenanceResult() {
		return maintenanceResult;
	}

	public void setMaintenanceResult(String maintenanceResult) {
		this.maintenanceResult = maintenanceResult;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getThruTime() {
		return thruTime;
	}

	public void setThruTime(Date thruTime) {
		this.thruTime = thruTime;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}