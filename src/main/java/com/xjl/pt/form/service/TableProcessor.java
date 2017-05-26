package com.xjl.pt.form.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.xjl.pt.form.domain.TableField;

/**
 * 表维护管理，如创建表/删除表/增加字段/修改字段/删除字段等等
 * 
 * @author lilisheng
 *
 */
@Component
public class TableProcessor {
	private static Log log = LogFactory.getLog(TableProcessor.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 在数据库中创建表，也创建固定的七个字段
	 * 
	 * @param tableName
	 */
	public void createTable(String tableName) {
		log.debug("createTable:" + tableName);
		String sql = "CREATE TABLE " + tableName + "(org character varying(36),master character varying(36),"
				+ "create_user_id character varying(36),create_date timestamp without time zone,"
				+ "cancel_user_id character varying(36),cancel_date timestamp without time zone,"
				+ "state character varying(1))";
		this.jdbcTemplate.execute(sql);
	}

	/**
	 * 删除一张表
	 * 
	 * @param tableName
	 */
	public void dropTable(String tableName) {
		log.debug("drop table " + tableName);
		String sql = "drop table " + tableName;
		this.jdbcTemplate.execute(sql);
	}
	/**
	 * 重命名表
	 * @param oldTableName 原有名称
	 * @param newTableName 新的名称
	 */
	public void renameTable(String oldTableName, String newTableName){
		String sql = "ALTER TABLE " + oldTableName + " RENAME TO " + newTableName;
		this.jdbcTemplate.execute(sql);
	}
	/**
	 * 往表中添加字段
	 * @param tableName 表名称
	 * @param fieldName 字段名称
	 * @param fieldType 字段类型
	 * @param length 字段长度
	 */
	public void addField(String tableName, String fieldName, String fieldType, Integer length) {
		System.out.println("add field " + fieldName + " into " + tableName);
		log.debug("add field " + fieldName + " into " + tableName);
		String sql = "ALTER TABLE " + tableName + " ADD COLUMN " + fieldName + " "
				+ getFieldTypeName(fieldType, length);
		this.jdbcTemplate.execute(sql);
	}
	/**
	 * 修改字段名称
	 * @param tableName 表名称 
	 * @param oldFieldName 老字段名称
	 * @param newFieldName 新字段名称
	 */
	public void renameField(String tableName, String oldFieldName, String newFieldName){
		String sql = "ALTER TABLE " + tableName + " RENAME COLUMN " + oldFieldName + " TO " + newFieldName;
		this.jdbcTemplate.execute(sql);
	}
	/**
	 * 修改字段类型
	 * @param tableName 表名称
	 * @param fieldName 字段名称
	 * @param fieldType 新的字段类型
	 * @param length 新的字段长度
	 */
	public void alterFieldType(String tableName, String fieldName, String newFieldType, Integer newLength) {
		String sql ="ALTER TABLE " + tableName + " ALTER COLUMN " + fieldName + " TYPE " + this.getFieldTypeName(newFieldType, newLength);
		this.jdbcTemplate.execute(sql);
	}
	/**
	 * 删除字段
	 * @param tableName 表名称
	 * @param fieldName 字段名称
	 */
	public void dropField(String tableName, String fieldName){
		String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + fieldName + " RESTRICT";
		this.jdbcTemplate.execute(sql);
	}
	private String getFieldTypeName(String fieldType, Integer length) {
		switch (fieldType) {
		case TableField.FIELD_TYPE_STRING:
			if (length == null){
				throw new RuntimeException("字符串类型长度不能为空");
			}
			return "character varying(" + length.intValue() + ")";
		case TableField.FIELD_TYPE_NUMBER:
			return "integer";
		case TableField.FIELD_TYPE_DATE:
			return "timestamp without time zone";
		case TableField.FIELD_TYPE_DICT:
			return "character(36)";
		case TableField.FIELD_TYPE_FK:
			return "character(36)";
		case TableField.FIELD_TYPE_PK:
			return "character(36)";
		default:
			throw new RuntimeException("未知字段类型异常:" + fieldType);
		}
	}

}