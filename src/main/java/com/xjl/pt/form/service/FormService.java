package com.xjl.pt.form.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.xjl.pt.form.domain.Form;
import com.xjl.pt.form.mapper.FormMapper;
/**
 * 表单的服务类
 * @author leasonlive
 *
 */
@Service
public class FormService {
	@Autowired
	private FormMapper formMapper;
	/**
	 * 查询
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Form> query(int page, int pageSize){
		PageHelper.startPage(page, pageSize);
		return this.formMapper.select();
	}
	/**
	 * 新增加一个form
	 * @param form
	 */
	public void add(Form form){
		if (StringUtils.isBlank(form.getFormId())){
			form.setFormId(UUID.randomUUID().toString());
		}
		this.formMapper.insert(form);
	}
	public void delete(String formId){
		this.formMapper.delete(formId);
	}
}
