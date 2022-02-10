package com.realnet.Module.salesnew.entity;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.realnet.Module.salesnew.entity.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.realnet.fnd.entity.Rn_AuditEntity;

import lombok.Data;

@Data
@Entity
public class Hlnewauthor  extends Rn_AuditEntity{	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;
    private String label1;
    private String label2;
    private String label3;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Hlnewbook> book;

	
}