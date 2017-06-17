package com.xjl.pt.form.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjl.pt.core.service.XJLService;
import com.xjl.pt.core.domain.XJLDomain;
import com.xjl.pt.form.domain.Form;
import com.xjl.pt.form.mapper.FormMapper;

/**
 * 表单
 * @author ServiceCoderTools lilisheng
 *
 */
@Service
public class FormService extends XJLService {
	@Autowired
	private FormMapper formMapper;
	@Override
	public void _add(XJLDomain domain) {
		this.formMapper.insert(domain);
	}
	@Override
	public void _delete(XJLDomain domain) {
		this.formMapper.delete(domain);
	}
	@Override
	public void _modify(XJLDomain domain) {
		this.formMapper.update(domain);
	}
	public List<Form> query(String search, int page, int pageSize) {
		if (StringUtils.isEmpty(search)){
			PageHelper.startPage(page, pageSize);
			return this.formMapper.selectAll();
		} else {
			throw new RuntimeException("带search值的查询还没有实现");
		}
	}
	public Form queryById(String formId) {
		return this.formMapper.selectById(formId);
	}
	@Override
	public void _resetNewId(XJLDomain domain) {
		((Form)domain).setFormId(UUID.randomUUID().toString());
	}
}
