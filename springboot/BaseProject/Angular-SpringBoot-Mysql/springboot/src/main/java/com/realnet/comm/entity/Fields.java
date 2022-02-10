package com.realnet.comm.entity;

import java.io.File;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import antlr.collections.List;

@Entity
@Table(name="field")
public class Fields {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String textfield;
	private String textarea;
	private String url;
	
	private String email;
	private String dropdown; 
	private String checkbox;
    private Boolean togglebutton;
    private Date datetime;
    private String autocomplete;
    private String upload_field;
    private Double currency_field;
    private Long contact_field;
    private String multiselect_autocomplete;
    private String multiselect_dropdown;
    //credit
    private Long masked;
    public Fields() {
    
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTextfield() {
		return textfield;
	}
	public void setTextfield(String textfield) {
		this.textfield = textfield;
	}
	public String getTextarea() {
		return textarea;
	}
	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDropdown() {
		return dropdown;
	}
	public void setDropdown(String dropdown) {
		this.dropdown = dropdown;
	}
	public String getCheckbox() {
		return checkbox;
	}
	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}
	public Boolean getTogglebutton() {
		return togglebutton;
	}
	public void setTogglebutton(Boolean togglebutton) {
		this.togglebutton = togglebutton;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getAutocomplete() {
		return autocomplete;
	}
	public void setAutocomplete(String autocomplete) {
		this.autocomplete = autocomplete;
	}
	public String getUpload_field() {
		return upload_field;
	}
	public void setUpload_field(String upload_field) {
		this.upload_field = upload_field;
	}
	public Double getCurrency_field() {
		return currency_field;
	}
	public void setCurrency_field(Double currency_field) {
		this.currency_field = currency_field;
	}
	public Long getContact_field() {
		return contact_field;
	}
	public void setContact_field(Long contact_field) {
		this.contact_field = contact_field;
	}
	public String getMultiselect_autocomplete() {
		return multiselect_autocomplete;
	}
	public void setMultiselect_autocomplete(String multiselect_autocomplete) {
		this.multiselect_autocomplete = multiselect_autocomplete;
	}
	public String getMultiselect_dropdown() {
		return multiselect_dropdown;
	}
	public void setMultiselect_dropdown(String multiselect_dropdown) {
		this.multiselect_dropdown = multiselect_dropdown;
	}
	public Long getMasked() {
		return masked;
	}
	public void setMasked(Long masked) {
		this.masked = masked;
	}
	public Fields(int id, String textfield, String textarea, String url, String email, String dropdown, String checkbox,
			Boolean togglebutton, Date datetime, String autocomplete, String upload_field, Double currency_field,
			Long contact_field, String multiselect_autocomplete, String multiselect_dropdown, Long masked) {
		super();
		this.id = id;
		this.textfield = textfield;
		this.textarea = textarea;
		this.url = url;
		this.email = email;
		this.dropdown = dropdown;
		this.checkbox = checkbox;
		this.togglebutton = togglebutton;
		this.datetime = datetime;
		this.autocomplete = autocomplete;
		this.upload_field = upload_field;
		this.currency_field = currency_field;
		this.contact_field = contact_field;
		this.multiselect_autocomplete = multiselect_autocomplete;
		this.multiselect_dropdown = multiselect_dropdown;
		this.masked = masked;
	}
    
	
}
