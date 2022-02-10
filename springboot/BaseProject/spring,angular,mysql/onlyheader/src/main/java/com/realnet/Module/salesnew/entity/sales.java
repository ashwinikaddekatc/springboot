package com.realnet.comm.entity;

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
public class sales extends Rn_AuditEntity  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
