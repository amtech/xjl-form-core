package com.xjl.pt.form.service;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xjl.pt.core.tools.CoderTools;
import com.xjl.pt.core.tools.XJLCoderTools;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:ApplicationContext-*.xml"})
public class CoderToolsTest {
	@Autowired
	private CoderTools coderTools;
	@Test
	public void getShortName(){
		String tableName = "xjl_pt_service_item";
		String shortName = XJLCoderTools.getShortName(tableName);
		Assert.assertEquals("service_item", shortName);
	}
	@Test
	public void getDomainName(){
		String tableName = "xjl_pt_service_Item";
		String domainName = XJLCoderTools.getDomainName(tableName);
		Assert.assertEquals("ServiceItem", domainName);
	}
	@Test
	public void getDomainFieldName(){
		String fieldName = "id";
		String domainFieldName = XJLCoderTools.getDomainFieldName(fieldName);
		Assert.assertEquals("id", domainFieldName);
		Assert.assertEquals("tableName", XJLCoderTools.getDomainFieldName("table_name"));
	}
	@Test
	public void getClassFile(){
		String className = "com.xjl.pt.core.domain.Abc";
		File classFile = XJLCoderTools.getClassFile(className);
		System.out.println(classFile.getAbsolutePath());
	}
	@Test
	public void generateForm(){
		this.coderTools.generateSDM("xjl_pt_form", "com.xjl.pt.form");
	}
}
