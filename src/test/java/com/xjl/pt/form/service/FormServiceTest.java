package com.xjl.pt.form.service;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.xjl.pt.form.domain.Form;
import com.xjl.pt.form.service.FormService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:ApplicationContext-*.xml"})
public class FormServiceTest {
	@Autowired
	private FormService formService;
	@Test
	public void find(){
		List<Form> list = this.formService.find(1, 10);
		PageInfo<Form> pageInfo = new PageInfo<Form>(list);
		for (Form form : list) {
			System.out.println(form.getFormName());
		}
		System.out.println(pageInfo.getTotal());
		System.out.println(list.size());
	}
	@Test
	public void inert(){
		Form form = new Form();
		String formId = UUID.randomUUID().toString();
		Assert.assertEquals(36, formId.length());
		form.setFormId(formId);
		form.setFormName("测试表单2");
		form.setFormHtml("helloXJL HTML");
		this.formService.insert(form);
	}
	@Test
	public void delete(){
		this.formService.delete("5729fe6f-1d7f-4a28-958a-27274962246c");
	}
}
