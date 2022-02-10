package com.realnet.wfb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RN_FB_LINES_T")

public class Rn_Fb_Line {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	// @SequenceGenerator(name = "course_seq", sequenceName =
	// "KOEL_MENU_REGISTER_S", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "FIELD_NAME")
	private String field_name;

	@Column(name = "MAPPING")
	private String mapping;

	@Column(name = "DATA_TYPE")
	private String data_type;

	@Column(name = "FORM_CODE")
	private String form_code;

	@Column(name = "KEY1")
	private String key1;

	@Column(name = "TYPE1")
	private String type1;

	@Column(name = "MANDATORY")
	private String mandatory;

	@Column(name = "HIDDEN")
	private String hidden;

	@Column(name = "READONLY")
	private String readonly;

	@Column(name = "DEPENDENT")
	private String dependent;

	@Column(name = "DEPENDENT_ON")
	private String dependent_on;

	@Column(name = "DEPENDENT_SP")
	private String dependent_sp;

	@Column(name = "DEPENDENT_SP_PARAM")
	private String dependent_sp_param;

	@Column(name = "VALIDATION_1")
	private String validation_1;

	@Column(name = "VAL_TYPE")
	private String val_type;

	@Column(name = "VAL_SP")
	private String val_sp;

	@Column(name = "VAL_SP_PARAM")
	private String val_sp_param;

	@Column(name = "SEQUENCE")
	private String sequence;

	@Column(name = "SEQ_NAME")
	private String seq_name;

	@Column(name = "SEQ_SP")
	private String seq_sp;

	@Column(name = "SEQ_SP_PARAM")
	private String seq_sp_param;

	@Column(name = "DEFAULT_1")
	private String default_1;

	@Column(name = "DEFAULT_TYPE")
	private String default_type;

	@Column(name = "DEFAULT_VALUE")
	private String default_value;

	@Column(name = "DEFAULT_SP")
	private String default_sp;

	@Column(name = "DEFAULT_SP_PARAM")
	private String default_sp_param;

	@Column(name = "CALCULATED_FIELD")
	private String calculated_field;

	@Column(name = "CAL_SP")
	private String cal_sp;

	@Column(name = "CAL_SP_PARAM")
	private String cal_sp_param;

	@Column(name = "ADD_TO_GRID")
	private String add_to_grid;

	@Column(name = "SP_FOR_AUTOCOMPLETE")
	private String sp_for_autocomplete;

	@Column(name = "SP_FOR_DROPDOWN")
	private String sp_for_dropdown;

	@Column(name = "SP_NAME_FOR_DROPDOWN")
	private String sp_name_for_dropdown;

	@Column(name = "ACCOUNT_ID")
	private int account_id;

	@Column(name = "MODULE_ID")
	private int module_id;

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public int getId() {
		return id;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	@Column(name = "PROJECT_ID")
	private int project_id;

	public void setId(int id) {
		this.id = id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getForm_code() {
		return form_code;
	}

	public void setForm_code(String form_code) {
		this.form_code = form_code;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	public String getHidden() {
		return hidden;
	}

	public void setHidden(String hidden) {
		this.hidden = hidden;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getDependent() {
		return dependent;
	}

	public void setDependent(String dependent) {
		this.dependent = dependent;
	}

	public String getDependent_on() {
		return dependent_on;
	}

	public void setDependent_on(String dependent_on) {
		this.dependent_on = dependent_on;
	}

	public String getDependent_sp() {
		return dependent_sp;
	}

	public void setDependent_sp(String dependent_sp) {
		this.dependent_sp = dependent_sp;
	}

	public String getDependent_sp_param() {
		return dependent_sp_param;
	}

	public void setDependent_sp_param(String dependent_sp_param) {
		this.dependent_sp_param = dependent_sp_param;
	}

	public String getValidation_1() {
		return validation_1;
	}

	public void setValidation_1(String validation_1) {
		this.validation_1 = validation_1;
	}

	public String getVal_type() {
		return val_type;
	}

	public void setVal_type(String val_type) {
		this.val_type = val_type;
	}

	public String getVal_sp() {
		return val_sp;
	}

	public void setVal_sp(String val_sp) {
		this.val_sp = val_sp;
	}

	public String getVal_sp_param() {
		return val_sp_param;
	}

	public void setVal_sp_param(String val_sp_param) {
		this.val_sp_param = val_sp_param;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getSeq_name() {
		return seq_name;
	}

	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}

	public String getSeq_sp() {
		return seq_sp;
	}

	public void setSeq_sp(String seq_sp) {
		this.seq_sp = seq_sp;
	}

	public String getSeq_sp_param() {
		return seq_sp_param;
	}

	public void setSeq_sp_param(String seq_sp_param) {
		this.seq_sp_param = seq_sp_param;
	}

	public String getDefault_1() {
		return default_1;
	}

	public void setDefault_1(String default_1) {
		this.default_1 = default_1;
	}

	public String getDefault_type() {
		return default_type;
	}

	public void setDefault_type(String default_type) {
		this.default_type = default_type;
	}

	public String getDefault_value() {
		return default_value;
	}

	public void setDefault_value(String default_value) {
		this.default_value = default_value;
	}

	public String getDefault_sp() {
		return default_sp;
	}

	public void setDefault_sp(String default_sp) {
		this.default_sp = default_sp;
	}

	public String getDefault_sp_param() {
		return default_sp_param;
	}

	public void setDefault_sp_param(String default_sp_param) {
		this.default_sp_param = default_sp_param;
	}

	public String getCalculated_field() {
		return calculated_field;
	}

	public void setCalculated_field(String calculated_field) {
		this.calculated_field = calculated_field;
	}

	public String getCal_sp() {
		return cal_sp;
	}

	public void setCal_sp(String cal_sp) {
		this.cal_sp = cal_sp;
	}

	public String getCal_sp_param() {
		return cal_sp_param;
	}

	public void setCal_sp_param(String cal_sp_param) {
		this.cal_sp_param = cal_sp_param;
	}

	public String getAdd_to_grid() {
		return add_to_grid;
	}

	public void setAdd_to_grid(String add_to_grid) {
		this.add_to_grid = add_to_grid;
	}

	public String getSp_for_autocomplete() {
		return sp_for_autocomplete;
	}

	public void setSp_for_autocomplete(String sp_for_autocomplete) {
		this.sp_for_autocomplete = sp_for_autocomplete;
	}

	public String getSp_for_dropdown() {
		return sp_for_dropdown;
	}

	public void setSp_for_dropdown(String sp_for_dropdown) {
		this.sp_for_dropdown = sp_for_dropdown;
	}

	public String getSp_name_for_dropdown() {
		return sp_name_for_dropdown;
	}

	public void setSp_name_for_dropdown(String sp_name_for_dropdown) {
		this.sp_name_for_dropdown = sp_name_for_dropdown;
	}

	public String getSp_name_for_autocomplete() {
		return sp_name_for_autocomplete;
	}

	public void setSp_name_for_autocomplete(String sp_name_for_autocomplete) {
		this.sp_name_for_autocomplete = sp_name_for_autocomplete;
	}

	@Column(name = "SP_NAME_FOR_AUTOCOMPLETE")
	private String sp_name_for_autocomplete;

	public String getType_field() {
		return type_field;
	}

	public void setType_field(String type_field) {
		this.type_field = type_field;
	}

	@Column(name = "TYPE_FIELD")
	private String type_field;
	
	@Column(name = "METHOD_NAME")
	private String method_name;

	public String getMethod_name() {
		return method_name;
	}

	public void setMethod_name(String method_name) {
		this.method_name = method_name;
	}

	@Column(name = "HEADER_ID")
	private int header_id;

	@Column(name = "SEQ")
	private int seq;

	@Column(name = "FORM_TYPE")
	private String form_type;

	@Column(name = "SECTION_NUM")
	private int section_num;

	@Column(name = "BUTTON_NUM")
	private int button_num;

	public int getHeader_id() {
		return header_id;
	}

	public void setHeader_id(int header_id) {
		this.header_id = header_id;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getForm_type() {
		return form_type;
	}

	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}

	public int getSection_num() {
		return section_num;
	}

	public void setSection_num(int section_num) {
		this.section_num = section_num;
	}

	public int getButton_num() {
		return button_num;
	}

	public void setButton_num(int button_num) {
		this.button_num = button_num;
	}

	@Column(name = "TYPE2")
	private String type2;

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	@Column(name = "LINE_TABLE_NAME")
	private String line_table_name;

	@Column(name = "LINE_TABLE_NO")
	private int line_table_no;

	public String getLine_table_name() {
		return line_table_name;
	}

	public void setLine_table_name(String line_table_name) {
		this.line_table_name = line_table_name;
	}

	public int getLine_table_no() {
		return line_table_no;
	}

	public void setLine_table_no(int line_table_no) {
		this.line_table_no = line_table_no;
	}

}
