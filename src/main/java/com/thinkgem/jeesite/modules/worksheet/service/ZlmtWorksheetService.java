/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.service;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.worksheet.dao.ZlmtWorksheetDao;
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheet;
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheetNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 工单管理Service
 * @author johnKee
 * @version 2016-06-26
 */
@Service
@Transactional(readOnly = true)
public class ZlmtWorksheetService extends CrudService<ZlmtWorksheetDao, ZlmtWorksheet> {

	@Autowired
	private ActTaskService actTaskService;

	@Autowired
	private ZlmtWorksheetNoteService zlmtWorksheetNoteService;

	public ZlmtWorksheet get(String id) {
		return super.get(id);
	}
	
	public List<ZlmtWorksheet> findList(ZlmtWorksheet zlmtWorksheet) {
		return super.findList(zlmtWorksheet);
	}
	
	public Page<ZlmtWorksheet> findPage(Page<ZlmtWorksheet> page, ZlmtWorksheet zlmtWorksheet) {
		return super.findPage(page, zlmtWorksheet);
	}
	
	@Transactional(readOnly = false)
	public void save(ZlmtWorksheet zlmtWorksheet) {
		// 申请发起
		if (StringUtils.isBlank(zlmtWorksheet.getId())){
			zlmtWorksheet.preInsert();
			dao.insert(zlmtWorksheet);
			// 启动流程
			actTaskService.startProcess(ActUtils.PD_LITTLE_FAULT_AUDIT[0], ActUtils.PD_LITTLE_FAULT_AUDIT[1], zlmtWorksheet.getId(), zlmtWorksheet.getWorksheetTitle());
		}
		// 重新编辑申请
		else{
			zlmtWorksheet.preUpdate();
			dao.update(zlmtWorksheet);

			zlmtWorksheet.getAct().setComment(("yes".equals(zlmtWorksheet.getAct().getFlag())?"[重申] ":"[销毁] ")+zlmtWorksheet.getAct().getComment());

			// 完成流程任务
			Map<String, Object> vars = Maps.newHashMap();
			vars.put("pass", "yes".equals(zlmtWorksheet.getAct().getFlag())? "1" : "0");
			actTaskService.complete(zlmtWorksheet.getAct().getTaskId(), zlmtWorksheet.getAct().getProcInsId(), zlmtWorksheet.getAct().getComment(), zlmtWorksheet.getWorksheetTitle(), vars);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(ZlmtWorksheet zlmtWorksheet) {
		super.delete(zlmtWorksheet);
	}

	/**
	 * 审核审批保存
	 * @param zlmtWorksheet
	 */
	@Transactional(readOnly = false)
	public void auditSave(ZlmtWorksheet zlmtWorksheet) {
		// 设置意见
		zlmtWorksheet.getAct().setComment(("yes".equals(zlmtWorksheet.getAct().getFlag())?"[同意] ":"[驳回] ")+zlmtWorksheet.getAct().getComment());

		zlmtWorksheet.preUpdate();

		// 对不同环节的业务逻辑进行操作
		String taskDefKey = zlmtWorksheet.getAct().getTaskDefKey();

		// 审核环节
		if (taskDefKey.contains("audit") || taskDefKey.contains("Audit")){
			ZlmtWorksheetNote note = new ZlmtWorksheetNote();
			note.setWorksheetId(zlmtWorksheet.getId());
			note.setNoteType("NOTE_AUDIT");
			note.setNoteContent(zlmtWorksheet.getAct().getComment());
			zlmtWorksheetNoteService.save(note);
		}
		else if ("doJob".equals(taskDefKey) || "applyEdit".equals(taskDefKey)){
			ZlmtWorksheetNote note = new ZlmtWorksheetNote();
			note.setWorksheetId(zlmtWorksheet.getId());
			note.setNoteType("NOTE_DO");
			note.setNoteContent(zlmtWorksheet.getAct().getComment());
			zlmtWorksheetNoteService.save(note);
		}
		// 未知环节，直接返回
		else{
			return;
		}
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(zlmtWorksheet.getAct().getFlag())? "1" : "0");
		actTaskService.complete(zlmtWorksheet.getAct().getTaskId(), zlmtWorksheet.getAct().getProcInsId(), zlmtWorksheet.getAct().getComment(), vars);
	}
}