package com.realnet.comm.testmodule30.entity;

import java.util.Date;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import com.realnet.fnd.entity.Rn_AuditEntity;


@Entity
@Table(name = "sales")
public class Hggsales{	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sales_id")
	private int sid;
	
dddddddddddddddddddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffddddddddddddddddddddddd

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

dddddddddddddddddddddddddddddddddddddddddddddffffffffffffffffffffffffffffffffffffffffddddddddddddddddddddddd	
}