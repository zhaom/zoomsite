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
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceAttributes;
import com.thinkgem.jeesite.modules.resource.service.ZlmtResourceAttributesService;

/**
 * 资源管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/zlmtResourceAttributes")
public class ZlmtResourceAttributesController extends BaseController {

	@Autowired
	private ZlmtResourceAttributesService zlmtResourceAttributesService;
	
	@ModelAttribute
	public ZlmtResourceAttributes get(@RequestParam(required=false) String id) {
		ZlmtResourceAttributes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtResourceAttributesService.get(id);
		}
		if (entity == null){
			entity = new ZlmtResourceAttributes();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:zlmtResourceAttributes:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtResourceAttributes zlmtResourceAttributes, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtResourceAttributes> page = zlmtResourceAttributesService.findPage(new Page<ZlmtResourceAttributes>(request, response), zlmtResourceAttributes); 
		model.addAttribute("page", page);
		return "modules/resource/zlmtResourceAttributesList";
	}

	@RequiresPermissions("resource:zlmtResourceAttributes:view")
	@RequestMapping(value = "form")
	public String form(ZlmtResourceAttributes zlmtResourceAttributes, Model model) {
		model.addAttribute("zlmtResourceAttributes", zlmtResourceAttributes);
		return "modules/resource/zlmtResourceAttributesForm";
	}

	@RequiresPermissions("resource:zlmtResourceAttributes:edit")
	@RequestMapping(value = "save")
	public String save(ZlmtResourceAttributes zlmtResourceAttributes, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtResourceAttributes)){
			return form(zlmtResourceAttributes, model);
		}
		zlmtResourceAttributesService.save(zlmtResourceAttributes);
		addMessage(redirectAttributes, "保存资源扩展属性信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResourceAttributes/?repage";
	}
	
	@RequiresPermissions("resource:zlmtResourceAttributes:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlmtResourceAttributes zlmtResourceAttributes, RedirectAttributes redirectAttributes) {
		zlmtResourceAttributesService.delete(zlmtResourceAttributes);
		addMessage(redirectAttributes, "删除资源扩展属性信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResourceAttributes/?repage";
	}

}