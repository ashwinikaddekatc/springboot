package com.realnet.Module.salesnew.entity;


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
public class Flf_builderauthor  extends Rn_AuditEntity{	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
    private String techstack;
    private String object_type;
    private String sub_object_type;
    private String isbuild;
	
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Flf_builderbook> book;

	
}