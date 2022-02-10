"package com.realnet.test_module1.MyProjectModule." + module_name + ".model;" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.SequenceGenerator;" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"import org.hibernate.annotations.GenericGenerator;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"MYPROJECTUI_T\")" + "\r\n" + 
"public class	Myprojectui_t" + "\r\n" + 
"{" + "\r\n" + 
"@Id" + "\r\n" + 
"@GeneratedValue(strategy = GenerationType.IDENTITY)" + "\r\n" + 
"@Column(name = \"ID\")" + "\r\n" + 
"private int id;" + "\r\n" + 
"" + "\r\n" + 
"@Column(name = \"TEXTFIELD1\")" + "\r\n" + 
"private String 	textfield1;" + "\r\n" + 
"" + "\r\n" + 
"@Column(name = \"TEXTFIELD2\")" + "\r\n" + 
"private String 	textfield2;" + "\r\n" + 
"" + "\r\n" + 
"@Column(name = \"TEXTFIELD3\")" + "\r\n" + 
"private String 	textfield3;" + "\r\n" + 
"" + "\r\n" + 
"@Column(name = \"TEXTFIELD4\")" + "\r\n" + 
"private String 	textfield4;" + "\r\n" + 
"" + "\r\n" + 
"	public int getId() {" + "\r\n" + 
"		return	id;" + "\r\n" + 
"	}" + "\r\n" + 
"	public void setId(int id) {" + "\r\n" + 
"		this.id = id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getTextfield1() {" + "\r\n" + 
"		return textfield1;" + "\r\n" + 
"	}" + "\r\n" + 
"	public void setTextfield1(String textfield1) {" + "\r\n" + 
"		this.textfield1 = textfield1;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getTextfield2() {" + "\r\n" + 
"		return textfield2;" + "\r\n" + 
"	}" + "\r\n" + 
"	public void setTextfield2(String textfield2) {" + "\r\n" + 
"		this.textfield2 = textfield2;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getTextfield3() {" + "\r\n" + 
"		return textfield3;" + "\r\n" + 
"	}" + "\r\n" + 
"	public void setTextfield3(String textfield3) {" + "\r\n" + 
"		this.textfield3 = textfield3;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getTextfield4() {" + "\r\n" + 
"		return textfield4;" + "\r\n" + 
"	}" + "\r\n" + 
"	public void setTextfield4(String textfield4) {" + "\r\n" + 
"		this.textfield4 = textfield4;" + "\r\n" + 
"	}" + "\r\n" + 
"@Column(name = \"EXTN1\")" + "\r\n" + 
"private String 	extn1;" + "\r\n" + 
"@Column(name = \"EXTN2\")" + "\r\n" + 
"private String 	extn2;" + "\r\n" + 
"@Column(name = \"EXTN3\")" + "\r\n" + 
"private String 	extn3;" + "\r\n" + 
"@Column(name = \"EXTN4\")" + "\r\n" + 
"private String 	extn4;" + "\r\n" + 
"@Column(name = \"EXTN5\")" + "\r\n" + 
"private String 	extn5;" + "\r\n" + 
"@Column(name = \"EXTN6\")" + "\r\n" + 
"private String 	extn6;" + "\r\n" + 
"@Column(name = \"EXTN7\")" + "\r\n" + 
"private String 	extn7;" + "\r\n" + 
"@Column(name = \"EXTN8\")" + "\r\n" + 
"private String 	extn8;" + "\r\n" + 
"@Column(name = \"EXTN9\")" + "\r\n" + 
"private String 	extn9;" + "\r\n" + 
"@Column(name = \"EXTN10\")" + "\r\n" + 
"private String 	extn10;" + "\r\n" + 
"@Column(name = \"EXTN11\")" + "\r\n" + 
"private String 	extn11;" + "\r\n" + 
"@Column(name = \"EXTN12\")" + "\r\n" + 
"private String 	extn12;" + "\r\n" + 
"@Column(name = \"EXTN13\")" + "\r\n" + 
"private String 	extn13;" + "\r\n" + 
"@Column(name = \"EXTN14\")" + "\r\n" + 
"private String 	extn14;" + "\r\n" + 
"@Column(name = \"EXTN15\")" + "\r\n" + 
"private String 	extn15;" + "\r\n" + 
"@Column(name = \"FLEX1\")" + "\r\n" + 
"private String 	flex1;" + "\r\n" + 
"@Column(name = \"FLEX2\")" + "\r\n" + 
"private String 	flex2;" + "\r\n" + 
"@Column(name = \"FLEX3\")" + "\r\n" + 
"private String 	flex3;" + "\r\n" + 
"@Column(name = \"FLEX4\")" + "\r\n" + 
"private String 	flex4;" + "\r\n" + 
"@Column(name = \"FLEX5\")" + "\r\n" + 
"private String 	flex5;" + "\r\n" + 
"public String getExtn1() {" + "\r\n" + 
"return extn1;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn1(String	extn1) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn1=extn1;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn2() {" + "\r\n" + 
"return extn2;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn2(String	extn2) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn2=extn2;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn3() {" + "\r\n" + 
"return extn3;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn3(String	extn3) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn3=extn3;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn4() {" + "\r\n" + 
"return extn4;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn4(String	extn4) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn4=extn4;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn5() {" + "\r\n" + 
"return extn5;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn5(String	extn5) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn5=extn5;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn6() {" + "\r\n" + 
"return extn6;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn6(String	extn6) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn6=extn6;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn7() {" + "\r\n" + 
"return extn7;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn7(String	extn7) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn7=extn7;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn8() {" + "\r\n" + 
"return extn8;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn8(String	extn8) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn8=extn8;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn9() {" + "\r\n" + 
"return extn9;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn9(String	extn9) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn9=extn9;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn10() {" + "\r\n" + 
"return extn10;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn10(String	extn10) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn10=extn10;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn11() {" + "\r\n" + 
"return extn11;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn11(String	extn11) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn11=extn11;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn12() {" + "\r\n" + 
"return extn12;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn12(String	extn12) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn12=extn12;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn13() {" + "\r\n" + 
"return extn13;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn13(String	extn13) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn13=extn13;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn14() {" + "\r\n" + 
"return extn14;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn14(String	extn14) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn14=extn14;" + "\r\n" + 
"}" + "\r\n" + 
"public String getExtn15() {" + "\r\n" + 
"return extn15;" + "\r\n" + 
"}" + "\r\n" + 
"public void setExtn15(String	extn15) {" + "\r\n" + 
"" + "\r\n" + 
"this.extn15=extn15;" + "\r\n" + 
"}" + "\r\n" + 
"public String getFlex1() {" + "\r\n" + 
"return flex1;" + "\r\n" + 
"}" + "\r\n" + 
"public void setFlex1(String	flex1) {" + "\r\n" + 
"" + "\r\n" + 
"this.flex1=flex1;" + "\r\n" + 
"}" + "\r\n" + 
"public String getFlex2() {" + "\r\n" + 
"return flex2;" + "\r\n" + 
"}" + "\r\n" + 
"public void setFlex2(String	flex2) {" + "\r\n" + 
"" + "\r\n" + 
"this.flex2=flex2;" + "\r\n" + 
"}" + "\r\n" + 
"public String getFlex3() {" + "\r\n" + 
"return flex3;" + "\r\n" + 
"}" + "\r\n" + 
"public void setFlex3(String	flex3) {" + "\r\n" + 
"" + "\r\n" + 
"this.flex3=flex3;" + "\r\n" + 
"}" + "\r\n" + 
"public String getFlex4() {" + "\r\n" + 
"return flex4;" + "\r\n" + 
"}" + "\r\n" + 
"public void setFlex4(String	flex4) {" + "\r\n" + 
"" + "\r\n" + 
"this.flex4=flex4;" + "\r\n" + 
"}" + "\r\n" + 
"public String getFlex5() {" + "\r\n" + 
"return flex5;" + "\r\n" + 
"}" + "\r\n" + 
"public void setFlex5(String	flex5) {" + "\r\n" + 
"" + "\r\n" + 
"this.flex5=flex5;" + "\r\n" + 
"}" + "\r\n" + 
"@Temporal(TemporalType.TIMESTAMP)" + "\r\n" + 
"	@Column(name = \"CREATED_AT\", nullable = false, updatable = false)" + "\r\n" + 
"	@CreatedDate" + "\r\n" + 
"	private Date createdAt;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	@Temporal(TemporalType.TIMESTAMP)" + "\r\n" + 
"	@Column(name = \"UPDATED_AT\", nullable = false)" + "\r\n" + 
"	@LastModifiedDate" + "\r\n" + 
"	private Date updatedAt;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"CREATED_BY\", updatable = false)" + "\r\n" + 
"	private Long createdBy;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"UPDATED_BY\")" + "\r\n" + 
"	private Long updatedBy;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"ACCOUNT_ID\")" + "\r\n" + 
"	private Long accountId;" + "\r\n" + 
"" + "\r\n" + 
"	public Date getCreatedAt() {" + "\r\n" + 
"		return createdAt;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setCreatedAt(Date createdAt) {" + "\r\n" + 
"		this.createdAt = createdAt;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Date getUpdatedAt() {" + "\r\n" + 
"		return updatedAt;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setUpdatedAt(Date updatedAt) {" + "\r\n" + 
"		this.updatedAt = updatedAt;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Long getCreatedBy() {" + "\r\n" + 
"		return createdBy;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setCreatedBy(Long createdBy) {" + "\r\n" + 
"		this.createdBy = createdBy;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Long getUpdatedBy() {" + "\r\n" + 
"		return updatedBy;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setUpdatedBy(Long updatedBy) {" + "\r\n" + 
"		this.updatedBy = updatedBy;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Long getAccountId() {" + "\r\n" + 
"		return accountId;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAccountId(Long accountId) {" + "\r\n" + 
"		this.accountId = accountId;" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
