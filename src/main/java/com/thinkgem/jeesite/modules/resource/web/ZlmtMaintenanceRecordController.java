/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtMaintenanceRecord;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResource;
import com.thinkgem.jeesite.modules.resource.service.ZlmtMaintenanceRecordService;
import com.thinkgem.jeesite.modules.resource.service.ZlmtResourceService;
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

/**
 * 资源管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/zlmtMaintenanceRecord")
public class ZlmtMaintenanceRecordController extends BaseController {

	@Autowired
	private ZlmtMaintenanceRecordService zlmtMaintenanceRecordService;

	@Autowired
	private ZlmtResourceService zlmtResourceService;
	
	@ModelAttribute
	public ZlmtMaintenanceRecord get(@RequestParam(required=false) String id) {
		ZlmtMaintenanceRecord entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtMaintenanceRecordService.get(id);
		}
		if (entity == null){
			entity = new ZlmtMaintenanceRecord();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:zlmtMaintenanceRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtMaintenanceRecord zlmtMaintenanceRecord, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtMaintenanceRecord> page = zlmtMaintenanceRecordService.findPage(new Page<ZlmtMaintenanceRecord>(request, response), zlmtMaintenanceRecord); 
		model.addAttribute("page", page);
		return "modules/resource/zlmtMaintenanceRecordList";
	}

	@RequiresPermissions("resource:zlmtMaintenanceRecord:view")
	@RequestMapping(value = "form")
	public String form(ZlmtMaintenanceRecord zlmtMaintenanceRecord, Model model) {
		model.addAttribute("zlmtMaintenanceRecord", zlmtMaintenanceRecord);
		if(zlmtMaintenanceRecord != null && zlmtMaintenanceRecord.getResource() != null) {
			model.addAttribute("resourceId", zlmtMaintenanceRecord.getResource().getId());
		}
		return "modules/resource/zlmtMaintenanceRecordForm";
	}

	@RequiresPermissions("resource:zlmtMaintenanceRecord:edit")
	@RequestMapping(value = "save")
	public String save(ZlmtMaintenanceRecord zlmtMaintenanceRecord, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtMaintenanceRecord)){
			return form(zlmtMaintenanceRecord, model);
		}
		if(zlmtMaintenanceRecord.getResource() == null) {
			ZlmtResource resource = zlmtResourceService.get(zlmtMaintenanceRecord.getResourceId());
			zlmtMaintenanceRecord.setResource(resource);
		}else {
			zlmtMaintenanceRecord.setResource(zlmtResourceService.get(zlmtMaintenanceRecord.getResource()));
		}
		zlmtMaintenanceRecordService.save(zlmtMaintenanceRecord);
		addMessage(redirectAttributes, "保存资源维护信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtMaintenanceRecord/?repage";
	}
	
	@RequiresPermissions("resource:zlmtMaintenanceRecord:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlmtMaintenanceRecord zlmtMaintenanceRecord, RedirectAttributes redirectAttributes) {
		zlmtMaintenanceRecordService.delete(zlmtMaintenanceRecord);
		addMessage(redirectAttributes, "删除资源维护信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtMaintenanceRecord/?repage";
	}

}