package com.realnet.fnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_DEPENDENT")
public class Rn_Test_Dependent 
{
	
	@Id
	@Column(name = "ID")
	private String id;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVal_a() {
		return val_a;
	}

	public void setVal_a(String val_a) {
		this.val_a = val_a;
	}

	public String getVal_b() {
		return val_b;
	}

	public void setVal_b(String val_b) {
		this.val_b = val_b;
	}

	@Column(name = "VAL_A")
	private String val_a;
	
	@Column(name = "VAL_B")
	private String val_b;

}


