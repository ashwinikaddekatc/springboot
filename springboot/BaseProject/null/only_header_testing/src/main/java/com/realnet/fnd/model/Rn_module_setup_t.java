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
@Table(name = "RN_MODULE_SETUP_T")
public class Rn_module_setup_t {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "ID")
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "MODULE_NAME")
	private String module_name;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "MODULE_PREFIX")
	private String module_prefix;
	@Column(name = "PROJECT_ID")
	private int project_id;

	@Column(name = "COPY_TO")
	private String copy_to;

	public Rn_module_setup_t(int data, String data1) {
		this.id = data;
		this.module_name = data1;
	}

	@Column(name = "Technology_stack")
	private String technology_stack;

	public String getTechnology_stack() {
		return technology_stack;
	}

	public void setTechnology_stack(String technology_stack) {
		this.technology_stack = technology_stack;
	}

	public Rn_module_setup_t() {
		// TODO Auto-generated constructor stub
	}

	public String getCopy_to() {
		return copy_to;
	}

	public void setCopy_to(String copy_to) {
		this.copy_to = copy_to;
	}

	public String getModule_name() {
		return module_name;
	}

	public void setModule_name(String module_name) {
		this.module_name = module_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModule_prefix() {
		return module_prefix;
	}

	public void setModule_prefix(String module_prefix) {
		this.module_prefix = module_prefix;
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	@Column(name = "ATTRIBUTE1")
	private String attribute1;
	@Column(name = "ATTRIBUTE2")
	private String attribute2;
	@Column(name = "ATTRIBUTE3")
	private String attribute3;
	@Column(name = "ATTRIBUTE4")
	private String attribute4;
	@Column(name = "ATTRIBUTE5")
	private String attribute5;
	@Column(name = "ATTRIBUTE6")
	private String attribute6;
	@Column(name = "ATTRIBUTE7")
	private String attribute7;
	@Column(name = "ATTRIBUTE8")
	private String attribute8;
	@Column(name = "ATTRIBUTE9")
	private String attribute9;
	@Column(name = "ATTRIBUTE10")
	private String attribute10;
	@Column(name = "ATTRIBUTE11")
	private String attribute11;
	@Column(name = "ATTRIBUTE12")
	private String attribute12;
	@Column(name = "ATTRIBUTE13")
	private String attribute13;
	@Column(name = "ATTRIBUTE14")
	private String attribute14;
	@Column(name = "ATTRIBUTE15")
	private String attribute15;
	@Column(name = "FLEX1")
	private String flex1;
	@Column(name = "FLEX2")
	private String flex2;
	@Column(name = "FLEX3")
	private String flex3;
	@Column(name = "FLEX4")
	private String flex4;
	@Column(name = "FLEX5")
	private String flex5;

	public String getAttribute1() {
		return attribute1;
	}

	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}

	public String getAttribute2() {
		return attribute2;
	}

	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}

	public String getAttribute3() {
		return attribute3;
	}

	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}

	public String getAttribute4() {
		return attribute4;
	}

	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}

	public String getAttribute5() {
		return attribute5;
	}

	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}

	public String getAttribute6() {
		return attribute6;
	}

	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}

	public String getAttribute7() {
		return attribute7;
	}

	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}

	public String getAttribute8() {
		return attribute8;
	}

	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}

	public String getAttribute9() {
		return attribute9;
	}

	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}

	public String getAttribute10() {
		return attribute10;
	}

	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}

	public String getAttribute11() {
		return attribute11;
	}

	public void setAttribute11(String attribute11) {
		this.attribute11 = attribute11;
	}

	public String getAttribute12() {
		return attribute12;
	}

	public void setAttribute12(String attribute12) {
		this.attribute12 = attribute12;
	}

	public String getAttribute13() {
		return attribute13;
	}

	public void setAttribute13(String attribute13) {
		this.attribute13 = attribute13;
	}

	public String getAttribute14() {
		return attribute14;
	}

	public void setAttribute14(String attribute14) {
		this.attribute14 = attribute14;
	}

	public String getAttribute15() {
		return attribute15;
	}

	public void setAttribute15(String attribute15) {
		this.attribute15 = attribute15;
	}

	public String getFlex1() {
		return flex1;
	}

	public void setFlex1(String flex1) {
		this.flex1 = flex1;
	}

	public String getFlex2() {
		return flex2;
	}

	public void setFlex2(String flex2) {
		this.flex2 = flex2;
	}

	public String getFlex3() {
		return flex3;
	}

	public void setFlex3(String flex3) {
		this.flex3 = flex3;
	}

	public String getFlex4() {
		return flex4;
	}

	public void setFlex4(String flex4) {
		this.flex4 = flex4;
	}

	public String getFlex5() {
		return flex5;
	}

	public void setFlex5(String flex5) {
		this.flex5 = flex5;
	}

	@Column(name = "CREATED_BY")
	private int created_by;
	@Column(name = "LAST_UPDATED_BY")
	private int last_updated_by;
	@Column(name = "CREATION_DATE")
	private Date creation_date;
	@Column(name = "LAST_UPDATE_DATE")
	private Date last_update_date;

	public int getCreated_by() {
		return created_by;
	}

	public void setCreated_by(int created_by) {
		this.created_by = created_by;
	}

	public int getLast_updated_by() {
		return last_updated_by;
	}

	public void setLast_updated_by(int last_updated_by) {
		this.last_updated_by = last_updated_by;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public Date getLast_update_date() {

		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.last_update_date = last_update_date;
	}

	@Column(name = "ACCOUNT_ID")
	private int account_id;

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	public Rn_module_setup_t(int id, String module_name, String description, String module_prefix, int project_id,
			String copy_to, String technology_stack, String attribute1, String attribute2, String attribute3,
			String attribute4, String attribute5, String attribute6, String attribute7, String attribute8,
			String attribute9, String attribute10, String attribute11, String attribute12, String attribute13,
			String attribute14, String attribute15, String flex1, String flex2, String flex3, String flex4,
			String flex5, int created_by, int last_updated_by, Date creation_date, Date last_update_date,
			int account_id) {
		super();
		this.id = id;
		this.module_name = module_name;
		this.description = description;
		this.module_prefix = module_prefix;
		this.project_id = project_id;
		this.copy_to = copy_to;
		this.technology_stack = technology_stack;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
		this.attribute6 = attribute6;
		this.attribute7 = attribute7;
		this.attribute8 = attribute8;
		this.attribute9 = attribute9;
		this.attribute10 = attribute10;
		this.attribute11 = attribute11;
		this.attribute12 = attribute12;
		this.attribute13 = attribute13;
		this.attribute14 = attribute14;
		this.attribute15 = attribute15;
		this.flex1 = flex1;
		this.flex2 = flex2;
		this.flex3 = flex3;
		this.flex4 = flex4;
		this.flex5 = flex5;
		this.created_by = created_by;
		this.last_updated_by = last_updated_by;
		this.creation_date = creation_date;
		this.last_update_date = last_update_date;
		this.account_id = account_id;
	}

	// don't delete
	public Rn_module_setup_t(String module_name, String description, String module_prefix, int project_id,
			String copy_to, String technology_stack, String attribute1, String attribute2, String attribute3,
			String attribute4, String attribute5, String attribute6, String attribute7, String attribute8,
			String attribute9, String attribute10, String attribute11, String attribute12, String attribute13,
			String attribute14, String attribute15, String flex1, String flex2, String flex3, String flex4,
			String flex5, int created_by, int last_updated_by, Date creation_date, Date last_update_date,
			int account_id) {
		super();
		this.module_name = module_name;
		this.description = description;
		this.module_prefix = module_prefix;
		this.project_id = project_id;
		this.copy_to = copy_to;
		this.technology_stack = technology_stack;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
		this.attribute6 = attribute6;
		this.attribute7 = attribute7;
		this.attribute8 = attribute8;
		this.attribute9 = attribute9;
		this.attribute10 = attribute10;
		this.attribute11 = attribute11;
		this.attribute12 = attribute12;
		this.attribute13 = attribute13;
		this.attribute14 = attribute14;
		this.attribute15 = attribute15;
		this.flex1 = flex1;
		this.flex2 = flex2;
		this.flex3 = flex3;
		this.flex4 = flex4;
		this.flex5 = flex5;
		this.created_by = created_by;
		this.last_updated_by = last_updated_by;
		this.creation_date = creation_date;
		this.last_update_date = last_update_date;
		this.account_id = account_id;
	}

	@Override
	public String toString() {
		return "Rn_module_setup_t [id=" + id + ", module_name=" + module_name + ", description=" + description
				+ ", module_prefix=" + module_prefix + ", project_id=" + project_id + ", copy_to=" + copy_to
				+ ", technology_stack=" + technology_stack + ", attribute1=" + attribute1 + ", attribute2=" + attribute2
				+ ", attribute3=" + attribute3 + ", attribute4=" + attribute4 + ", attribute5=" + attribute5
				+ ", attribute6=" + attribute6 + ", attribute7=" + attribute7 + ", attribute8=" + attribute8
				+ ", attribute9=" + attribute9 + ", attribute10=" + attribute10 + ", attribute11=" + attribute11
				+ ", attribute12=" + attribute12 + ", attribute13=" + attribute13 + ", attribute14=" + attribute14
				+ ", attribute15=" + attribute15 + ", flex1=" + flex1 + ", flex2=" + flex2 + ", flex3=" + flex3
				+ ", flex4=" + flex4 + ", flex5=" + flex5 + ", created_by=" + created_by + ", last_updated_by="
				+ last_updated_by + ", creation_date=" + creation_date + ", last_update_date=" + last_update_date
				+ ", account_id=" + account_id + "]";
	}

}