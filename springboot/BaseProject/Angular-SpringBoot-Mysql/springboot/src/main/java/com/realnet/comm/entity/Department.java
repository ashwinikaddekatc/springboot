package com.realnet.comm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.realnet.fnd.entity.Rn_ExtensionEntity;




@Entity
@Table(name = "department")
public class Department extends Rn_ExtensionEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;
	private String Dname;
	private String Dhead;
	private Long Dcontact;
	private Integer EmpCount;
	public Department() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	public String getDhead() {
		return Dhead;
	}
	public void setDhead(String dhead) {
		Dhead = dhead;
	}
	public Long getDcontact() {
		return Dcontact;
	}
	public void setDcontact(Long dcontact) {
		Dcontact = dcontact;
	}
	public Integer getEmpCount() {
		return EmpCount;
	}
	public void setEmpCount(Integer empCount) {
		EmpCount = empCount;
	}
	public Department(Integer id, String dname, String dhead, Long dcontact, Integer empCount) {
		super();
		this.id = id;
		Dname = dname;
		Dhead = dhead;
		Dcontact = dcontact;
		EmpCount = empCount;
	}
	
}
 