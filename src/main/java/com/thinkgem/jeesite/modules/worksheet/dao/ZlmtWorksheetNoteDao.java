/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.worksheet.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.worksheet.entity.ZlmtWorksheetNote;

/**
 * 工单管理DAO接口
 * @author johnKee
 * @version 2016-06-26
 */
@MyBatisDao
public interface ZlmtWorksheetNoteDao extends CrudDao<ZlmtWorksheetNote> {
	
}