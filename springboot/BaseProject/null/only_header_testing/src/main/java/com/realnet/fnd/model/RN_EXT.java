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
@Table(name = "RN_EXT_T")

public class RN_EXT 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "course_seq", sequenceName = "KOEL_MENU_REGISTER_S", allocationSize = 1)
	
	@Column(name = "ID")
	private int id;
	
	@Column(name = "JSP_NAME")
	private String jsp_name;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJsp_name() {
		return jsp_name;
	}

	public void setJsp_name(String jsp_name)
	{
		this.jsp_name = jsp_name;
	}

	
	
	
	
			
}

