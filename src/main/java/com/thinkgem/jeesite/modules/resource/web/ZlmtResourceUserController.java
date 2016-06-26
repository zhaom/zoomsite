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
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceUser;
import com.thinkgem.jeesite.modules.resource.service.ZlmtResourceUserService;

/**
 * 资源管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/zlmtResourceUser")
public class ZlmtResourceUserController extends BaseController {

	@Autowired
	private ZlmtResourceUserService zlmtResourceUserService;
	
	@ModelAttribute
	public ZlmtResourceUser get(@RequestParam(required=false) String id) {
		ZlmtResourceUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtResourceUserService.get(id);
		}
		if (entity == null){
			entity = new ZlmtResourceUser();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:zlmtResourceUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtResourceUser zlmtResourceUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtResourceUser> page = zlmtResourceUserService.findPage(new Page<ZlmtResourceUser>(request, response), zlmtResourceUser); 
		model.addAttribute("page", page);
		return "modules/resource/zlmtResourceUserList";
	}

	@RequiresPermissions("resource:zlmtResourceUser:view")
	@RequestMapping(value = "form")
	public String form(ZlmtResourceUser zlmtResourceUser, Model model) {
		model.addAttribute("zlmtResourceUser", zlmtResourceUser);
		return "modules/resource/zlmtResourceUserForm";
	}

	@RequiresPermissions("resource:zlmtResourceUser:edit")
	@RequestMapping(value = "save")
	public String save(ZlmtResourceUser zlmtResourceUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtResourceUser)){
			return form(zlmtResourceUser, model);
		}
		zlmtResourceUserService.save(zlmtResourceUser);
		addMessage(redirectAttributes, "保存资源人员关系信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResourceUser/?repage";
	}
	
	@RequiresPermissions("resource:zlmtResourceUser:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlmtResourceUser zlmtResourceUser, RedirectAttributes redirectAttributes) {
		zlmtResourceUserService.delete(zlmtResourceUser);
		addMessage(redirectAttributes, "删除资源人员关系信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResourceUser/?repage";
	}

}