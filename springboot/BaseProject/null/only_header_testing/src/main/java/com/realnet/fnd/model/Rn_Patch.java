   package com.realnet.fnd.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;import org.hibernate.annotations.GenericGenerator;import java.util.Date;
@Entity
@Table(name = "RN_PATCH_TABLE_T")
public class	Rn_Patch
{
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
@GenericGenerator(name = "native", strategy = "native")


	@Column(name = "ID")
	private int 	id;
	
	@Column(name = "FILENAME")
	private String 	filename;
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "LOCATION")
	private String 	location;
	
	@Column(name = "STATUS")
	private String 	status;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}


	@Column(name = "PROJECT_NAME")
	private String 	project_name;
	
	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getComp_type() {
		return comp_type;
	}

	public void setComp_type(String comp_type) {
		this.comp_type = comp_type;
	}

	@Column(name = "COMP_TYPE")
	private String 	comp_type;
	
	
	@Column(name = "OBJECT_TYPE")
	private String 	object_type;

	public String getObject_type() {
		return object_type;
	}

	public void setObject_type(String object_type) {
		this.object_type = object_type;
	}

}