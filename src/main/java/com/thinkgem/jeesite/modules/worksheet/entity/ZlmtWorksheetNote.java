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
public class ZlmtWorksheetNote extends DataEntity<ZlmtWorksheetNote> {
	
	private static final long serialVersionUID = 1L;
	private String worksheetId;		// 工单id
	private String noteContent;		// 处理意见
	private String noteType;		// 意见类型 : 处理、审批意见、备注、附件、参考
	
	public ZlmtWorksheetNote() {
		super();
	}

	public ZlmtWorksheetNote(String id){
		super(id);
	}

	@Length(min=1, max=64, message="工单id长度必须介于 1 和 64 之间")
	public String getWorksheetId() {
		return worksheetId;
	}

	public void setWorksheetId(String worksheetId) {
		this.worksheetId = worksheetId;
	}
	
	@Length(min=0, max=512, message="处理意见长度必须介于 0 和 512 之间")
	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	
	@Length(min=0, max=64, message="意见类型 : 处理、审批意见、备注、附件、参考长度必须介于 0 和 64 之间")
	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	
}