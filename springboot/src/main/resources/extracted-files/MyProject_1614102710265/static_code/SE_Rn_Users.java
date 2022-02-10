"package com.realnet." + module_name + ".model;" + "\r\n" + 
"" + "\r\n" + 
"import java.io.Serializable;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"" + "\r\n" + 
"import org.hibernate.annotations.GenericGenerator;" + "\r\n" + 
"import org.springframework.format.annotation.DateTimeFormat;" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"RN_USERS_T\")" + "\r\n" + 
"public class Rn_Users implements Serializable {" + "\r\n" + 
"	private static final long serialVersionUID = 1L;" + "\r\n" + 
"" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO, generator = \"native\")" + "\r\n" + 
"	@GenericGenerator(name = \"native\", strategy = \"native\")" + "\r\n" + 
"	@Column(name = \"USER_ID\")" + "\r\n" + 
"	private int user_id;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"PROFILE_ID\")" + "\r\n" + 
"	private int profile_id;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"ACCOUNT_ID\")" + "\r\n" + 
"	private int account_id;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"USER_NAME\")" + "\r\n" + 
"	private String user_name;	" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"ATTRIBUTE1\")" + "\r\n" + 
"	private String source;	" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"PASSWORD\")" + "\r\n" + 
"	private String password;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"USER_TYPE\")" + "\r\n" + 
"	private String user_type;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"FIRST_NAME\")" + "\r\n" + 
"	private String first_name;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"MIDDLE_NAME\")" + "\r\n" + 
"	private String middle_name;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"LAST_NAME\")" + "\r\n" + 
"	private String last_name;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"CONTACT_NUMBER\")" + "\r\n" + 
"	private String contact_number;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"EMAIL_ADDRESS\")" + "\r\n" + 
"	private String email_address;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"START_DATE\")" + "\r\n" + 
"	private Date start_date;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"END_DATE\")" + "\r\n" + 
"	private Date end_date;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"IS_ACTIVE\")" + "\r\n" + 
"	private String is_active;" + "\r\n" + 
"	" + "\r\n" + 
"	@DateTimeFormat(pattern = \"YYYY-MM-DD\")" + "\r\n" + 
"	@Column(name = \"LAST_LOGIN\")" + "\r\n" + 
"	private Date last_login= new java.sql.Date(new java.util.Date().getTime());" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"CREATED_BY\")" + "\r\n" + 
"	private int created_by;" + "\r\n" + 
"" + "\r\n" + 
"	@DateTimeFormat(pattern = \"YYYY-MM-DD\")" + "\r\n" + 
"	@Column(name = \"CREATION_DATE\" , updatable = false)" + "\r\n" + 
"	private Date creation_date= new java.sql.Date(new java.util.Date().getTime());" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"LAST_UPDATED_BY\")" + "\r\n" + 
"	private int last_updated_by;" + "\r\n" + 
"" + "\r\n" + 
"	@DateTimeFormat(pattern = \"YYYY-MM-DD\")" + "\r\n" + 
"	@Column(name = \"LAST_UPDATE_DATE\" , updatable = true)" + "\r\n" + 
"	private Date last_update_date= new java.sql.Date(new java.util.Date().getTime());" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"FORM_CODE\")" + "\r\n" + 
"	private String form_code;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name = \"FIELD_NAME\")" + "\r\n" + 
"	private String field_name;" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"	public int getAccount_id() {" + "\r\n" + 
"		return account_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setAccount_id(int account_id) {" + "\r\n" + 
"		this.account_id = account_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getField_name() {" + "\r\n" + 
"		return field_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setField_name(String field_name) {" + "\r\n" + 
"		this.field_name = field_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getForm_code() {" + "\r\n" + 
"		return form_code;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setForm_code(String form_code) {" + "\r\n" + 
"		this.form_code = form_code;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public int getUser_id() {" + "\r\n" + 
"		return user_id;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	public void setUser_id(int user_id) {" + "\r\n" + 
"		this.user_id = user_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public int getProfile_id() {" + "\r\n" + 
"		return profile_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setProfile_id(int profile_id) {" + "\r\n" + 
"		this.profile_id = profile_id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getUser_name() {" + "\r\n" + 
"		return user_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setUser_name(String user_name) {" + "\r\n" + 
"		this.user_name = user_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getPassword() {" + "\r\n" + 
"		return password;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setPassword(String password) {" + "\r\n" + 
"		this.password = password;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getUser_type() {" + "\r\n" + 
"		return user_type;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setUser_type(String user_type) {" + "\r\n" + 
"		this.user_type = user_type;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getFirst_name() {" + "\r\n" + 
"		return first_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setFirst_name(String first_name) {" + "\r\n" + 
"		this.first_name = first_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getMiddle_name() {" + "\r\n" + 
"		return middle_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setMiddle_name(String middle_name) {" + "\r\n" + 
"		this.middle_name = middle_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getLast_name() {" + "\r\n" + 
"		return last_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setLast_name(String last_name) {" + "\r\n" + 
"		this.last_name = last_name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getContact_number() {" + "\r\n" + 
"		return contact_number;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setContact_number(String contact_number) {" + "\r\n" + 
"		this.contact_number = contact_number;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getEmail_address() {" + "\r\n" + 
"		return email_address;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setEmail_address(String email_address) {" + "\r\n" + 
"		this.email_address = email_address;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Date getStart_date() {" + "\r\n" + 
"		return start_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setStart_date(Date start_date) {" + "\r\n" + 
"		this.start_date = start_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Date getEnd_date() {" + "\r\n" + 
"		return end_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setEnd_date(Date end_date) {" + "\r\n" + 
"		this.end_date = end_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getIs_active() {" + "\r\n" + 
"		return is_active;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setIs_active(String is_active) {" + "\r\n" + 
"		this.is_active = is_active;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Date getLast_login() {" + "\r\n" + 
"		return last_login;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setLast_login(Date last_login) {" + "\r\n" + 
"		this.last_login = last_login;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public int getCreated_by() " + "\r\n" + 
"	{" + "\r\n" + 
"		return created_by;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setCreated_by(int user_id) {" + "\r\n" + 
"		this.created_by = user_id;" + "\r\n" + 
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
"	public int getLast_updated_by() {" + "\r\n" + 
"		return last_updated_by;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setLast_updated_by(int user_id) {" + "\r\n" + 
"		this.last_updated_by = user_id;" + "\r\n" + 
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
"	public String getSource() {" + "\r\n" + 
"		return source;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setSource(String source) {" + "\r\n" + 
"		this.source = source;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
