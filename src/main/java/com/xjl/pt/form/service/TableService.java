package com.xjl.pt.form.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjl.pt.core.domain.User;
import com.xjl.pt.core.domain.XJLDomain;
import com.xjl.pt.core.service.XJLService;
import com.xjl.pt.form.domain.Table;
import com.xjl.pt.form.mapper.TableMapper;

/**
 * 表对象的服务类
 * @author lilisheng
 *
 */
@Service
public class TableService extends XJLService{
	@Autowired
	private TableMapper tableMapper;
	@Autowired
	private TableProcessor tableProcessor;
	@Override
	public void _add(XJLDomain domain) {
		this.tableMapper.insert(domain);
		this.tableProcessor.createTable(((Table)domain).getTableName());
	}
	@Override
	public void _delete(XJLDomain domain) {
		this.tableMapper.delete(domain);
		this.tableProcessor.renameTable(((Table)domain).getTableName(), "_D_"+((Table)domain).getTableName());
	}
	@Override
	public void modify(XJLDomain domain, User user) {
		Table table = (Table)domain;
		Table dbTable = this.queryById(table.getTableId());
		this.tableMapper.update(domain);
		if (!StringUtils.equals(table.getTableName(), dbTable.getTableName())){
			//如果表的名称被修改过则需要修改物理表
			this.tableProcessor.renameTable(dbTable.getTableName(), table.getTableName());
		}
	}
	@Override
	public void _resetNewId(XJLDomain domain) {
		((Table)domain).setTableId(UUID.randomUUID().toString());
	}
	public List<Table> query(int page, int pageSize){
		PageHelper.startPage(page, pageSize);
		return this.tableMapper.selectAll();
	}
	public Table queryById(String tableId){
		if (StringUtils.isEmpty(tableId)){
			return null;
		}
		return this.tableMapper.selectById(tableId);
	}
	
}
