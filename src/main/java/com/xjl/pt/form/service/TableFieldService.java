package com.xjl.pt.form.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjl.pt.core.domain.Dict;
import com.xjl.pt.core.domain.User;
import com.xjl.pt.core.domain.XJLDomain;
import com.xjl.pt.core.service.DictService;
import com.xjl.pt.core.service.XJLService;
import com.xjl.pt.form.domain.Table;
import com.xjl.pt.form.domain.TableField;
import com.xjl.pt.form.mapper.TableFieldMapper;
/**
 * 字段服务类
 * @author lilisheng
 *
 */
@Service
public class TableFieldService extends XJLService {
	private static Log log = LogFactory.getLog(TableFieldService.class);
	@Autowired
	private TableFieldMapper tableFieldMapper;
	@Autowired
	private TableService tableService;
	@Autowired
	private DictService dictService;
	@Autowired
	private TableProcessor tableProcessor;
	@Override
	public void _add(XJLDomain domain) {
		TableField field = (TableField)domain;
		if (TableField.FIELD_TYPE_STRING.equals(field.getFieldType())){
			if (field.getFieldLength() == null){
				throw new RuntimeException(field.getFieldName() + " 字符串类型 长度不能为空");
			}
		}
		this.tableFieldMapper.insert(domain);
		tableProcessor.addField(field.getTableId$name(), field.getFieldName(), field.getFieldType(), field.getFieldLength());
	}

	@Override
	public void _delete(XJLDomain domain) {
		this.tableFieldMapper.delete(domain);
		TableField field = (TableField)domain;
		this.tableProcessor.renameField(field.getTableId$name(), field.getFieldName(), "_D_"+ field.getFieldName());
	}
	@Override
	public void modify(XJLDomain domain, User user) {
		this.tableFieldMapper.update(domain);
		TableField field = (TableField)domain;
		this.tableProcessor.alterFieldType(field.getTableId$name(), field.getFieldName(), field.getFieldType(), field.getFieldLength());
	}
	@Override
	public void _resetNewId(XJLDomain domain) {
		((TableField)domain).setFieldId(UUID.randomUUID().toString());
	}
	public int queryCountByTableId(String tableId){
		return this.tableFieldMapper.selectCountByTableId(tableId);
	}
	public List<TableField> queryByTableId(String tableId, int page, int pageSize){
		PageHelper.startPage(page, pageSize);
		return this.tableFieldMapper.selectByTableId(tableId);
	}
	public void initFieldType$name(TableField field){
		if (StringUtils.isEmpty(field.getFieldType())){
			field.setFieldType$name("空");
			return;
		}
		switch (field.getFieldType()){
		case TableField.FIELD_TYPE_STRING:
			field.setFieldType$name("字符");
			break;
		case TableField.FIELD_TYPE_NUMBER:
			field.setFieldType$name("数字");
			break;
		case TableField.FIELD_TYPE_DATE:
			field.setFieldType$name("日期");
			break;
		case TableField.FIELD_TYPE_DICT:
			field.setFieldType$name("字典");
			break;
		case TableField.FIELD_TYPE_PK:
			field.setFieldType$name("主键");
			break;
		case TableField.FIELD_TYPE_FK:
			field.setFieldType$name("外键");
			break;
		default:
			field.setFieldType$name("未知"+field.getFieldType());
		}
	}
	public void initTableId$name(TableField field){
		if (StringUtils.isEmpty(field.getTableId())){
			return;
		}
		Table table = this.tableService.queryById(field.getTableId());
		if (table == null){
			return;
		}
		field.setTableId$name(table.getTableName());
	}
	public void initForeignTableId$name(TableField field){
		if (StringUtils.isEmpty(field.getForeignTableId())){
			return;
		}
		Table table = this.tableService.queryById(field.getForeignTableId());
		if (table == null){
			return;
		}
		field.setForeignTableId$name(table.getTableName());
	}
	public void initDictId$name(TableField field){
		if (StringUtils.isEmpty(field.getDictId())){
			return;
		}
		Dict dict = this.dictService.queryById(field.getDictId());
		if (dict == null){
			return;
		}
		field.setDictId$name(dict.getDictName());
	}
}
