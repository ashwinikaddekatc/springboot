package com.realnet.fnd.model;

import java.util.Date;

public class Rn_Lookup_Autofill 
{
	private String lookup_type;
	private String active_start_date;
	private String active_end_date;
	private String enabled_flag;
	private int created_by;
	private String creation_date;
	private int last_updated_by;
	private String last_update_date;
	
	private String lookup_code;
	private String meaning;
	private String description;
	
	// for roles and access
	private int user_role_assignment_id;
	private int user_id;
	private String role;
	private String enable_flag;
	private String end_date;
	
	//menu register
	private int main_menu_id;
	private String main_menu_name;
	private String main_menu_action_name;
	private String main_menu_icon;
	
	//function register
	private int menu_id;
	private String menu_name;
	private int function_id;
	private String function_name;
	private String function_action_name;
	private String f_enable_flag;
	private String f_end_date;
	
	//report group header
	private int report_group_header_id;
	private String report_name;
	private String gr_description;
	private String header_active;
	private String header_start_date;
	private String header_end_date;
	
	//vacation rule
	private int vacation_rule_id;
	private int to_user_id;
	private String from_date;
	private String to_date;
	private String status;
	private String rule_type;
	
	
	
	private String patient_country;
	public String getPatient_country() {
		return patient_country;
	}
	public void setPatient_country(String patient_country) {
		this.patient_country = patient_country;
	}
	public String getPatient_state() {
		return patient_state;
	}
	public void setPatient_state(String patient_state) {
		this.patient_state = patient_state;
	}
	public String getPatient_city() {
		return patient_city;
	}
	public void setPatient_city(String patient_city) {
		this.patient_city = patient_city;
	}
	private String patient_state;
	private String patient_city;
	
	//private String table_name;
	
	
	
	
	public int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	private String table_name;
	private String jsp_name;
	private String form_code;
	private String controller_name;
	private String service_name;
	private String service_impl_name;
	private String dao_name;
	private String dao_impl_name;
	
	
	
	
	
	
	public int getMain_menu_id() {
		return main_menu_id;
	}
	public void setMain_menu_id(int main_menu_id) {
		this.main_menu_id = main_menu_id;
	}
	public String getMain_menu_name() {
		return main_menu_name;
	}
	public void setMain_menu_name(String main_menu_name) {
		this.main_menu_name = main_menu_name;
	}
	public String getMain_menu_action_name() {
		return main_menu_action_name;
	}
	public void setMain_menu_action_name(String main_menu_action_name) {
		this.main_menu_action_name = main_menu_action_name;
	}
	public String getMain_menu_icon() {
		return main_menu_icon;
	}
	public void setMain_menu_icon(String main_menu_icon) {
		this.main_menu_icon = main_menu_icon;
	}
	public int getUser_role_assignment_id() {
		return user_role_assignment_id;
	}
	public void setUser_role_assignment_id(int user_role_assignment_id) {
		this.user_role_assignment_id = user_role_assignment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEnable_flag() {
		return enable_flag;
	}
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getLookup_type() {
		return lookup_type;
	}
	public void setLookup_type(String lookup_type) {
		this.lookup_type = lookup_type;
	}
	public String getActive_start_date() {
		return active_start_date;
	}
	public void setActive_start_date(String active_start_date) {
		this.active_start_date = active_start_date;
	}
	public String getActive_end_date() {
		return active_end_date;
	}
	public void setActive_end_date(String active_end_date) {
		this.active_end_date = active_end_date;
	}
	public String getEnabled_flag() {
		return enabled_flag;
	}
	public void setEnabled_flag(String enabled_flag) {
		this.enabled_flag = enabled_flag;
	}
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public String getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(String creation_date) {
		this.creation_date = creation_date;
	}
	public int getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(int last_updated_by) {
		this.last_updated_by = last_updated_by;
	}
	public String getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(String last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getLookup_code() {
		return lookup_code;
	}
	public void setLookup_code(String lookup_code) {
		this.lookup_code = lookup_code;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public int getFunction_id() {
		return function_id;
	}
	public void setFunction_id(int function_id) {
		this.function_id = function_id;
	}
	public String getFunction_name() {
		return function_name;
	}
	public void setFunction_name(String function_name) {
		this.function_name = function_name;
	}
	public String getFunction_action_name() {
		return function_action_name;
	}
	public void setFunction_action_name(String function_action_name) {
		this.function_action_name = function_action_name;
	}
	public String getF_enable_flag() {
		return f_enable_flag;
	}
	public void setF_enable_flag(String f_enable_flag) {
		this.f_enable_flag = f_enable_flag;
	}
	public String getF_end_date() {
		return f_end_date;
	}
	public void setF_end_date(String f_end_date) {
		this.f_end_date = f_end_date;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getReport_group_header_id() {
		return report_group_header_id;
	}
	public void setReport_group_header_id(int report_group_header_id) {
		this.report_group_header_id = report_group_header_id;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getGr_description() {
		return gr_description;
	}
	public void setGr_description(String gr_description) {
		this.gr_description = gr_description;
	}
	public String getHeader_active() {
		return header_active;
	}
	public void setHeader_active(String header_active) {
		this.header_active = header_active;
	}
	public String getHeader_start_date() {
		return header_start_date;
	}
	public void setHeader_start_date(String header_start_date) {
		this.header_start_date = header_start_date;
	}
	public String getHeader_end_date() {
		return header_end_date;
	}
	public void setHeader_end_date(String header_end_date) {
		this.header_end_date = header_end_date;
	}
	public int getVacation_rule_id() {
		return vacation_rule_id;
	}
	public void setVacation_rule_id(int vacation_rule_id) {
		this.vacation_rule_id = vacation_rule_id;
	}
	public int getTo_user_id() {
		return to_user_id;
	}
	public void setTo_user_id(int to_user_id) {
		this.to_user_id = to_user_id;
	}
	public String getFrom_date() {
		return from_date;
	}
	public void setFrom_date(String from_date) {
		this.from_date = from_date;
	}
	public String getTo_date() {
		return to_date;
	}
	public void setTo_date(String to_date) {
		this.to_date = to_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRule_type() {
		return rule_type;
	}
	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}
	
}
