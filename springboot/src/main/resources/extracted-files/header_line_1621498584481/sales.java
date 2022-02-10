package com.realnet.comm.entity;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.realnet.fnd.entity.Rn_AuditEntity;


@Entity
public class sales extends Rn_AuditEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_id")
	private int sid;
	@Column(name = "sales_name")
	private String sname; //text
	@Column(name = "sales_department")
	private String department; //dropdown
	
	
	
	
	@OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<salesperson> salesperson;


	public int getSid() {
		return sid;
	}


	public void setSid(int sid) {
		this.sid = sid;
	}


	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public Set<salesperson> getSalesperson() {
		return salesperson;
	}


	public void setSalesperson(Set<salesperson> salesperson) {
		this.salesperson = salesperson;
	}


	
	
	
}
