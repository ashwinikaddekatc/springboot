"package com.realnet." + module_name + ".model;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.CascadeType;" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.OneToMany;" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonManagedReference;" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"instructor\")" + "\r\n" + 
"public class Instructor extends AuditModel {" + "\r\n" + 
"" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	@Column(name = \"id\")" + "\r\n" + 
"	private int id;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"first_name\")" + "\r\n" + 
"	private String firstName;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"last_name\")" + "\r\n" + 
"	private String lastName;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"email\")" + "\r\n" + 
"	private String email;" + "\r\n" + 
"" + "\r\n" + 
"	@OneToMany(mappedBy = \"instructor\", orphanRemoval = true, cascade = CascadeType.PERSIST)" + "\r\n" + 
"	@JsonManagedReference" + "\r\n" + 
"	private List<Course> courses;" + "\r\n" + 
"" + "\r\n" + 
"	public Instructor(String firstName, String lastName, String email) {" + "\r\n" + 
"		this.firstName = firstName;" + "\r\n" + 
"		this.lastName = lastName;" + "\r\n" + 
"		this.email = email;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Instructor() {" + "\r\n" + 
"		super();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public int getId() {" + "\r\n" + 
"		return id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setId(int id) {" + "\r\n" + 
"		this.id = id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getFirstName() {" + "\r\n" + 
"		return firstName;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setFirstName(String firstName) {" + "\r\n" + 
"		this.firstName = firstName;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getLastName() {" + "\r\n" + 
"		return lastName;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setLastName(String lastName) {" + "\r\n" + 
"		this.lastName = lastName;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getEmail() {" + "\r\n" + 
"		return email;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setEmail(String email) {" + "\r\n" + 
"		this.email = email;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public List<Course> getCourses() {" + "\r\n" + 
"		return courses;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setCourses(List<Course> courses) {" + "\r\n" + 
"		this.courses = courses;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
