/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResource;
import com.thinkgem.jeesite.modules.resource.service.ZlmtResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源管理Controller
 * @author johnKee
 * @version 2016-06-26
 */
@Controller
@RequestMapping(value = "${adminPath}/resource/zlmtResource")
public class ZlmtResourceController extends BaseController {

	@Autowired
	private ZlmtResourceService zlmtResourceService;
	
	@ModelAttribute
	public ZlmtResource get(@RequestParam(required=false) String id) {
		ZlmtResource entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = zlmtResourceService.get(id);
		}
		if (entity == null){
			entity = new ZlmtResource();
		}
		return entity;
	}
	
	@RequiresPermissions("resource:zlmtResource:view")
	@RequestMapping(value = {"list", ""})
	public String list(ZlmtResource zlmtResource, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ZlmtResource> page = zlmtResourceService.findPage(new Page<ZlmtResource>(request, response), zlmtResource); 
		model.addAttribute("page", page);
		return "modules/resource/zlmtResourceList";
	}

	@RequiresPermissions("resource:zlmtResource:view")
	@RequestMapping(value = "form")
	public String form(ZlmtResource zlmtResource, Model model) {
		model.addAttribute("zlmtResource", zlmtResource);
		return "modules/resource/zlmtResourceForm";
	}

	@RequiresPermissions("resource:zlmtResource:edit")
	@RequestMapping(value = "save")
	public String save(ZlmtResource zlmtResource, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, zlmtResource)){
			return form(zlmtResource, model);
		}
		zlmtResourceService.save(zlmtResource);
		addMessage(redirectAttributes, "保存资源基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResource/?repage";
	}
	
	@RequiresPermissions("resource:zlmtResource:edit")
	@RequestMapping(value = "delete")
	public String delete(ZlmtResource zlmtResource, RedirectAttributes redirectAttributes) {
		zlmtResourceService.delete(zlmtResource);
		addMessage(redirectAttributes, "删除资源基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/resource/zlmtResource/?repage";
	}

	@RequestMapping(value = "getResourceIdByCode")
	@ResponseBody
	public Map<String,String> getResourceIdByCode(String resourceCode){
		ZlmtResource resource = new ZlmtResource();
		Map<String, String> resultMap = new HashMap<String, String>();
		resource.setResourceCode(resourceCode);
		List<ZlmtResource> resourceList = zlmtResourceService.findList(resource);
		if(resourceList != null && resourceList.size() == 1){
			resultMap.put("code","S");
			resultMap.put("resourceId",resourceList.get(0).getId());
		}else if(resourceList == null || resourceList.size()<1) {
			resultMap.put("code","N");
		}else {
			resultMap.put("code","M");
		}
		return resultMap;
	}

}