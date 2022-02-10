package com.realnet.Module.salesnew.entity;


import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Flf_uisales {	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int sid;
    private String tech_stack;
    private String object_type;
    private String sub_object_type;
	
	
	@OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Flf_uisalesperson> salesperson;


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	public String getTech_stack() {
		return tech_stack;
	}

	public void setTech_stack(String tech_stack) {
		this.tech_stack = tech_stack;
	}
	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}
	public String getSub_object_type() {
		return sub_object_type;
	}

	public void setSub_object_type(String sub_object_type) {
		this.sub_object_type = sub_object_type;
	}
	public  Set<Flf_uisalesperson>  getFlf_uisalesperson() {
		return salesperson;
	}

	public void setFlf_uisalesperson(Set<Flf_uisalesperson>  Flf_uisalesperson) {
		this.salesperson = Flf_uisalesperson;
	}



	
	
	
}