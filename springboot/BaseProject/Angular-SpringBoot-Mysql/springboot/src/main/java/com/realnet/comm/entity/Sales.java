package com.realnet.comm.entity;

import lombok.*;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModelProperty;

//@Data
@Entity
@Table(name = "sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	private String name;
    private String department ;
    private Date joining_date;
    private String status;
   
    @OneToMany(mappedBy = "sales",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<SalesPerson> salesPerson;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<SalesPerson> getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(Set<SalesPerson> salesPerson) {
		this.salesPerson = salesPerson;
	}

	public Sales(int id, String name, String department, Date joining_date, String status,
			Set<SalesPerson> salesPerson) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.joining_date = joining_date;
		this.status = status;
		this.salesPerson = salesPerson;
	}

	public Sales() {
		super();
		// TODO Auto-generated constructor stub
	}

}
