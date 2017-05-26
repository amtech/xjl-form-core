package com.xjl.pt.form.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xjl.pt.form.domain.TableField;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:ApplicationContext-*.xml"})
public class TableProcessorTest {
	@Autowired
	private TableProcessor tableProcessor;
	@Test
	public void createTable(){
		this.tableProcessor.createTable("test_hello");
	}
	@Test
	public void dropTable(){
		this.tableProcessor.dropTable("test_hello");
	}
	
	@Test
	public void addField(){
		this.tableProcessor.addField("test_hello", "pk", TableField.FIELD_TYPE_PK, 0);
		this.tableProcessor.addField("test_hello", "fk", TableField.FIELD_TYPE_FK, 0);
		this.tableProcessor.addField("test_hello", "zifu", TableField.FIELD_TYPE_STRING, 10);
		this.tableProcessor.addField("test_hello", "shuzi", TableField.FIELD_TYPE_NUMBER, 0);
		this.tableProcessor.addField("test_hello", "riqi", TableField.FIELD_TYPE_DATE, 0);
		this.tableProcessor.addField("test_hello", "zidian", TableField.FIELD_TYPE_DICT, 0);
	}
	@Test
	public void renameTable(){
		this.tableProcessor.renameTable("test_hello", "test_xjl");
	}
	@Test
	public void alterField(){
		this.tableProcessor.alterFieldType("test_xjl", "shuzi", TableField.FIELD_TYPE_STRING, 20);
	}
	@Test
	public void renameField(){
		this.tableProcessor.renameField("test_xjl", "shuzi", "shuzizhifu");
	}
	@Test
	public void dropField(){
		this.tableProcessor.dropField("test_xjl", "shuzizhifu");
	}
}
