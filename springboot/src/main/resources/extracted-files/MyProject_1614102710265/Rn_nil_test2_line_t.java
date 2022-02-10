package com.realnet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "RN_NIL_TEST2_LINE_T")
public class	Rn_nil_test2_line_t {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "L_ID")
private int l_id;

@Column(name = "L_TEXTFIELD5")
private String 	l_textfield5;

@Column(name = "L_TEXTFIELD6")
private String 	l_textfield6;

@Column(name = "L_TEXTFIELD7")
private String 	l_textfield7;

@Column(name = "L_TEXTFIELD8")
private String 	l_textfield8;

 @ManyToOne(fetch = FetchType.LAZY, optional = false)
 @JoinColumn(name = "rn_nil_test2_header_t_id")
	@JsonBackReference
 private	Rn_nil_test2_header_t rn_nil_test2_header_t;
	
@Column(name = "ATTRIBUTE1")
private String 	attribute1;
@Column(name = "ATTRIBUTE2")
private String 	attribute2;
@Column(name = "ATTRIBUTE3")
private String 	attribute3;
@Column(name = "ATTRIBUTE4")
private String 	attribute4;
@Column(name = "ATTRIBUTE5")
private String 	attribute5;
@Column(name = "ATTRIBUTE6")
private String 	attribute6;
@Column(name = "ATTRIBUTE7")
private String 	attribute7;
@Column(name = "ATTRIBUTE8")
private String 	attribute8;
@Column(name = "ATTRIBUTE9")
private String 	attribute9;
@Column(name = "ATTRIBUTE10")
private String 	attribute10;
@Column(name = "ATTRIBUTE11")
private String 	attribute11;
@Column(name = "ATTRIBUTE12")
private String 	attribute12;
@Column(name = "ATTRIBUTE13")
private String 	attribute13;
@Column(name = "ATTRIBUTE14")
private String 	attribute14;
@Column(name = "ATTRIBUTE15")
private String 	attribute15;
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
	
public int getL_id() {
	return	l_id;
}
public void setL_id(int	l_id) {
	this.l_id=l_id;
}

public String getL_textfield5() {
return	l_textfield5;
}
public void setL_textfield5(String	l_textfield5) {
this.l_textfield5=l_textfield5;
}

public String getL_textfield6() {
return	l_textfield6;
}
public void setL_textfield6(String	l_textfield6) {
this.l_textfield6=l_textfield6;
}

public String getL_textfield7() {
return	l_textfield7;
}
public void setL_textfield7(String	l_textfield7) {
this.l_textfield7=l_textfield7;
}

public String getL_textfield8() {
return	l_textfield8;
}
public void setL_textfield8(String	l_textfield8) {
this.l_textfield8=l_textfield8;
}

public String getAttribute1() {
return	attribute1;
}
public void setAttribute1(String	attribute1) {
this.attribute1 = attribute1;
}

public String getAttribute2() {
return	attribute2;
}
public void setAttribute2(String	attribute2) {
this.attribute2 = attribute2;
}

public String getAttribute3() {
return	attribute3;
}
public void setAttribute3(String	attribute3) {
this.attribute3 = attribute3;
}

public String getAttribute4() {
return	attribute4;
}
public void setAttribute4(String	attribute4) {
this.attribute4 = attribute4;
}

public String getAttribute5() {
return	attribute5;
}
public void setAttribute5(String	attribute5) {
this.attribute5 = attribute5;
}

public String getAttribute6() {
return	attribute6;
}
public void setAttribute6(String	attribute6) {
this.attribute6 = attribute6;
}

public String getAttribute7() {
return	attribute7;
}
public void setAttribute7(String	attribute7) {
this.attribute7 = attribute7;
}

public String getAttribute8() {
return	attribute8;
}
public void setAttribute8(String	attribute8) {
this.attribute8 = attribute8;
}

public String getAttribute9() {
return	attribute9;
}
public void setAttribute9(String	attribute9) {
this.attribute9 = attribute9;
}

public String getAttribute10() {
return	attribute10;
}
public void setAttribute10(String	attribute10) {
this.attribute10 = attribute10;
}

public String getAttribute11() {
return	attribute11;
}
public void setAttribute11(String	attribute11) {
this.attribute11 = attribute11;
}

public String getAttribute12() {
return	attribute12;
}
public void setAttribute12(String	attribute12) {
this.attribute12 = attribute12;
}

public String getAttribute13() {
return	attribute13;
}
public void setAttribute13(String	attribute13) {
this.attribute13 = attribute13;
}

public String getAttribute14() {
return	attribute14;
}
public void setAttribute14(String	attribute14) {
this.attribute14 = attribute14;
}

public String getAttribute15() {
return	attribute15;
}
public void setAttribute15(String	attribute15) {
this.attribute15 = attribute15;
}

public String getFlex1() {
return	flex1;
}
public void setFlex1(String	flex1) {
this.flex1 = flex1;
}

public String getFlex2() {
return	flex2;
}
public void setFlex2(String	flex2) {
this.flex2 = flex2;
}

public String getFlex3() {
return	flex3;
}
public void setFlex3(String	flex3) {
this.flex3 = flex3;
}

public String getFlex4() {
return	flex4;
}
public void setFlex4(String	flex4) {
this.flex4 = flex4;
}

public String getFlex5() {
return	flex5;
}
public void setFlex5(String	flex5) {
this.flex5 = flex5;
}
public	Rn_nil_test2_header_t	getRn_nil_test2_header_t() {
		return	rn_nil_test2_header_t;
	}

	public void setRn_nil_test2_header_t(Rn_nil_test2_header_t rn_nil_test2_header_t) {
		this.rn_nil_test2_header_t = rn_nil_test2_header_t;
	}

 	@Column(name = "CREATED_BY")
	private int created_by;
	@Column(name = "LAST_UPDATED_BY")
	private int last_updated_by;
	@Column(name = "CREATION_DATE")
	private Date creation_date;
	@Column(name = "LAST_UPDATE_DATE")
	private Date last_update_date;
	@Column(name = "ACCOUNT_ID")
	private int account_id;
	
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
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

}