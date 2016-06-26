/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheetNote;
import com.thinkgem.jeesite.modules.worksheet.service.ZlmtWorksheetNoteService;

/**
 * 工单管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/worksheet/zlmtWorksheetNote")
public class ZlmtWorksheetNoteController extends BaseController {

	@Autowired
	private ZlmtWorksheetNoteService zlmtWorksheetNoteService;
	
	@ModelAttribute
	public ZlmtWorksheetNote get(@RequestParam(required=false) String id) {
		ZlmtWorksheetNote entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtWorksheetNoteService.get(id);
		}
		if (entity == null){
			entity = new ZlmtWorksheetNote();
		}
		return entity;
	}
	
	@RequiresPermissions("worksheet:zlmtWorksheetNote:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtWorksheetNote zlmtWorksheetNote, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtWorksheetNote> page = zlmtWorksheetNoteService.findPage(new Page<ZlmtWorksheetNote>(request, response), zlmtWorksheetNote); 
		model.addAttribute("page", page);
		return "modules/worksheet/zlmtWorksheetNoteList";
	}

	@RequiresPermissions("worksheet:zlmtWorksheetNote:view")
	@RequestMapping(value = "form")
	public String form(ZlmtWorksheetNote zlmtWorksheetNote, Model model) {
		model.addAttribute("zlmtWorksheetNote", zlmtWorksheetNote);
		return "modules/worksheet/zlmtWorksheetNoteForm";
	}

	@RequiresPermissions("worksheet:zlmtWorksheetNote:edit")
	@RequestMapping(value = "save")
	public String save(ZlmtWorksheetNote zlmtWorksheetNote, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtWorksheetNote)){
			return form(zlmtWorksheetNote, model);
		}
		zlmtWorksheetNoteService.save(zlmtWorksheetNote);
		addMessage(redirectAttributes, "保存工单处理记录信息成功");
		return "redirect:"+Global.getAdminPath()+"/worksheet/zlmtWorksheetNote/?repage";
	}
	
	@RequiresPermissions("worksheet:zlmtWorksheetNote:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlmtWorksheetNote zlmtWorksheetNote, RedirectAttributes redirectAttributes) {
		zlmtWorksheetNoteService.delete(zlmtWorksheetNote);
		addMessage(redirectAttributes, "删除工单处理记录信息成功");
		return "redirect:"+Global.getAdminPath()+"/worksheet/zlmtWorksheetNote/?repage";
	}

}