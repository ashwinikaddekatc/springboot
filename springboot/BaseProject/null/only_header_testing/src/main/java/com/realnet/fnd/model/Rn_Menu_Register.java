package com.realnet.fnd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "RN_MENU_REGISTER_T")

public class Rn_Menu_Register 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "course_seq")
	@SequenceGenerator(name = "course_seq", sequenceName = "KOEL_MENU_REGISTER_S", allocationSize = 1)
	
	@Column(name = "MAIN_MENU_ID")
	private int main_menu_id;
	
	@Column(name = "MAIN_MENU_NAME")
	private String main_menu_name;
	
	@Column(name = "MAIN_MENU_CTRLR_NAME")	
	private String main_menu_ctrlr_name;
	
	@Column(name = "MAIN_MENU_ACTION_NAME")	
	private String main_menu_action_name;
	
	@Column(name = "MAIN_MENU_ICON")	
	private String main_menu_icon;
	
	@Column(name = "ENABLE_FLAG")	
	private String enable_flag;
	
	@Column(name = "CREATED_BY",updatable=false)
	private int created_by;
	
	//@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "END_DATE")
	private Date end_date;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "CREATION_DATE", updatable=false)
	
	private Date creation_date= new java.sql.Date(new java.util.Date().getTime());
	@Column(name = "LAST_UPDATED_BY")
	private int last_updated_by;
	
	@DateTimeFormat(pattern = "YYYY-MM-DD")
	@Column(name = "LAST_UPDATE_DATE")
	private Date last_update_date= new java.sql.Date(new java.util.Date().getTime());

	@Column(name = "ATTRIBUTE1")
	private String attribute1;
	
	@Column(name = "ATTRIBUTE2")
	private String attribute2;
	
	@Column(name = "ATTRIBUTE3")
	private String attribute3;
	
	@Column(name = "ATTRIBUTE4")	
	private String attribute4;	
	
	@Column(name = "ATTRIBUTE5")	
	private String attribute5;
	
	
	
	public String getEnable_flag() {
		return enable_flag;
	}
	public void setEnable_flag(String enable_flag) {
		this.enable_flag = enable_flag;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
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
	public String getMain_menu_ctrlr_name() {
		return main_menu_ctrlr_name;
	}
	public void setMain_menu_ctrlr_name(String main_menu_ctrlr_name) {
		this.main_menu_ctrlr_name = main_menu_ctrlr_name;
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
	public int getCreated_by() {
		return created_by;
	}
	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}
	public Date getCreation_date() {
		return creation_date;
	}
	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}
	public int getLast_updated_by() {
		return last_updated_by;
	}
	public void setLast_updated_by(int last_updated_by) {
		this.last_updated_by = last_updated_by;
	}
	public Date getLast_update_date() {
		return last_update_date;
	}
	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	
		
}

