package com.xjl.pt.form.domain;
/**
 * 表单
 * @author leasonlive
 *
 */
public class Form {
	//表单id
	private String formId;
	//表单名称
	private String formName;
	//表单描述
	private String formDesc;
	//表单对应的html
	private String formHtml;
	//表单显示前需要执行的脚本
	private String beforeShowScript;
	//表单提交的时候需要执行的脚本
	private String beforeSubmitScript;
	public String getFormId() {
		return formId;
	}
	public void setFormId(String formId) {
		this.formId = formId;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getFormDesc() {
		return formDesc;
	}
	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}
	public String getFormHtml() {
		return formHtml;
	}
	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}
	public String getBeforeShowScript() {
		return beforeShowScript;
	}
	public void setBeforeShowScript(String beforeShowScript) {
		this.beforeShowScript = beforeShowScript;
	}
	public String getBeforeSubmitScript() {
		return beforeSubmitScript;
	}
	public void setBeforeSubmitScript(String beforeSubmitScript) {
		this.beforeSubmitScript = beforeSubmitScript;
	}
	
}
