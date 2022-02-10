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
@Table(name = "RN_USER_ASSIGNMENTS_T")
public class Rn_User_Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "course_seq")
	@SequenceGenerator(name = "course_seq", sequenceName = "KOEL_USER_ASSIGNMENT_S", allocationSize = 1)
	
	
	@Column(name = "USER_ASSIGNMENT_ID")
	private int user_assignment_id;
	
	@Column(name = "USER_ID")
	private int user_id;
	
	@Column(name = "MENU_GROUP_ID")
	private int menu_group_id;
	
	@Column(name = "REPORT_GROUP_ID")
	private int report_group_id;
	

	@Column(name = "CREATED_BY")
	private int created_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "CREATION_DATE" , updatable = false)
	private Date creation_date= new java.sql.Date(new java.util.Date().getTime());

	@Column(name = "LAST_UPDATED_BY")
	private int last_updated_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "LAST_UPDATE_DATE" , updatable = true)
	private Date last_update_date= new java.sql.Date(new java.util.Date().getTime());

	
	@Column(name = "ATTRIBUTE1")
	private String attribute1;
	
	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	public int getUser_assignment_id() {
		return user_assignment_id;
	}

	public void setUser_assignment_id(int user_assignment_id) {
		this.user_assignment_id = user_assignment_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMenu_group_id() {
		return menu_group_id;
	}

	public void setMenu_group_id(int menu_group_id) {
		this.menu_group_id = menu_group_id;
	}

	public int getReport_group_id() {
		return report_group_id;
	}

	public void setReport_group_id(int report_group_id) {
		this.report_group_id = report_group_id;
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
			
}
