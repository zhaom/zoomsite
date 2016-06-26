/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.ActEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 工单管理Entity
 * @author johnKee
 * @version 2016-06-26
 */
public class ZlmtWorksheet extends ActEntity<ZlmtWorksheet> {
	
	private static final long serialVersionUID = 1L;
	private String worksheetCode;		// 工单编码
	private String worksheetTitle;		// 标题
	private String worksheetReason;		// 发起工单原因
	private String worksheetReq;		// 工单要求处理结果
	private Date reqBeginTime;		// 要求开始处理时间
	private Date reqThruTime;		// 要求处理结束时间
	private Date realBeginTime;		// 实际开始时间
	private String realThruTime;		// 实际处理结束时间
	private String worksheetStatus;		// 工单状态 : 创建、审批、处理、完成、作废
	private String exAttributeDictType;		// 扩展属性字典类型
	
	public ZlmtWorksheet() {
		super();
	}

	public ZlmtWorksheet(String id){
		super(id);
	}

	@Length(min=0, max=64, message="工单编码长度必须介于 0 和 64 之间")
	public String getWorksheetCode() {
		return worksheetCode;
	}

	public void setWorksheetCode(String worksheetCode) {
		this.worksheetCode = worksheetCode;
	}
	
	@Length(min=0, max=256, message="标题长度必须介于 0 和 256 之间")
	public String getWorksheetTitle() {
		return worksheetTitle;
	}

	public void setWorksheetTitle(String worksheetTitle) {
		this.worksheetTitle = worksheetTitle;
	}
	
	@Length(min=0, max=256, message="发起工单原因长度必须介于 0 和 256 之间")
	public String getWorksheetReason() {
		return worksheetReason;
	}

	public void setWorksheetReason(String worksheetReason) {
		this.worksheetReason = worksheetReason;
	}
	
	@Length(min=0, max=256, message="工单要求处理结果长度必须介于 0 和 256 之间")
	public String getWorksheetReq() {
		return worksheetReq;
	}

	public void setWorksheetReq(String worksheetReq) {
		this.worksheetReq = worksheetReq;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReqBeginTime() {
		return reqBeginTime;
	}

	public void setReqBeginTime(Date reqBeginTime) {
		this.reqBeginTime = reqBeginTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReqThruTime() {
		return reqThruTime;
	}

	public void setReqThruTime(Date reqThruTime) {
		this.reqThruTime = reqThruTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRealBeginTime() {
		return realBeginTime;
	}

	public void setRealBeginTime(Date realBeginTime) {
		this.realBeginTime = realBeginTime;
	}
	
	@Length(min=0, max=1, message="实际处理结束时间长度必须介于 0 和 1 之间")
	public String getRealThruTime() {
		return realThruTime;
	}

	public void setRealThruTime(String realThruTime) {
		this.realThruTime = realThruTime;
	}
	
	@Length(min=0, max=64, message="工单状态 : 创建、审批、处理、完成、作废长度必须介于 0 和 64 之间")
	public String getWorksheetStatus() {
		return worksheetStatus;
	}

	public void setWorksheetStatus(String worksheetStatus) {
		this.worksheetStatus = worksheetStatus;
	}
	
	@Length(min=0, max=64, message="扩展属性字典类型长度必须介于 0 和 64 之间")
	public String getExAttributeDictType() {
		return exAttributeDictType;
	}

	public void setExAttributeDictType(String exAttributeDictType) {
		this.exAttributeDictType = exAttributeDictType;
	}
	
}