package com.realnet.test_module1.MyProjectModule.model;
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
@Table(name = "MYPROJECTUI_T")
public class	Myprojectui_t
{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "ID")
private int id;

@Column(name = "TEXTFIELD1")
private String 	textfield1;

@Column(name = "TEXTFIELD2")
private String 	textfield2;

@Column(name = "TEXTFIELD3")
private String 	textfield3;

@Column(name = "TEXTFIELD4")
private String 	textfield4;

	public int getId() {
		return	id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getTextfield1() {
		return textfield1;
	}
	public void setTextfield1(String textfield1) {
		this.textfield1 = textfield1;
	}

	public String getTextfield2() {
		return textfield2;
	}
	public void setTextfield2(String textfield2) {
		this.textfield2 = textfield2;
	}

	public String getTextfield3() {
		return textfield3;
	}
	public void setTextfield3(String textfield3) {
		this.textfield3 = textfield3;
	}

	public String getTextfield4() {
		return textfield4;
	}
	public void setTextfield4(String textfield4) {
		this.textfield4 = textfield4;
	}
@Column(name = "EXTN1")
private String 	extn1;
@Column(name = "EXTN2")
private String 	extn2;
@Column(name = "EXTN3")
private String 	extn3;
@Column(name = "EXTN4")
private String 	extn4;
@Column(name = "EXTN5")
private String 	extn5;
@Column(name = "EXTN6")
private String 	extn6;
@Column(name = "EXTN7")
private String 	extn7;
@Column(name = "EXTN8")
private String 	extn8;
@Column(name = "EXTN9")
private String 	extn9;
@Column(name = "EXTN10")
private String 	extn10;
@Column(name = "EXTN11")
private String 	extn11;
@Column(name = "EXTN12")
private String 	extn12;
@Column(name = "EXTN13")
private String 	extn13;
@Column(name = "EXTN14")
private String 	extn14;
@Column(name = "EXTN15")
private String 	extn15;
@Column(name = "FLEX1")
private String 	flex1;
@Column(name = "FLEX2")
private String 	flex2;
@Column(name = "FLEX3")
private String 	flex3;
@Column(name = "FLEX4")
private String 	flex4;
@Column(name = "FLEX5")
private String 	flex5;
public String getExtn1() {
return extn1;
}
public void setExtn1(String	extn1) {

this.extn1=extn1;
}
public String getExtn2() {
return extn2;
}
public void setExtn2(String	extn2) {

this.extn2=extn2;
}
public String getExtn3() {
return extn3;
}
public void setExtn3(String	extn3) {

this.extn3=extn3;
}
public String getExtn4() {
return extn4;
}
public void setExtn4(String	extn4) {

this.extn4=extn4;
}
public String getExtn5() {
return extn5;
}
public void setExtn5(String	extn5) {

this.extn5=extn5;
}
public String getExtn6() {
return extn6;
}
public void setExtn6(String	extn6) {

this.extn6=extn6;
}
public String getExtn7() {
return extn7;
}
public void setExtn7(String	extn7) {

this.extn7=extn7;
}
public String getExtn8() {
return extn8;
}
public void setExtn8(String	extn8) {

this.extn8=extn8;
}
public String getExtn9() {
return extn9;
}
public void setExtn9(String	extn9) {

this.extn9=extn9;
}
public String getExtn10() {
return extn10;
}
public void setExtn10(String	extn10) {

this.extn10=extn10;
}
public String getExtn11() {
return extn11;
}
public void setExtn11(String	extn11) {

this.extn11=extn11;
}
public String getExtn12() {
return extn12;
}
public void setExtn12(String	extn12) {

this.extn12=extn12;
}
public String getExtn13() {
return extn13;
}
public void setExtn13(String	extn13) {

this.extn13=extn13;
}
public String getExtn14() {
return extn14;
}
public void setExtn14(String	extn14) {

this.extn14=extn14;
}
public String getExtn15() {
return extn15;
}
public void setExtn15(String	extn15) {

this.extn15=extn15;
}
public String getFlex1() {
return flex1;
}
public void setFlex1(String	flex1) {

this.flex1=flex1;
}
public String getFlex2() {
return flex2;
}
public void setFlex2(String	flex2) {

this.flex2=flex2;
}
public String getFlex3() {
return flex3;
}
public void setFlex3(String	flex3) {

this.flex3=flex3;
}
public String getFlex4() {
return flex4;
}
public void setFlex4(String	flex4) {

this.flex4=flex4;
}
public String getFlex5() {
return flex5;
}
public void setFlex5(String	flex5) {

this.flex5=flex5;
}
@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATED_AT", nullable = false)
	@LastModifiedDate
	private Date updatedAt;

	@Column(name = "CREATED_BY", updatable = false)
	private Long createdBy;

	@Column(name = "UPDATED_BY")
	private Long updatedBy;

	@Column(name = "ACCOUNT_ID")
	private Long accountId;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
}