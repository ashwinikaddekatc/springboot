package com.realnet.comm.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.realnet.fnd.entity.Rn_AuditEntity;

import lombok.Data;

@Data
@Entity
public class author extends Rn_AuditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String afname;
	private String lname;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<book> book;

	
}
