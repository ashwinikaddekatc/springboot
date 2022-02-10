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

public class Rn_Hierarchy_Setup1 
{
	
	
	private int wf_hierarchy_id;

	private int user_id;	

	private String approver_level;
	private int apr_order;

	private int to_value;

	private int created_by;


	private Date creation_date= new java.sql.Date(new java.util.Date().getTime());

	private int last_updated_by;

	private Date last_update_date= new java.sql.Date(new java.util.Date().getTime());

	private String attribute1;

	private String attribute2;
	private String attribute3;
	
	private String user_name;


	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getWf_hierarchy_id() {
		return wf_hierarchy_id;
	}
	public void setWf_hierarchy_id(int wf_hierarchy_id) {
		this.wf_hierarchy_id = wf_hierarchy_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getApprover_level() {
		return approver_level;
	}
	public void setApprover_level(String approver_level) {
		this.approver_level = approver_level;
	}
	
	public int getApr_order() {
		return apr_order;
	}
	public void setApr_order(int apr_order) {
		this.apr_order = apr_order;
	}
	public int getTo_value() {
		return to_value;
	}
	public void setTo_value(int to_value) {
		this.to_value = to_value;
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

	
}

