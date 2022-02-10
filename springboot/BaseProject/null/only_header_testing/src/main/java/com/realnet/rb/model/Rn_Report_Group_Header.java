package com.realnet.rb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name ="RN_REPORT_GROUP_HEADER_T")
public class Rn_Report_Group_Header {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "course_seq")
	@SequenceGenerator(name = "course_seq", sequenceName = "KOEL_REPORT_GRP_HEAD_S", allocationSize = 1)
	
	@Column(name = "REPORT_GROUP_HEADER_ID")
	private int report_group_header_id;

	@Column(name = "REPORT_NAME")
	private String report_name;
	
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "HEADER_ACTIVE")
	private String header_active;

	@Column(name = "HEADER_START_DATE")
	private Date header_start_date;
	
	@Column(name = "HEADER_END_DATE")
	private Date header_end_date;

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
