"package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"import java.util.Set;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.CascadeType;" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.OneToMany;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonManagedReference;" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_AuditEntity;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbhlentityheader1 + "{"+
"	" + "\r\n" + 
"	/**" + "\r\n" + 
"	 * " + "\r\n" + 
"	 */" + "\r\n" + 
"	private static final long serialVersionUID = 1L;" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	@Column(name = \"sales_id\")" + "\r\n" + 
"	private int sid;" + "\r\n" + 
"	@Column(name = \"sales_name\")" + "\r\n" + 
"	private String sname; //text" + "\r\n" + 
"	@Column(name = \"sales_department\")" + "\r\n" + 
"	private String department; //dropdown" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	@OneToMany(mappedBy = \"sales\",cascade = CascadeType.ALL)" + "\r\n" + 
"	@JsonManagedReference" + "\r\n" + 
"	private Set<salesperson> salesperson;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public int getSid() {" + "\r\n" + 
"		return sid;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public void setSid(int sid) {" + "\r\n" + 
"		this.sid = sid;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public String getSname() {" + "\r\n" + 
"		return sname;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public void setSname(String sname) {" + "\r\n" + 
"		this.sname = sname;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public String getDepartment() {" + "\r\n" + 
"		return department;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public void setDepartment(String department) {" + "\r\n" + 
"		this.department = department;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public Set<salesperson> getSalesperson() {" + "\r\n" + 
"		return salesperson;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public void setSalesperson(Set<salesperson> salesperson) {" + "\r\n" + 
"		this.salesperson = salesperson;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"}" 
