"package com.realnet." + module_name + ".model;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.FetchType;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.JoinColumn;" + "\r\n" + 
"import javax.persistence.ManyToOne;" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonBackReference;" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"RN_HL_LINE_T\")" + "\r\n" + 
"public class Rn_hl_line_t {" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.IDENTITY)" + "\r\n" + 
"	@Column(name = \"L_ID\")" + "\r\n" + 
"	private int l_id;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"L_TEXTFIELD5\")" + "\r\n" + 
"	private String l_textfield5;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"L_TEXTFIELD6\")" + "\r\n" + 
"	private String l_textfield6;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"L_TEXTFIELD7\")" + "\r\n" + 
"	private String l_textfield7;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"L_TEXTFIELD8\")" + "\r\n" + 
"	private String l_textfield8;" + "\r\n" + 
"" + "\r\n" + 
"	@ManyToOne(fetch = FetchType.LAZY, optional = false)" + "\r\n" + 
"	@JoinColumn(name = \"rn_hl_header_t_id\")" + "\r\n" + 
"	@JsonBackReference" + "\r\n" + 
"	private Rn_hl_header_t rn_hl_header_t;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE1\")" + "\r\n" + 
"	private String attribute1;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE2\")" + "\r\n" + 
"	private String attribute2;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE3\")" + "\r\n" + 
"	private String attribute3;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE4\")" + "\r\n" + 
"	private String attribute4;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE5\")" + "\r\n" + 
"	private String attribute5;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE6\")" + "\r\n" + 
"	private String attribute6;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE7\")" + "\r\n" + 
"	private String attribute7;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE8\")" + "\r\n" + 
"	private String attribute8;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE9\")" + "\r\n" + 
"	private String attribute9;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE10\")" + "\r\n" + 
"	private String attribute10;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE11\")" + "\r\n" + 
"	private String attribute11;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE12\")" + "\r\n" + 
"	private String attribute12;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE13\")" + "\r\n" + 
"	private String attribute13;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE14\")" + "\r\n" + 
"	private String attribute14;" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE15\")" + "\r\n" + 
"	private String attribute15;" + "\r\n" + 
"	@Column(name = \"FLEX1\")" + "\r\n" + 
"	private String flex1;" + "\r\n" + 
"	@Column(name = \"FLEX2\")" + "\r\n" + 
"	private String flex2;" + "\r\n" + 
"	@Column(name = \"FLEX3\")" + "\r\n" + 
"	private String flex3;" + "\r\n" + 
"	@Column(name = \"FLEX4\")" + "\r\n" + 
"	private String flex4;" + "\r\n" + 
"	@Column(name = \"FLEX5\")" + "\r\n" + 
"	private String flex5;" + "\r\n" + 
"	" + "\r\n" + 
"	public Rn_hl_line_t() {" + "\r\n" + 
"		super();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public int getL_id() {" + "\r\n" + 
"		return l_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setL_id(int l_id) {" + "\r\n" + 
"		this.l_id = l_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getL_textfield5() {" + "\r\n" + 
"		return l_textfield5;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setL_textfield5(String l_textfield5) {" + "\r\n" + 
"		this.l_textfield5 = l_textfield5;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getL_textfield6() {" + "\r\n" + 
"		return l_textfield6;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setL_textfield6(String l_textfield6) {" + "\r\n" + 
"		this.l_textfield6 = l_textfield6;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getL_textfield7() {" + "\r\n" + 
"		return l_textfield7;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setL_textfield7(String l_textfield7) {" + "\r\n" + 
"		this.l_textfield7 = l_textfield7;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getL_textfield8() {" + "\r\n" + 
"		return l_textfield8;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setL_textfield8(String l_textfield8) {" + "\r\n" + 
"		this.l_textfield8 = l_textfield8;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute1() {" + "\r\n" + 
"		return attribute1;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute1(String attribute1) {" + "\r\n" + 
"		this.attribute1 = attribute1;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute2() {" + "\r\n" + 
"		return attribute2;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute2(String attribute2) {" + "\r\n" + 
"		this.attribute2 = attribute2;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute3() {" + "\r\n" + 
"		return attribute3;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute3(String attribute3) {" + "\r\n" + 
"		this.attribute3 = attribute3;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute4() {" + "\r\n" + 
"		return attribute4;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute4(String attribute4) {" + "\r\n" + 
"		this.attribute4 = attribute4;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute5() {" + "\r\n" + 
"		return attribute5;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute5(String attribute5) {" + "\r\n" + 
"		this.attribute5 = attribute5;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute6() {" + "\r\n" + 
"		return attribute6;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute6(String attribute6) {" + "\r\n" + 
"		this.attribute6 = attribute6;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute7() {" + "\r\n" + 
"		return attribute7;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute7(String attribute7) {" + "\r\n" + 
"		this.attribute7 = attribute7;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute8() {" + "\r\n" + 
"		return attribute8;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute8(String attribute8) {" + "\r\n" + 
"		this.attribute8 = attribute8;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute9() {" + "\r\n" + 
"		return attribute9;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute9(String attribute9) {" + "\r\n" + 
"		this.attribute9 = attribute9;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute10() {" + "\r\n" + 
"		return attribute10;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute10(String attribute10) {" + "\r\n" + 
"		this.attribute10 = attribute10;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute11() {" + "\r\n" + 
"		return attribute11;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute11(String attribute11) {" + "\r\n" + 
"		this.attribute11 = attribute11;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute12() {" + "\r\n" + 
"		return attribute12;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute12(String attribute12) {" + "\r\n" + 
"		this.attribute12 = attribute12;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute13() {" + "\r\n" + 
"		return attribute13;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute13(String attribute13) {" + "\r\n" + 
"		this.attribute13 = attribute13;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute14() {" + "\r\n" + 
"		return attribute14;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute14(String attribute14) {" + "\r\n" + 
"		this.attribute14 = attribute14;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getAttribute15() {" + "\r\n" + 
"		return attribute15;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAttribute15(String attribute15) {" + "\r\n" + 
"		this.attribute15 = attribute15;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getFlex1() {" + "\r\n" + 
"		return flex1;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setFlex1(String flex1) {" + "\r\n" + 
"		this.flex1 = flex1;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getFlex2() {" + "\r\n" + 
"		return flex2;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setFlex2(String flex2) {" + "\r\n" + 
"		this.flex2 = flex2;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getFlex3() {" + "\r\n" + 
"		return flex3;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setFlex3(String flex3) {" + "\r\n" + 
"		this.flex3 = flex3;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getFlex4() {" + "\r\n" + 
"		return flex4;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setFlex4(String flex4) {" + "\r\n" + 
"		this.flex4 = flex4;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getFlex5() {" + "\r\n" + 
"		return flex5;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setFlex5(String flex5) {" + "\r\n" + 
"		this.flex5 = flex5;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Rn_hl_header_t getRn_hl_header_t() {" + "\r\n" + 
"		return rn_hl_header_t;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setRn_hl_header_t(Rn_hl_header_t rn_hl_header_t) {" + "\r\n" + 
"		this.rn_hl_header_t = rn_hl_header_t;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"CREATED_BY\")" + "\r\n" + 
"	private int created_by;" + "\r\n" + 
"	@Column(name = \"LAST_UPDATED_BY\")" + "\r\n" + 
"	private int last_updated_by;" + "\r\n" + 
"	@Column(name = \"CREATION_DATE\")" + "\r\n" + 
"	private Date creation_date;" + "\r\n" + 
"	@Column(name = \"LAST_UPDATE_DATE\")" + "\r\n" + 
"	private Date last_update_date;" + "\r\n" + 
"	@Column(name = \"ACCOUNT_ID\")" + "\r\n" + 
"	private int account_id;" + "\r\n" + 
"" + "\r\n" + 
"	public int getCreated_by() {" + "\r\n" + 
"		return created_by;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setCreated_by(int created_by) {" + "\r\n" + 
"		this.created_by = created_by;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public int getLast_updated_by() {" + "\r\n" + 
"		return last_updated_by;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setLast_updated_by(int last_updated_by) {" + "\r\n" + 
"		this.last_updated_by = last_updated_by;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Date getCreation_date() {" + "\r\n" + 
"		return creation_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setCreation_date(Date creation_date) {" + "\r\n" + 
"		this.creation_date = creation_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Date getLast_update_date() {" + "\r\n" + 
"		return last_update_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setLast_update_date(Date last_update_date) {" + "\r\n" + 
"		this.last_update_date = last_update_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public int getAccount_id() {" + "\r\n" + 
"		return account_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAccount_id(int account_id) {" + "\r\n" + 
"		this.account_id = account_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public String toString() {" + "\r\n" + 
"		return \"Rn_hl_line_t [l_id=\" + l_id + \", l_textfield5=\" + l_textfield5 + \", l_textfield6=\" + l_textfield6" + "\r\n" + 
"				+ \", l_textfield7=\" + l_textfield7 + \", l_textfield8=\" + l_textfield8 + \", rn_hl_header_t=\"" + "\r\n" + 
"				+ rn_hl_header_t + \", attribute1=\" + attribute1 + \", attribute2=\" + attribute2 + \", attribute3=\"" + "\r\n" + 
"				+ attribute3 + \", attribute4=\" + attribute4 + \", attribute5=\" + attribute5 + \", attribute6=\"" + "\r\n" + 
"				+ attribute6 + \", attribute7=\" + attribute7 + \", attribute8=\" + attribute8 + \", attribute9=\"" + "\r\n" + 
"				+ attribute9 + \", attribute10=\" + attribute10 + \", attribute11=\" + attribute11 + \", attribute12=\"" + "\r\n" + 
"				+ attribute12 + \", attribute13=\" + attribute13 + \", attribute14=\" + attribute14 + \", attribute15=\"" + "\r\n" + 
"				+ attribute15 + \", flex1=\" + flex1 + \", flex2=\" + flex2 + \", flex3=\" + flex3 + \", flex4=\" + flex4" + "\r\n" + 
"				+ \", flex5=\" + flex5 + \", created_by=\" + created_by + \", last_updated_by=\" + last_updated_by" + "\r\n" + 
"				+ \", creation_date=\" + creation_date + \", last_update_date=\" + last_update_date + \", account_id=\"" + "\r\n" + 
"				+ account_id + \"]\";" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"}" 
