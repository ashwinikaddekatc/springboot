package com.realnet.fnd.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;import org.hibernate.annotations.GenericGenerator;import java.util.Date;
@Entity
@Table(name = "RN_DYNAMIC_FORM_T")
public class	Rn_Dynamic_Form
{
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
@GenericGenerator(name = "native", strategy = "native")


	@Column(name = "ID")
	private int 	id;
	
	public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public int getForm_id() {
	return form_id;
}

public void setForm_id(int form_id) {
	this.form_id = form_id;
}

public int getAccount_id() {
	return account_id;
}

public void setAccount_id(int account_id) {
	this.account_id = account_id;
}

public int getForm_version() {
	return form_version;
}

public void setForm_version(int form_version) {
	this.form_version = form_version;
}

public int getCreated_by() {
	return created_by;
}

public void setCreated_by(int created_by) {
	this.created_by = created_by;
}

public Date getCreated_date() {
	return created_date;
}

public void setCreated_date(Date created_date) {
	this.created_date = created_date;
}

public int getUpdated_by() {
	return updated_by;
}

public void setUpdated_by(int updated_by) {
	this.updated_by = updated_by;
}

public Date getUpdated_date() {
	return updated_date;
}

public void setUpdated_date(Date updated_date) {
	this.updated_date = updated_date;
}

public String getComp1() {
	return comp1;
}

public void setComp1(String comp1) {
	this.comp1 = comp1;
}

public String getComp2() {
	return comp2;
}

public void setComp2(String comp2) {
	this.comp2 = comp2;
}

public String getComp3() {
	return comp3;
}

public void setComp3(String comp3) {
	this.comp3 = comp3;
}

public String getComp4() {
	return comp4;
}

public void setComp4(String comp4) {
	this.comp4 = comp4;
}

public String getComp5() {
	return comp5;
}

public void setComp5(String comp5) {
	this.comp5 = comp5;
}

public String getComp6() {
	return comp6;
}

public void setComp6(String comp6) {
	this.comp6 = comp6;
}

public String getComp7() {
	return comp7;
}

public void setComp7(String comp7) {
	this.comp7 = comp7;
}

public String getComp8() {
	return comp8;
}

public void setComp8(String comp8) {
	this.comp8 = comp8;
}

public String getComp9() {
	return comp9;
}

public void setComp9(String comp9) {
	this.comp9 = comp9;
}



public String getComp12() {
	return comp12;
}

public void setComp12(String comp12) {
	this.comp12 = comp12;
}

public String getComp13() {
	return comp13;
}

public void setComp13(String comp13) {
	this.comp13 = comp13;
}

public String getComp14() {
	return comp14;
}

public void setComp14(String comp14) {
	this.comp14 = comp14;
}

public String getComp15() {
	return comp15;
}

public void setComp15(String comp15) {
	this.comp15 = comp15;
}

public String getComp16() {
	return comp16;
}

public void setComp16(String comp16) {
	this.comp16 = comp16;
}

public String getComp17() {
	return comp17;
}

public void setComp17(String comp17) {
	this.comp17 = comp17;
}

public String getComp18() {
	return comp18;
}

public void setComp18(String comp18) {
	this.comp18 = comp18;
}

public String getComp19() {
	return comp19;
}

public void setComp19(String comp19) {
	this.comp19 = comp19;
}

public String getComp20() {
	return comp20;
}

public void setComp20(String comp20) {
	this.comp20 = comp20;
}

public String getComp21() {
	return comp21;
}

public void setComp21(String comp21) {
	this.comp21 = comp21;
}

public String getComp22() {
	return comp22;
}

public void setComp22(String comp22) {
	this.comp22 = comp22;
}

public String getComp23() {
	return comp23;
}

public void setComp23(String comp23) {
	this.comp23 = comp23;
}

public String getComp24() {
	return comp24;
}

public void setComp24(String comp24) {
	this.comp24 = comp24;
}

public String getComp25() {
	return comp25;
}

public void setComp25(String comp25) {
	this.comp25 = comp25;
}

public String getComp26() {
	return comp26;
}

public void setComp26(String comp26) {
	this.comp26 = comp26;
}

public String getComp27() {
	return comp27;
}

public void setComp27(String comp27) {
	this.comp27 = comp27;
}

public String getComp28() {
	return comp28;
}

public void setComp28(String comp28) {
	this.comp28 = comp28;
}

public String getComp29() {
	return comp29;
}

public void setComp29(String comp29) {
	this.comp29 = comp29;
}

public String getComp30() {
	return comp30;
}

public void setComp30(String comp30) {
	this.comp30 = comp30;
}

	@Column(name = "FORM_ID")
	private int 	form_id;
	
	@Column(name = "ACCOUNT_ID")
	private int 	account_id;
	
	@Column(name = "FORM_VERSION")
	private int 	form_version;
	
	@Column(name = "CREATED_BY")
	private int 	created_by;
	
	@Column(name = "CREATED_DATE")
	private Date 	created_date;
	
	@Column(name = "UPDATED_BY")
	private int 	updated_by;
	
	@Column(name = "UPDATED_DATE")
	private Date 	updated_date;
	
	
	@Column(name = "COMP1")
	private String comp1;
	
	
	@Column(name = "COMP2")
	private String comp2;
	
	
	@Column(name = "COMP3")
	private String comp3;
	
	
	@Column(name = "COMP4")
	private String comp4;
	
	
	@Column(name = "COMP5")
	private String comp5;
	
	
	@Column(name = "COMP6")
	private String comp6;
	
	
	@Column(name = "COMP7")
	private String comp7;
	
	
	@Column(name = "COMP8")
	private String comp8;
	
	@Column(name = "COMP9")
	private String comp9;
	
	
	@Column(name = "COMP10")
	private String comp10;
	
	public String getComp10() {
		return comp10;
	}

	public void setComp10(String comp10) {
		this.comp10 = comp10;
	}

	public String getComp11() {
		return comp11;
	}

	public void setComp11(String comp11) {
		this.comp11 = comp11;
	}

	@Column(name = "COMP11")
	private String comp11;
	
	@Column(name = "COMP12")
	private String comp12;
	
	@Column(name = "COMP13")
	private String comp13;
	
	@Column(name = "COMP14")
	private String comp14;
	
	@Column(name = "COMP15")
	private String comp15;
	
	@Column(name = "COMP16")
	private String comp16;
	
	@Column(name = "COMP17")
	private String comp17;
	
	@Column(name = "COMP18")
	private String comp18;
	
	@Column(name = "COMP19")
	private String comp19;
	
	@Column(name = "COMP20")
	private String comp20;
	
	@Column(name = "COMP21")
	private String comp21;
	
	@Column(name = "COMP22")
	private String comp22;
	
	@Column(name = "COMP23")
	private String comp23;
	
	@Column(name = "COMP24")
	private String comp24;
	
	@Column(name = "COMP25")
	private String comp25;
	
	@Column(name = "COMP26")
	private String comp26;
	
	
	@Column(name = "COMP27")
	private String comp27;
	
	@Column(name = "COMP28")
	private String comp28;
	
	@Column(name = "COMP29")
	private String comp29;
	
	@Column(name = "COMP30")
	private String comp30;
	
	
	
	
	
	
	
	
	



}