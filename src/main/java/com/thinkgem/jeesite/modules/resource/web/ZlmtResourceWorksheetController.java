/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.web;

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
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceWorksheet;
import com.thinkgem.jeesite.modules.resource.service.ZlmtResourceWorksheetService;

/**
 * 资源管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/zlmtResourceWorksheet")
public class ZlmtResourceWorksheetController extends BaseController {

	@Autowired
	private ZlmtResourceWorksheetService zlmtResourceWorksheetService;
	
	@ModelAttribute
	public ZlmtResourceWorksheet get(@RequestParam(required=false) String id) {
		ZlmtResourceWorksheet entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtResourceWorksheetService.get(id);
		}
		if (entity == null){
			entity = new ZlmtResourceWorksheet();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:zlmtResourceWorksheet:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtResourceWorksheet zlmtResourceWorksheet, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtResourceWorksheet> page = zlmtResourceWorksheetService.findPage(new Page<ZlmtResourceWorksheet>(request, response), zlmtResourceWorksheet); 
		model.addAttribute("page", page);
		return "modules/resource/zlmtResourceWorksheetList";
	}

	@RequiresPermissions("resource:zlmtResourceWorksheet:view")
	@RequestMapping(value = "form")
	public String form(ZlmtResourceWorksheet zlmtResourceWorksheet, Model model) {
		model.addAttribute("zlmtResourceWorksheet", zlmtResourceWorksheet);
		return "modules/resource/zlmtResourceWorksheetForm";
	}

	@RequiresPermissions("resource:zlmtResourceWorksheet:edit")
	@RequestMapping(value = "save")
	public String save(ZlmtResourceWorksheet zlmtResourceWorksheet, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtResourceWorksheet)){
			return form(zlmtResourceWorksheet, model);
		}
		zlmtResourceWorksheetService.save(zlmtResourceWorksheet);
		addMessage(redirectAttributes, "保存资源派工单信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResourceWorksheet/?repage";
	}
	
	@RequiresPermissions("resource:zlmtResourceWorksheet:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlmtResourceWorksheet zlmtResourceWorksheet, RedirectAttributes redirectAttributes) {
		zlmtResourceWorksheetService.delete(zlmtResourceWorksheet);
		addMessage(redirectAttributes, "删除资源派工单信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResourceWorksheet/?repage";
	}

}