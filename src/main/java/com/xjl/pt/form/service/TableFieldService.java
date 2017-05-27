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
		this.resetField(field);
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
		
		TableField field = (TableField)domain;
		if (TableField.FIELD_TYPE_STRING.equals(field.getFieldType())){
			if (field.getFieldLength() == null){
				throw new RuntimeException(field.getFieldName() + " 字符串类型 长度不能为空");
			}
		}
		this.resetField(field);
		TableField dbField = this.queryById(field.getFieldId());
		this.tableFieldMapper.update(domain);
		//如果新的名称和数据库中原有名称不一致，说明修改了字段名称，需要先修改数据库物理表中的字段名称
		if (!dbField.getFieldName().equals(field.getFieldName())){
			this.tableProcessor.renameField(field.getTableId$name(), dbField.getFieldName(), field.getFieldName());
		}
		if (dbField.getFieldType().equals(field.getFieldType())){
			//如果字段类型一样，看是不是字符串，如果是字符串，比较一下长度是不是一样
			if (TableField.FIELD_TYPE_STRING.equals(field.getFieldType()) && dbField.getFieldLength().intValue() != field.getFieldLength().intValue()){
				//同样是字符串类型，但是长度发生了变化，需要修改物理表中的类型
				this.tableProcessor.alterFieldType(field.getTableId$name(), field.getFieldName(), field.getFieldType(), field.getFieldLength());
			}
		} else {
			//字段类型不一致，需要修改物理表
			this.tableProcessor.alterFieldType(field.getTableId$name(), field.getFieldName(), field.getFieldType(), field.getFieldLength());
		}
	}
	@Override
	public void _resetNewId(XJLDomain domain) {
		((TableField)domain).setFieldId(UUID.randomUUID().toString());
	}
	public TableField queryById(String fieldId){
		return this.tableFieldMapper.selectById(fieldId);
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
	/**
	 * 重置里面的一些值，把不相关的值去掉和根据类型设置长度
	 * @param field
	 */
	public void resetField(TableField field){
		switch (field.getFieldType()) {
		case TableField.FIELD_TYPE_STRING:
			field.setDictId(null);
			field.setForeignTableId(null);
			break;
		case TableField.FIELD_TYPE_NUMBER:
			field.setDictId(null);
			field.setForeignTableId(null);
			field.setFieldLength(null);
			break;
		case TableField.FIELD_TYPE_DATE:
			field.setDictId(null);
			field.setForeignTableId(null);
			field.setFieldLength(null);
			break;
		case TableField.FIELD_TYPE_DICT:
			field.setForeignTableId(null);
			field.setFieldLength(36);
			break;
		case TableField.FIELD_TYPE_FK:
			field.setDictId(null);
			field.setFieldLength(36);
			break;
		case TableField.FIELD_TYPE_PK:
			field.setDictId(null);
			field.setForeignTableId(null);
			field.setFieldLength(36);
			break;
		default:
			throw new RuntimeException(field.getFieldName() + "有一个未知字段类型:" + field.getFieldType());
		}
	}
}
