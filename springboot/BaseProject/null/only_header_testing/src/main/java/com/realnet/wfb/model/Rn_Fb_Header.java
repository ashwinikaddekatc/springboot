package com.realnet.wfb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RN_FB_HEADER_T")
public class Rn_Fb_Header {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	public Rn_Fb_Header(int data) {
		this.id = data;

	}

	public void Rn_Fb_Header1(int data1) {
		this.project_id = data1;

	}

	@Column(name = "TECH_STACK")
	private String tech_stack;

	@Column(name = "OBJECT_TYPE")
	private String object_type;

	@Column(name = "SUB_OBJECT_TYPE")
	private String sub_object_type;

	public String getTech_stack() {
		return tech_stack;
	}

	public void setTech_stack(String tech_stack) {
		this.tech_stack = tech_stack;
	}

	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

	public String getSub_object_type() {
		return sub_object_type;
	}

	public void setSub_object_type(String sub_object_type) {
		this.sub_object_type = sub_object_type;
	}

	@Column(name = "ACCOUNT_ID")
	private int account_id;

	@Column(name = "PROJECT_ID")
	private int project_id;

	@Column(name = "MODULE_ID")
	private int module_id;

	public int getModule_id() {
		return module_id;
	}

	public Rn_Fb_Header(int data, String data1) {
		this.id = data;
		this.ui_name = data1;
	}

	public Rn_Fb_Header() {
		// TODO Auto-generated constructor stub
	}

	public Rn_Fb_Header(String data1) {
		// TODO Auto-generated constructor stub
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	/*
	 * public String getTag_name() { return tag_name; }
	 * 
	 * public void setTag_name(String tag_name) { this.tag_name = tag_name; }
	 */

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}

	public String getJsp_name() {
		return jsp_name;
	}

	public void setJsp_name(String jsp_name) {
		this.jsp_name = jsp_name;
	}

	public String getForm_code() {
		return form_code;
	}

	public void setForm_code(String form_code) {
		this.form_code = form_code;
	}

	public String getController_name() {
		return controller_name;
	}

	public void setController_name(String controller_name) {
		this.controller_name = controller_name;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_impl_name() {
		return service_impl_name;
	}

	public void setService_impl_name(String service_impl_name) {
		this.service_impl_name = service_impl_name;
	}

	public String getDao_name() {
		return dao_name;
	}

	public void setDao_name(String dao_name) {
		this.dao_name = dao_name;
	}

	public String getDao_impl_name() {
		return dao_impl_name;
	}

	public void setDao_impl_name(String dao_impl_name) {
		this.dao_impl_name = dao_impl_name;
	}

	/*
	 * @Column(name = "TAG_NAME") public String tag_name;
	 */

	@Column(name = "FORM_TYPE")
	private String form_type;

	public String getForm_type() {
		return form_type;
	}

	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}

	@Column(name = "TABLE_NAME")
	private String table_name;

	@Column(name = "LINE_TABLE_NAME")
	private String line_table_name;

	public String getLine_table_name() {
		return line_table_name;
	}

	public void setLine_table_name(String line_table_name) {
		this.line_table_name = line_table_name;
	}

	@Column(name = "MULTILINE_TABLE_NAME")
	private String multiline_table_name;

	public String getMultiline_table_name() {
		return multiline_table_name;
	}

	public void setMultiline_table_name(String multiline_table_name) {
		this.multiline_table_name = multiline_table_name;
	}

	@Column(name = "JSP_NAME")
	private String jsp_name;

	@Column(name = "FORM_CODE")
	private String form_code;

	@Column(name = "CONTROLLER_NAME")
	private String controller_name;

	@Column(name = "SERVICE_NAME")
	private String service_name;

	@Column(name = "SERVICE_IMPL_NAME")
	private String service_impl_name;

	@Column(name = "DAO_NAME")
	private String dao_name;

	@Column(name = "DAO_IMPL_NAME")
	private String dao_impl_name;

	/*
	 * @Column(name = "COLUMN_NAME") private String column_name;
	 * 
	 * public String getColumn_name() { return column_name; }
	 * 
	 * public void setColumn_name(String column_name) { this.column_name =
	 * column_name; }
	 */
	@Column(name = "IS_BUILD")
	private String is_build;

	public String getIs_build() {
		return is_build;
	}

	public void setIs_build(String is_build) {
		this.is_build = is_build;
	}

	public String getIs_updated() {
		return is_updated;
	}

	public void setIs_updated(String is_updated) {
		this.is_updated = is_updated;
	}

	@Column(name = "IS_UPDATED")
	private String is_updated;

	@Column(name = "UI_NAME")
	private String ui_name;

	@Column(name = "MENU_NAME")
	private String menu_name;

	@Column(name = "HEADER_NAME")
	private String header_name;

	@Column(name = "CONVERTED_TABLE_NAME")
	private String converted_table_name;

	public String getUi_name() {
		return ui_name;
	}

	public void setUi_name(String ui_name) {
		this.ui_name = ui_name;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getHeader_name() {
		return header_name;
	}

	public void setHeader_name(String header_name) {
		this.header_name = header_name;
	}

	public String getConverted_table_name() {
		return converted_table_name;
	}

	public void setConverted_table_name(String converted_table_name) {
		this.converted_table_name = converted_table_name;
	}

	@Override
	public String toString() {
		return "Rn_Fb_Header [id=" + id + ", tech_stack=" + tech_stack + ", object_type=" + object_type
				+ ", sub_object_type=" + sub_object_type + ", account_id=" + account_id + ", project_id=" + project_id
				+ ", module_id=" + module_id + ", form_type=" + form_type + ", table_name=" + table_name
				+ ", line_table_name=" + line_table_name + ", multiline_table_name=" + multiline_table_name
				+ ", jsp_name=" + jsp_name + ", form_code=" + form_code + ", controller_name=" + controller_name
				+ ", service_name=" + service_name + ", service_impl_name=" + service_impl_name + ", dao_name="
				+ dao_name + ", dao_impl_name=" + dao_impl_name + ", is_build=" + is_build + ", is_updated="
				+ is_updated + ", ui_name=" + ui_name + ", menu_name=" + menu_name + ", header_name=" + header_name
				+ ", converted_table_name=" + converted_table_name + "]";
	}
	public Rn_Fb_Header(int id, String tech_stack, String object_type, String sub_object_type, int account_id, int project_id,
			int module_id, String form_type, String table_name, String line_table_name, String multiline_table_name,
			String jsp_name, String form_code, String controller_name, String service_name, String service_impl_name,
			String dao_name, String dao_impl_name, String is_build, String is_updated, String ui_name, String menu_name,
			String header_name, String converted_table_name) {
		super();
		this.id = id;
		this.tech_stack = tech_stack;
		this.object_type = object_type;
		this.sub_object_type = sub_object_type;
		this.account_id = account_id;
		this.project_id = project_id;
		this.module_id = module_id;
		this.form_type = form_type;
		this.table_name = table_name;
		this.line_table_name = line_table_name;
		this.multiline_table_name = multiline_table_name;
		this.jsp_name = jsp_name;
		this.form_code = form_code;
		this.controller_name = controller_name;
		this.service_name = service_name;
		this.service_impl_name = service_impl_name;
		this.dao_name = dao_name;
		this.dao_impl_name = dao_impl_name;
		this.is_build = is_build;
		this.is_updated = is_updated;
		this.ui_name = ui_name;
		this.menu_name = menu_name;
		this.header_name = header_name;
		this.converted_table_name = converted_table_name;
	}
	

	public Rn_Fb_Header(String tech_stack, String object_type, String sub_object_type, int account_id, int project_id,
			int module_id, String form_type, String table_name, String line_table_name, String multiline_table_name,
			String jsp_name, String form_code, String controller_name, String service_name, String service_impl_name,
			String dao_name, String dao_impl_name, String is_build, String is_updated, String ui_name, String menu_name,
			String header_name, String converted_table_name) {
		super();
		this.tech_stack = tech_stack;
		this.object_type = object_type;
		this.sub_object_type = sub_object_type;
		this.account_id = account_id;
		this.project_id = project_id;
		this.module_id = module_id;
		this.form_type = form_type;
		this.table_name = table_name;
		this.line_table_name = line_table_name;
		this.multiline_table_name = multiline_table_name;
		this.jsp_name = jsp_name;
		this.form_code = form_code;
		this.controller_name = controller_name;
		this.service_name = service_name;
		this.service_impl_name = service_impl_name;
		this.dao_name = dao_name;
		this.dao_impl_name = dao_impl_name;
		this.is_build = is_build;
		this.is_updated = is_updated;
		this.ui_name = ui_name;
		this.menu_name = menu_name;
		this.header_name = header_name;
		this.converted_table_name = converted_table_name;
	}

}