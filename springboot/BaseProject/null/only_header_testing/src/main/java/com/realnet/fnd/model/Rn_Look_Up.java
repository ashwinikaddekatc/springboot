package com.realnet.fnd.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RN_LOOKUP_TYPE_T")
public class Rn_Look_Up 
{
	@Id
	@Column(name = "LOOKUP_TYPE")
	private String lookup_type;

	//@DateTimeFormat(pattern = "dd/mm/yyyy")

	@Column(name = "ACTIVE_START_DATE")
	private Date active_start_date;

	//@DateTimeFormat(pattern = "dd/mm/yyyy")

	@Column(name = "ACTIVE_END_DATE")
	private Date active_end_date;
		
	@Column(name = "ENABLED_FLAG")
	private String enabled_flag;
	
	@Column(name = "CREATED_BY",updatable=false)
	private int created_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")

	@Column(name = "CREATION_DATE",updatable=false)
	private Date creation_date= new java.sql.Date(new java.util.Date().getTime());

	
	@Column(name = "LAST_UPDATED_BY")
	private int last_updated_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")

	@Column(name = "LAST_UPDATE_DATE")
	private Date last_update_date = new java.sql.Date(new java.util.Date().getTime());

	public String getLookup_type() {
		return lookup_type;
	}

	public void setLookup_type(String lookup_type) {
		this.lookup_type = lookup_type;
	}

	public Date getActive_start_date() {
		return active_start_date;
	}

	public void setActive_start_date(Date active_start_date) {
		this.active_start_date = active_start_date;
	}

	public Date getActive_end_date() {
		return active_end_date;
	}

	public void setActive_end_date(Date active_end_date) {
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
