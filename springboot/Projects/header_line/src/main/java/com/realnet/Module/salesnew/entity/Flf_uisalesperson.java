package com.realnet.Module.salesnew.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Flf_uisalesperson{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Pid;
    private String operation_type;
    private String field_type;
    private String Code;
    private String file_type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonBackReference
	private Flf_uisales sales;
}