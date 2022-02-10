package com.realnet.comm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
@Data
@Entity
@Table(name = "Sales_person")
public class SalesPerson {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="NAME")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="sales_id",nullable=false)
	@JsonBackReference
	private Sales sales;
}







//package com.realnet.comm.entity;
//
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//
//import lombok.Data;
//@Data
//@Entity
//@Table(name = "Sales_Person")
//public class SalesPerson {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private int id;
//	
//	
//	//getter setter
//	private String name;
//	
//	@ManyToOne(fetch = FetchType.LAZY,optional = false)
//	@JoinColumn(name = "sales_fk" )
//	@JsonBackReference
//	private Sales sales;
//	
////	public SalesPerson(){
////		super();
////	}
//	//@ManyToOne(fetch = FetchType.LAZY)
//	//@JsonBackReference
//	
//
//	
////	//getter setter
////	public int getId() {
////		return id;
////	}
////
////	public void setId(int id) {
////		this.id = id;
////	}
////
////	public String getName() {
////		return name;
////	}
////
////	public void setName(String name) {
////		this.name = name;
////	}
//	
//	
//}
