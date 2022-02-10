package com.realnet.fnd.model;

import java.io.Serializable;
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
@Table(name = "RN_USERS_T")
public class Rn_Users1 implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "course_seq")
	@SequenceGenerator(name = "course_seq", sequenceName = "KOEL_UESRS_S", allocationSize = 1)
	@Column(name = "USER_ID" , updatable = false)
	private int user_id;
	
	@Column(name = "PROFILE_ID" , updatable = false)
	private int profile_id;

	@Column(name = "USER_NAME" , updatable = false)
	private String user_name;	
	
	@Column(name = "ATTRIBUTE1", updatable = false)
	private String source;	

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_TYPE", updatable = false)
	private String user_type;
	
	@Column(name = "FIRST_NAME", updatable = false)
	private String first_name;
	
	@Column(name = "MIDDLE_NAME", updatable = false)
	private String middle_name;

	@Column(name = "LAST_NAME", updatable = false)
	private String last_name;

	@Column(name = "CONTACT_NUMBER", updatable = false)
	private String contact_number;
	
	@Column(name = "EMAIL_ADDRESS", updatable = false)
	private String email_address;

	@Column(name = "START_DATE", updatable = false)
	private Date start_date;
	
	@Column(name = "END_DATE", updatable = false)
	private Date end_date;

	@Column(name = "IS_ACTIVE", updatable = false)
	private String is_active;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "LAST_LOGIN", updatable = false)
	private Date last_login= new java.sql.Date(new java.util.Date().getTime());

	@Column(name = "CREATED_BY", updatable = false)
	private int created_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "CREATION_DATE" , updatable = false)
	private Date creation_date= new java.sql.Date(new java.util.Date().getTime());

	@Column(name = "LAST_UPDATED_BY")
	private int last_updated_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "LAST_UPDATE_DATE")
	private Date last_update_date= new java.sql.Date(new java.util.Date().getTime());

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProfile_id() {
		return profile_id;
	}

	public void setProfile_id(int profile_id) {
		this.profile_id = profile_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getMiddle_name() {
		return middle_name;
	}

	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getContact_number() {
		return contact_number;
	}

	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getIs_active() {
		return is_active;
	}

	public void setIs_active(String is_active) {
		this.is_active = is_active;
	}

	public Date getLast_login() {
		return last_login;
	}

	public void setLast_login(Date last_login) {
		this.last_login = last_login;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	
}
