package com.xjl.pt.form.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xjl.pt.core.domain.XJLDomain;
import com.xjl.pt.core.mapper.XJLMapper;
import com.xjl.pt.form.domain.TableField;

/**
 * 字段
 * @author lilisheng
 *
 */
@Repository
public interface TableFieldMapper {
	public static final String TABLE_NAME="XJL_PT_TABLE_FIELD";
	@Insert("insert into " + TABLE_NAME +"("
			+ "field_id,field_name,field_desc,field_type,"
			+ "field_length,pk,dict_id,table_id,foreign_table_id,"+ XJLMapper.FIX_INSERT_FIELD
			+ ") values("
			+ "#{fieldId},#{fieldName},#{fieldDesc},#{fieldType},"
			+ "#{fieldLength},#{pk},#{dictId},#{tableId},#{foreignTableId},"+XJLMapper.FIX_INSERT_VALUE+")")
	public void insert(XJLDomain field);
	@Select("select field_id as fieldId,field_name as fieldName,field_desc as fieldDesc"
			+ ",field_type as fieldType,field_length as fieldLength,pk as pk"
			+ ",dict_id as dictId, table_id as tableId,foreign_table_id foreignTableId"
			+ ","+XJLMapper.FIX_SELECT_FIELD
			+ " from " + TABLE_NAME + " where table_id=#{tableId} and state='A'")
	public List<TableField> selectByTableId(String tableId);
	@Select("select count(*) from " + TABLE_NAME + " where table_id=#{tableId} and state='A'")
	public int selectCountByTableId(String tableId);
}
