package com.realnet.fnd.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "RN_LOOKUP_VALUES_T")

public class Rn_Lookup_Values 
{
	@Id
	@Column(name = "LOOKUP_CODE")
	private String lookup_code;
	
	@Column(name = "MEANING")
	private String meaning;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "LOOKUP_TYPE")
	private String lookup_type;
	
	
	@Column(name = "PATIENT_COUNTRY")
	private String patient_country;
	
	@Column(name = "DROP_VALUE")
	private String drop_value;
	
	public String getDrop_value() {
		return drop_value;
	}
	public void setDrop_value(String drop_value) {
		this.drop_value = drop_value;
	}
	@Column(name = "STATE_NAME")
	private String state_name;
	
	public String getState_name() {
		return state_name;
	}
	public void setState_name(String state_name) {
		this.state_name = state_name;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	@Column(name = "CITY_NAME")
	private String city_name;
	
	
	@Column(name = "ZIP_CODE")
	private String zip_code;
	
	
	@Column(name = "COUNTRY_ID")
	private int country_id;
	
	
	
	public int getCountry_id() {
		return country_id;
	}
	public void setCountry_id(int data2) {
		this.country_id = data2;
	}
	public String getPatient_country() {
		return patient_country;
	}
	public void setPatient_country(String patient_country) {
		this.patient_country = patient_country;
	}
	/*@Column(name = "TABLE_NAME")
	private String table_name;

	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}*/
	@Column(name = "ACTIVE_START_DATE")
	private Date active_start_date;

	@Column(name = "ACTIVE_END_DATE")
	private Date active_end_date;

	@Column(name = "ENABLED_FLAG")
	private String enabled_flag;

	@Column(name = "CREATED_BY")
	private int created_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "CREATION_DATE")
	private Date creation_date= new java.sql.Date(new java.util.Date().getTime());

	@Column(name = "LAST_UPDATED_BY")
	private int last_updated_by;

	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@Column(name = "LAST_UPDATE_DATE")
	private Date last_update_date= new java.sql.Date(new java.util.Date().getTime());

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

