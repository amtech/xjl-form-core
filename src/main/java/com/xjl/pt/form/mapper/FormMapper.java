package com.xjl.pt.form.mapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.xjl.pt.core.mapper.XJLMapper;
import com.xjl.pt.core.domain.XJLDomain;
import com.xjl.pt.form.domain.Form;
/**
 * 表单
 * @author MapperCoderTools lilisheng
 *
*/
@Repository
public interface FormMapper {
	static final String TABLE_NAME = "xjl_pt_form";
	static final String SELECT_ALL = "form_id as formId,form_name as formName,form_desc as formDesc,form_html as formHtml,before_show_script as beforeShowScript,before_submit_script as beforeSubmitScript,"+ XJLMapper.FIX_SELECT_FIELD;
	static final String INSERT_FIELD = "form_id,form_name,form_desc,form_html,before_show_script,before_submit_script," + XJLMapper.FIX_INSERT_FIELD;
	static final String INSERT_VALUE = "#{formId},#{formName},#{formDesc},#{formHtml},#{beforeShowScript},#{beforeSubmitScript}," + XJLMapper.FIX_INSERT_VALUE;
	static final String UPDATE_FIELD = "form_name=#{formName},form_desc=#{formDesc},form_html=#{formHtml},before_show_script=#{beforeShowScript},before_submit_script=#{beforeSubmitScript}," + XJLMapper.FIX_UPDATE_FIELD;
	@Insert("insert into " + TABLE_NAME + "(" + INSERT_FIELD + ") values("+INSERT_VALUE+")")
	public void insert(XJLDomain domain);
	@Update("update " + TABLE_NAME + " set " + XJLMapper.FIX_DELETE_FIELD + " where form_id=#{formId}")	
	public void delete(XJLDomain domain);
	@Update("update " + TABLE_NAME + " set " + UPDATE_FIELD + " where form_id=#{formId}")	
	public void update(XJLDomain domain);
	@Select("select " + SELECT_ALL + " from " + TABLE_NAME + " where state='A' order by create_date desc")
	public List<Form> selectAll();
	@Select("select " + SELECT_ALL + " from " + TABLE_NAME + " where form_id=#{formId}")
	public Form selectById(String dictId);
	
	
	
}