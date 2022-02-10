package com.realnet.fnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import java.util.Date;

@Entity
@Table(name = "RN_PROJECT_SETUP_T")
public class Rn_Project_Setup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private int id;

	public Rn_Project_Setup(int data, String data1) {
		this.id = data;
		this.project_name = data1;
	}

	public Rn_Project_Setup() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "PROJECT_NAME")
	private String project_name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "MODULE_ID")
	private int module_id;

	@Column(name = "COPY_TO")
	private String copy_to;

	@Column(name = "Technology_stack")
	private String technology_stack;

	public String getTechnology_stack() {
		return technology_stack;
	}

	public void setTechnology_stack(String technology_stack) {
		this.technology_stack = technology_stack;
	}

	public String getCopy_to() {
		return copy_to;
	}

	public void setCopy_to(String copy_to) {
		this.copy_to = copy_to;
	}

	public int getModule_id() {
		return module_id;
	}

	public void setModule_id(int module_id) {
		this.module_id = module_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "PROJECT_PREFIX")
	private String project_prefix;

	public String getProject_prefix() {
		return project_prefix;
	}

	public void setProject_prefix(String project_prefix) {
		this.project_prefix = project_prefix;
	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}

	public String getDb_user() {
		return db_user;
	}

	public void setDb_user(String db_user) {
		this.db_user = db_user;
	}

	public String getDb_password() {
		return db_password;
	}

	public void setDb_password(String db_password) {
		this.db_password = db_password;
	}

	public String getPort_no() {
		return port_no;
	}

	public void setPort_no(String port_no) {
		this.port_no = port_no;
	}

	@Column(name = "DB_NAME")
	private String db_name;

	@Column(name = "DB_USER")
	private String db_user;

	@Column(name = "DB_PASSWORD")
	private String db_password;

	@Column(name = "PORT_NO")
	private String port_no;
	
	
	

	public Rn_Project_Setup(int id, String project_name, String description, int module_id, String copy_to,
			String technology_stack, String project_prefix, String db_name, String db_user, String db_password,
			String port_no) {
		super();
		this.id = id;
		this.project_name = project_name;
		this.description = description;
		this.module_id = module_id;
		this.copy_to = copy_to;
		this.technology_stack = technology_stack;
		this.project_prefix = project_prefix;
		this.db_name = db_name;
		this.db_user = db_user;
		this.db_password = db_password;
		this.port_no = port_no;
	}

	// don't delete
	public Rn_Project_Setup(String project_name, String description, int module_id, String copy_to,
			String technology_stack, String project_prefix, String db_name, String db_user, String db_password,
			String port_no) {
		super();
		this.project_name = project_name;
		this.description = description;
		this.module_id = module_id;
		this.copy_to = copy_to;
		this.technology_stack = technology_stack;
		this.project_prefix = project_prefix;
		this.db_name = db_name;
		this.db_user = db_user;
		this.db_password = db_password;
		this.port_no = port_no;
	}
	
	

}