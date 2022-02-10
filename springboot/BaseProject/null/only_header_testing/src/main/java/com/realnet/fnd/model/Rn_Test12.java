package com.realnet.fnd.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;import org.hibernate.annotations.GenericGenerator;
@Entity

@Table(name = "TEST12")
public class	Rn_Test12
{

@GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
@GenericGenerator(name = "native", strategy = "native")

@Id
@Column(name = "ID")
private int id;


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirst_name() {
	return first_name;
}

public void setFirst_name(String first_name) {
	this.first_name = first_name;
}

public String getMiddle_name() {
	return middle_name;
}

public void setMiddle_name(String middle_name) {
	this.middle_name = middle_name;
}

public String getSirname() {
	return sirname;
}

public void setSirname(String sirname) {
	this.sirname = sirname;
}

@Column(name = "first_name")
private String first_name;


@Column(name = "middle_name")
private String middle_name;

@Column(name = "sirname")
private String 	sirname;



}