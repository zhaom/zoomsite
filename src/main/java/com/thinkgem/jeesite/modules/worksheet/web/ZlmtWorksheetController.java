/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceWorksheet;
import com.thinkgem.jeesite.modules.resource.service.ZlmtResourceService;
import com.thinkgem.jeesite.modules.resource.service.ZlmtResourceWorksheetService;
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheet;
import com.thinkgem.jeesite.modules.worksheet.service.ZlmtWorksheetService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 工单管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/worksheet/zlmtWorksheet")
public class ZlmtWorksheetController extends BaseController {

	@Autowired
	private ZlmtWorksheetService zlmtWorksheetService;

	@Autowired
	private ZlmtResourceWorksheetService zlmtResourceWorksheetService;

	@Autowired
	private ZlmtResourceService zlmtResourceService;
	
	@ModelAttribute
	public ZlmtWorksheet get(@RequestParam(required=false) String id) {
		ZlmtWorksheet entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtWorksheetService.get(id);
		}
		if (entity == null){
			entity = new ZlmtWorksheet();
		}
		return entity;
	}
	
	@RequiresPermissions("worksheet:zlmtWorksheet:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtWorksheet zlmtWorksheet, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtWorksheet> page = zlmtWorksheetService.findPage(new Page<ZlmtWorksheet>(request, response), zlmtWorksheet); 
		model.addAttribute("page", page);
		return "modules/worksheet/zlmtWorksheetList";
	}

	@RequestMapping(value = "form")
	public String form(ZlmtWorksheet zlmtWorksheet, Model model) {
		String view = "zlmtWorksheetForm";
		if(StringUtils.isNotBlank(zlmtWorksheet.getId())){
			String taskDefKey = zlmtWorksheet.getAct().getTaskDefKey();
			// 查看工单
			if(zlmtWorksheet.getAct().isFinishTask()){
				view = "zlmtWorksheetView";
			}
			// 审批环节
			else if (taskDefKey.contains("audit") || taskDefKey.contains("Audit")){
				view = "zlmtWorksheetAuditForm";
			}else if(taskDefKey.contains("do") || taskDefKey.contains("Do")){    //处理或者修改等环节
				view = "zlmtWorksheetDoForm";
			}
			ZlmtResourceWorksheet resourceWorksheet = new ZlmtResourceWorksheet();
			resourceWorksheet.setWorksheetId(zlmtWorksheet.getId());
			List<ZlmtResourceWorksheet> resourceWorksheetList = zlmtResourceWorksheetService.findList(resourceWorksheet);
			if(resourceWorksheetList != null && resourceWorksheetList.size()>0){
				model.addAttribute("resourceSheetList", resourceWorksheetList);
			}
		}else{
			String worksheetCode = "WS"+ DateUtils.getYear()+DateUtils.getMonth()+DateUtils.getDay()+"01";
			zlmtWorksheet.setWorksheetCode(worksheetCode);
		}
		model.addAttribute("zlmtWorksheet", zlmtWorksheet);
		return "modules/worksheet/"+view;
	}

	@RequestMapping(value = "save")
	public String save(ZlmtWorksheet zlmtWorksheet, Model model, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtWorksheet)){
			return form(zlmtWorksheet, model);
		}
		zlmtWorksheetService.save(zlmtWorksheet);
		String resourceCodes = httpServletRequest.getParameter("resources");
		if(StringUtils.isNotBlank(resourceCodes)){
			zlmtResourceWorksheetService.batchSave(zlmtWorksheet.getId(), resourceCodes);
		}
		addMessage(redirectAttributes, "提交审批成功");
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	/**
	 * 工单执行（完成任务）
	 * @param zlmtWorksheet
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "saveAudit")
	public String saveAudit(ZlmtWorksheet zlmtWorksheet, Model model) {
		if (StringUtils.isBlank(zlmtWorksheet.getAct().getFlag())
				|| StringUtils.isBlank(zlmtWorksheet.getAct().getComment())){
			addMessage(model, "请填写审核意见。");
			return form(zlmtWorksheet, model);
		}
		zlmtWorksheetService.auditSave(zlmtWorksheet);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	@RequestMapping(value = "delete")
	public String delete(ZlmtWorksheet zlmtWorksheet, RedirectAttributes redirectAttributes) {
		zlmtWorksheetService.delete(zlmtWorksheet);
		addMessage(redirectAttributes, "删除工单基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/worksheet/zlmtWorksheet/?repage";
	}

}