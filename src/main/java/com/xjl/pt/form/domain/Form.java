package com.xjl.pt.form.domain;
import com.xjl.pt.core.domain.XJLDomain;
import com.xjl.pt.core.annotation.FieldDB;
import com.xjl.pt.core.annotation.TableDB;
import java.util.Date;
/**
* 表单
* @author DomainCoderTools lilisheng
*
*/
@TableDB(name="xjl_pt_form")
public class Form extends XJLDomain {
	//表单id
	private String formId;
	//表单名称
	private String formName;
	//表单描述
	private String formDesc;
	//表单对应的html
	private String formHtml;
	//表单提交前的js脚本
	private String beforeShowScript;
	//表单提交后由java执行的js脚本
	private String beforeSubmitScript;
	public void setFormId(String formId){
		this.formId = formId;
	}
	public String getFormId(){
		return this.formId;
	}
	public void setFormName(String formName){
		this.formName = formName;
	}
	public String getFormName(){
		return this.formName;
	}
	public void setFormDesc(String formDesc){
		this.formDesc = formDesc;
	}
	public String getFormDesc(){
		return this.formDesc;
	}
	public void setFormHtml(String formHtml){
		this.formHtml = formHtml;
	}
	public String getFormHtml(){
		return this.formHtml;
	}
	public void setBeforeShowScript(String beforeShowScript){
		this.beforeShowScript = beforeShowScript;
	}
	public String getBeforeShowScript(){
		return this.beforeShowScript;
	}
	public void setBeforeSubmitScript(String beforeSubmitScript){
		this.beforeSubmitScript = beforeSubmitScript;
	}
	public String getBeforeSubmitScript(){
		return this.beforeSubmitScript;
	}
}
