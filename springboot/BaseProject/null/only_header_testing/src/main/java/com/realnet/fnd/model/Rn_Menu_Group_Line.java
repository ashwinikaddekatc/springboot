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
@Table(name = "RN_MENU_GROUP_LINES_T")
public class Rn_Menu_Group_Line{
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GenericGenerator(name = "native", strategy = "native")
	//@SequenceGenerator(name = "course_seq", sequenceName = "KOEL_MENU_GROUP_LINE_S", allocationSize = 1)
	
	@Column(name = "MENU_GROUP_LINE_ID")
	private int menu_group_line_id;
	
	
	@Column(name = "MENU_GROUP_HEADER_ID")
	private int menu_group_header_id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ACTIVE")
	private String active;

	@Column(name = "START_DATE")
	private Date start_date;
	
	@Column(name = "END_DATE")
	private Date end_date;
	
	@Column(name = "MENU_ID")
	private int menu_id;
	
	
	public Rn_Menu_Group_Line(){}
	
	public Rn_Menu_Group_Line(int menu_id, String menu_name){
		this.menu_id=menu_id;
		this.name=menu_name;
	}
	

	public int getMenu_group_line_id() {
		return menu_group_line_id;
	}

	public void setMenu_group_line_id(int menu_group_line_id) {
		this.menu_group_line_id = menu_group_line_id;
	}

	public int getMenu_group_header_id() {
		return menu_group_header_id;
	}

	public void setMenu_group_header_id(int menu_group_header_id) {
		this.menu_group_header_id = menu_group_header_id;
	}	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
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

	public int getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	
}
