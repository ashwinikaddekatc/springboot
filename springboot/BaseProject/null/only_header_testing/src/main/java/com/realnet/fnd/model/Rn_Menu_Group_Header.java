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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "RN_MENU_GROUP_HEADER_T")
public class Rn_Menu_Group_Header{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GenericGenerator(name = "native", strategy = "native")

	//@SequenceGenerator(name = "course_seq", sequenceName = "KOEL_MENU_GROUP_HEAD_S", allocationSize = 1)
	
	@Column(name = "MENU_GROUP_HEADER_ID")
	private int menu_group_header_id;

	@Column(name = "MENU_NAME")
	private String menu_name;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "HEADER_ACTIVE")
	private String header_active;

	@Column(name = "HEADER_START_DATE")
	private Date header_start_date;
	
	@Column(name = "HEADER_END_DATE")
	private Date header_end_date;

	public int getMenu_group_header_id() {
		return menu_group_header_id;
	}

	public void setMenu_group_header_id(int menu_group_header_id) {
		this.menu_group_header_id = menu_group_header_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHeader_active() {
		return header_active;
	}

	public void setHeader_active(String header_active) {
		this.header_active = header_active;
	}

	public Date getHeader_start_date() {
		return header_start_date;
	}

	public void setHeader_start_date(Date header_start_date) {
		this.header_start_date = header_start_date;
	}

	public Date getHeader_end_date() {
		return header_end_date;
	}

	public void setHeader_end_date(Date header_end_date) {
		this.header_end_date = header_end_date;
	}
}
