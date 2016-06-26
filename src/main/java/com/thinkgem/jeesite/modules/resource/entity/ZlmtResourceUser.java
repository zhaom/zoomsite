/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资源管理Entity
 * @author johnKee
 * @version 2016-06-26
 */
public class ZlmtResourceUser extends DataEntity<ZlmtResourceUser> {
	
	private static final long serialVersionUID = 1L;
	private String resourceId;		// 资源id
	private User user;		// 用户id
	private String relationType;		// 关系类型 : 属主联系人、研发联系人、使用联系人、测试联系人、维护责任人、维护第二责任人
	private Date fromTime;		// 开始时间
	private Date thruTime;		// 结束时间
	private String remark;		// 备注
	
	public ZlmtResourceUser() {
		super();
	}

	public ZlmtResourceUser(String id){
		super(id);
	}

	@Length(min=1, max=64, message="资源id长度必须介于 1 和 64 之间")
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
	
	@NotNull(message="用户id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=64, message="关系类型 : 属主联系人、研发联系人、使用联系人、测试联系人、维护责任人、维护第二责任人长度必须介于 1 和 64 之间")
	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="开始时间不能为空")
	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getThruTime() {
		return thruTime;
	}

	public void setThruTime(Date thruTime) {
		this.thruTime = thruTime;
	}
	
	@Length(min=0, max=256, message="备注长度必须介于 0 和 256 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}