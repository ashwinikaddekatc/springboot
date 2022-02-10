package com.realnet.comm.GaneshMod.entity;

import java.util.Date;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;



@Entity
public class Ganesh_uisales{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_id")
	private int sid;
	
    private String label1;
    private String label2;
    private String label3;
    private String label4;


	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getLabel1() {
		return label1;
	}

	public void setLabel1(String label1) {
		this.label1 = label1;
	}
	public String getLabel2() {
		return label2;
	}

	public void setLabel2(String label2) {
		this.label2 = label2;
	}
	public String getLabel3() {
		return label3;
	}

	public void setLabel3(String label3) {
		this.label3 = label3;
	}
	public String getLabel4() {
		return label4;
	}

	public void setLabel4(String label4) {
		this.label4 = label4;
	}
	
}