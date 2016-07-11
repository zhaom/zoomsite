SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS zlmt_maintenance_record;
DROP TABLE IF EXISTS zlmt_resource;
DROP TABLE IF EXISTS zlmt_resource_attributes;
DROP TABLE IF EXISTS zlmt_resource_user;
DROP TABLE IF EXISTS zlmt_resource_worksheet;
DROP TABLE IF EXISTS zlmt_worksheet;
DROP TABLE IF EXISTS zlmt_worksheet_attributes;
DROP TABLE IF EXISTS zlmt_worksheet_note;




/* Create Tables */

-- 资源维护记录
CREATE TABLE zlmt_maintenance_record
(
	id varchar(64) NOT NULL COMMENT '主键id',
	resource_id varchar(64) NOT NULL COMMENT '资源id',
	user_id varchar(64) COMMENT '用户id',
	user_name varchar(64) COMMENT '姓名',
	user_tel varchar(64) COMMENT '用户电话',
	maintenance_reason varchar(256) COMMENT '运维原因',
	maintenance_operation varchar(256) COMMENT '运维操作记录',
	maintenance_result varchar(64) COMMENT '运维结果',
	from_time datetime COMMENT '开始时间',
	thru_time datetime COMMENT '结束时间',
	remark varchar(256) COMMENT '备注',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '资源维护记录';


-- 资源主表
CREATE TABLE zlmt_resource
(
	id varchar(64) NOT NULL COMMENT '主键id',
	resource_code varchar(64) NOT NULL COMMENT '资源编码',
	resource_name varchar(128) NOT NULL COMMENT '资源名称',
	loc_idc varchar(128) COMMENT '所在机房',
	loc_cabinet varchar(128) COMMENT '所在机柜',
	loc_cabinet_place varchar(128) COMMENT '所在机柜位置',
	resource_sn varchar(128) COMMENT '资源标准编号',
	manufacturer varchar(256) COMMENT '制造商',
	manufacturer_linkman varchar(56) COMMENT '制造商联系人',
	manufacturer_tel varchar(64) COMMENT '联系电话',
	-- 主机、路由器、交换机、ups、空调、门禁、监控摄像头等
	resource_type varchar(64) COMMENT '资源类型（主机、路由、交换机等） : 主机、路由器、交换机、ups、空调、门禁、监控摄像头等',
	resource_model varchar(64) COMMENT '型号',
	-- 1-4级
	resource_level varchar(64) COMMENT '运维等级 : 1-4级',
	owner_service varchar(64) COMMENT '所属业务',
	purchase_date date COMMENT '采购时间',
	maint_date date COMMENT '上架时间',
	config_desc varchar(2048) COMMENT '配置信息',
	-- 闲置中、故障中、工作中、已作废
	resource_status varchar(64) COMMENT '设备状态 : 闲置中、故障中、工作中、已作废',
	owner_office_id varchar(64) COMMENT '所属单位编号',
	owner_office varchar(128) COMMENT '所属单位',
	ex_attribute_dict_type varchar(64) COMMENT '扩展属性字典类型',
	remark varchar(512) COMMENT '备注',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '资源主表';


-- 资源扩展属性表
CREATE TABLE zlmt_resource_attributes
(
	id varchar(64) NOT NULL COMMENT '主键id',
	resource_id varchar(64) NOT NULL COMMENT '资源id',
	attribute_code varchar(64) NOT NULL COMMENT '属性编码',
	attribute_name varchar(128) NOT NULL COMMENT '属性名称',
	attribute_value varchar(256) COMMENT '属性值',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '资源扩展属性表';


-- 资源用户关系表
CREATE TABLE zlmt_resource_user
(
	id varchar(64) NOT NULL COMMENT '主键id',
	resource_id varchar(64) NOT NULL COMMENT '资源id',
	user_id varchar(64) NOT NULL COMMENT '用户id',
	-- 属主联系人、研发联系人、使用联系人、测试联系人、维护责任人、维护第二责任人
	relation_type varchar(64) NOT NULL COMMENT '关系类型 : 属主联系人、研发联系人、使用联系人、测试联系人、维护责任人、维护第二责任人',
	from_time datetime NOT NULL COMMENT '开始时间',
	thru_time datetime COMMENT '结束时间',
	remark varchar(256) COMMENT '备注',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '资源用户关系表';


-- 资源派工单表
CREATE TABLE zlmt_resource_worksheet
(
	id varchar(64) NOT NULL COMMENT '主键id',
	resource_id varchar(64) NOT NULL COMMENT '资源id',
	worksheet_id varchar(64) NOT NULL COMMENT '工单id',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '资源派工单表';


-- 工单
CREATE TABLE zlmt_worksheet
(
	id varchar(64) NOT NULL COMMENT '主键id',
	worksheet_code varchar(64) COMMENT '工单编码',
	worksheet_title varchar(256) COMMENT '标题',
	worksheet_reason varchar(256) COMMENT '发起工单原因',
	worksheet_req varchar(256) COMMENT '工单要求处理结果',
	req_begin_time datetime COMMENT '要求开始处理时间',
	req_thru_time datetime COMMENT '要求处理结束时间',
	real_begin_time datetime COMMENT '实际开始时间',
	real_thru_time char COMMENT '实际处理结束时间',
	-- 创建、审批、处理、完成、作废
	worksheet_status varchar(64) COMMENT '工单状态 : 创建、审批、处理、完成、作废',
	ex_attribute_dict_type varchar(64) COMMENT '扩展属性字典类型',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '工单';


-- 工单扩展属性表
CREATE TABLE zlmt_worksheet_attributes
(
	id varchar(64) NOT NULL COMMENT '主键id',
	worksheet_id varchar(64) NOT NULL COMMENT '工单id',
	attribute_code varchar(64) NOT NULL COMMENT '属性编码',
	attribute_name varchar(128) NOT NULL COMMENT '属性名称',
	attribute_value varchar(256) COMMENT '属性值',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '工单扩展属性表';


-- 工单处理信息
CREATE TABLE zlmt_worksheet_note
(
	id varchar(64) NOT NULL COMMENT '主键id',
	worksheet_id varchar(64) NOT NULL COMMENT '工单id',
	note_content varchar(512) COMMENT '处理意见',
	-- 处理、审批意见、备注、附件、参考
	note_type varchar(64) COMMENT '意见类型 : 处理、审批意见、备注、附件、参考',
	-- 创建者
	create_by varchar(64) NOT NULL COMMENT '创建者 : 创建者',
	-- 创建时间
	create_date datetime NOT NULL COMMENT '创建时间 : 创建时间',
	-- 更新者
	update_by varchar(64) NOT NULL COMMENT '更新者 : 更新者',
	-- 更新时间
	update_date datetime NOT NULL COMMENT '更新时间 : 更新时间',
	-- 备注信息
	remarks varchar(255) COMMENT '备注信息 : 备注信息',
	-- 删除标记（0：正常；1：删除）
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记 : 删除标记（0：正常；1：删除）',
	PRIMARY KEY (id)
) COMMENT = '工单处理信息';



