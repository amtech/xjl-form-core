package com.xjl.pt.form.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjl.pt.core.domain.Dict;
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
	@Autowired
	private TableFieldMapper tableFieldMapper;
	@Autowired
	private TableService tableService;
	@Autowired
	private DictService dictService;
	@Override
	public void _add(XJLDomain domain) {
		this.tableFieldMapper.insert(domain);
	}

	@Override
	public void _delete(XJLDomain domain) {
		throw new RuntimeException("该方法还未实现");
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
		switch (field.getFieldType()){
		case "10":
			field.setFieldType$name("字符");
			break;
		case "20":
			field.setFieldType$name("数字");
			break;
		case "30":
			field.setFieldType$name("日期");
			break;
		case "40":
			field.setFieldType$name("字典");
			break;
		default:
			field.setFieldType$name("未知");
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
