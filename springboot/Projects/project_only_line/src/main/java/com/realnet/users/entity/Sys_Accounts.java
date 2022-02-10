package com.realnet.users.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude="users")
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "sys_accounts")
public class Sys_Accounts {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "workspace")
	private String workspace;

	@Column(name = "gst_no")
	private String gstNumber;
	
	@OneToMany(mappedBy = "sys_account", targetEntity = User.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<User> users;

}
