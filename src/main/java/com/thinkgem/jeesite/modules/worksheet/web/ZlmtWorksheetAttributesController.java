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
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheetAttributes;
import com.thinkgem.jeesite.modules.worksheet.service.ZlmtWorksheetAttributesService;

/**
 * 工单管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/worksheet/zlmtWorksheetAttributes")
public class ZlmtWorksheetAttributesController extends BaseController {

	@Autowired
	private ZlmtWorksheetAttributesService zlmtWorksheetAttributesService;
	
	@ModelAttribute
	public ZlmtWorksheetAttributes get(@RequestParam(required=false) String id) {
		ZlmtWorksheetAttributes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtWorksheetAttributesService.get(id);
		}
		if (entity == null){
			entity = new ZlmtWorksheetAttributes();
		}
		return entity;
	}
	
	@RequiresPermissions("worksheet:zlmtWorksheetAttributes:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtWorksheetAttributes zlmtWorksheetAttributes, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtWorksheetAttributes> page = zlmtWorksheetAttributesService.findPage(new Page<ZlmtWorksheetAttributes>(request, response), zlmtWorksheetAttributes); 
		model.addAttribute("page", page);
		return "modules/worksheet/zlmtWorksheetAttributesList";
	}

	@RequiresPermissions("worksheet:zlmtWorksheetAttributes:view")
	@RequestMapping(value = "form")
	public String form(ZlmtWorksheetAttributes zlmtWorksheetAttributes, Model model) {
		model.addAttribute("zlmtWorksheetAttributes", zlmtWorksheetAttributes);
		return "modules/worksheet/zlmtWorksheetAttributesForm";
	}

	@RequiresPermissions("worksheet:zlmtWorksheetAttributes:edit")
	@RequestMapping(value = "save")
	public String save(ZlmtWorksheetAttributes zlmtWorksheetAttributes, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtWorksheetAttributes)){
			return form(zlmtWorksheetAttributes, model);
		}
		zlmtWorksheetAttributesService.save(zlmtWorksheetAttributes);
		addMessage(redirectAttributes, "保存工单扩展属性信息成功");
		return "redirect:"+Global.getAdminPath()+"/worksheet/zlmtWorksheetAttributes/?repage";
	}
	
	@RequiresPermissions("worksheet:zlmtWorksheetAttributes:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlmtWorksheetAttributes zlmtWorksheetAttributes, RedirectAttributes redirectAttributes) {
		zlmtWorksheetAttributesService.delete(zlmtWorksheetAttributes);
		addMessage(redirectAttributes, "删除工单扩展属性信息成功");
		return "redirect:"+Global.getAdminPath()+"/worksheet/zlmtWorksheetAttributes/?repage";
	}

}