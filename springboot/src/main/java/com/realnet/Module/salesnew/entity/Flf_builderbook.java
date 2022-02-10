package com.realnet.Module.salesnew.entity;

import javax.persistence.Column;
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
public class Flf_builderbook{	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    private String filetype;
    private String formtype;
    private String field_type;
    private String operation_type;
    
    @Column(length = 8000)
    private String code;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JsonBackReference
	private Flf_builderauthor author;

	
}