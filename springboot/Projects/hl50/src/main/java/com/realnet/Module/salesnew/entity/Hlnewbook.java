package com.realnet.Module.salesnew.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.realnet.Module.salesnew.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Hlnewbook{	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Integer id;

    private String column5;
    private String column6;
    private String column7;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonBackReference
	private Hlnewauthor author;

	
}