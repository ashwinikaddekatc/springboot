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
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonBackReference;" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"course\")" + "\r\n" + 
"public class Course extends AuditModel {" + "\r\n" + 
"" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	@Column(name = \"id\")" + "\r\n" + 
"	private int id;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"title\")" + "\r\n" + 
"	private String title;" + "\r\n" + 
"" + "\r\n" + 
"	@ManyToOne(fetch = FetchType.LAZY, optional = false)" + "\r\n" + 
"	@JoinColumn(name = \"instructor_id\")" + "\r\n" + 
"	@JsonBackReference" + "\r\n" + 
"	private Instructor instructor;" + "\r\n" + 
"" + "\r\n" + 
"	public Course() {" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Course(String title) {" + "\r\n" + 
"		this.title = title;" + "\r\n" + 
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
"	public String getTitle() {" + "\r\n" + 
"		return title;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setTitle(String title) {" + "\r\n" + 
"		this.title = title;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Instructor getInstructor() {" + "\r\n" + 
"		return instructor;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setInstructor(Instructor instructor) {" + "\r\n" + 
"		this.instructor = instructor;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public String toString() {" + "\r\n" + 
"		return \"Course [id=\" + id + \", title=\" + title + \"]\";" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
