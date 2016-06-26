/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.resource.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.resource.entity.ZlmtResourceUser;

/**
 * 资源管理DAO接口
 * @author johnKee
 * @version 2016-06-26
 */
@MyBatisDao
public interface ZlmtResourceUserDao extends CrudDao<ZlmtResourceUser> {
	
}