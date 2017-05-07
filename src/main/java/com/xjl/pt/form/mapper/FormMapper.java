package com.xjl.pt.form.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.xjl.pt.form.domain.Form;

@Repository
public interface FormMapper {
	@Insert("insert into xjl_pt_form(form_id, form_name,form_desc,form_html,before_show_script,before_submit_script) "
			+ "values(#{formId},#{formName},#{formDesc},#{formHtml},#{beforeShowScript},#{beforeSubmitScript})")
	public void insert(Form form);
	@Select("select form_id as formId, form_name as formName,form_desc as formDesc,form_html as formHtml "
			+ ",before_show_script as beforeShowScript,before_submit_script as beforeSubmitScript "
			+ "from xjl_pt_form")
	public List<Form> all();
	@Delete("delete from xjl_pt_form where form_id=#{formId}")
	public void delete(String formId);
}
