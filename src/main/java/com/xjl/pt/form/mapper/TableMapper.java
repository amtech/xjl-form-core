package com.xjl.pt.form.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
/**
 * 表
 * @author lilisheng
 *
 */

import com.xjl.pt.core.domain.XJLDomain;
import com.xjl.pt.core.mapper.XJLMapper;
import com.xjl.pt.form.domain.Table;
@Repository
public interface TableMapper {
	public static final String TABLE_NAME="XJL_PT_TABLE";
	@Insert("insert into " + TABLE_NAME +"(table_id,table_name,table_desc,"+ XJLMapper.FIX_INSERT_FIELD
			+ ") values(#{tableId},#{tableName},#{tableDesc},"+XJLMapper.FIX_INSERT_VALUE+")")
	public void insert(XJLDomain table);
	@Select("select table_id as tableId,table_name as tableName,table_desc as tableDesc"
			+ ","+XJLMapper.FIX_SELECT_FIELD
			+ " from " + TABLE_NAME + " where state='A'")
	public List<Table> selectAll();
	@Select("select table_id as tableId,table_name as tableName,table_desc as tableDesc"
			+ ","+XJLMapper.FIX_SELECT_FIELD
			+ " from " + TABLE_NAME + " where table_id=#{tableId}")
	public Table selectById(String tableId);

}
