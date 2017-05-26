package com.xjl.pt.form.domain;

import org.apache.commons.lang3.StringUtils;

import com.xjl.pt.core.domain.XJLDomain;
/**
 * 表字段定义
 * @author lilisheng
 *
 */
public class TableField extends XJLDomain {
	//字段id
	private String fieldId;
	//字段名称
	private String fieldName;
	//字段描述
	private String fieldDesc;
	//字段类型
	private String fieldType;
	//字段长度
	private Integer fieldLength;
	//字典id
	private String dictId;
	//表id
	private String tableId;
	//如果是外键的话对应的外键表id
	private String foreignTableId;
	//对应tableId的表名称，不是数据库中的字段
	private String tableId$name;
	//对应外键表的表名称，不是数据库中的字段
	private String foreignTableId$name;
	//对应字段类型名称，不是数据库中的字段
	private String fieldType$name;
	//对应字典dictId的名称，不是数据库字段
	private String dictId$name;
	//字符串
	public static final String FIELD_TYPE_STRING = "10";
	//数字
	public static final String FIELD_TYPE_NUMBER = "20";
	//日期
	public static final String FIELD_TYPE_DATE = "30";
	//字典
	public static final String FIELD_TYPE_DICT = "40";
	//外键
	public static final String FIELD_TYPE_FK = "50";
	//主键
	public static final String FIELD_TYPE_PK = "60";
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}
	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public Integer getFieldLength() {
		return fieldLength;
	}
	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getForeignTableId() {
		return foreignTableId;
	}
	public void setForeignTableId(String foreignTableId) {
		this.foreignTableId = foreignTableId;
	}
	public String getTableId$name() {
		return tableId$name;
	}
	public void setTableId$name(String tableId$name) {
		this.tableId$name = tableId$name;
	}
	public String getForeignTableId$name() {
		return foreignTableId$name;
	}
	public void setForeignTableId$name(String foreignTableId$name) {
		this.foreignTableId$name = foreignTableId$name;
	}
	public String getFieldType$name() {
		return fieldType$name;
	}
	public void setFieldType$name(String fieldType$name) {
		this.fieldType$name = fieldType$name;
	}
	public String getDictId$name() {
		return dictId$name;
	}
	public void setDictId$name(String dictId$name) {
		this.dictId$name = dictId$name;
	}
	
}
