"package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.*;" + "\r\n" + 
"import java.util.*;" + "\r\n" + 
"import javax.persistence.*;" + "\r\n" + 
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonManagedReference;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
"" + "\r\n" + 
"//@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"sales\")" + "\r\n" + 
"public class " + salesjava + "{"+
"" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"    private int id;" + "\r\n" + 
"	private String name;" + "\r\n" + 
"    private String department ;" + "\r\n" + 
"    private Date joining_date;" + "\r\n" + 
"    private String status;" + "\r\n" + 
"   " + "\r\n" + 
"    @OneToMany(mappedBy = \"sales\",cascade = CascadeType.ALL)" + "\r\n" + 
"    @JsonManagedReference" + "\r\n" + 
"    private Set<SalesPerson> salesPerson;" + "\r\n" + 
"" + "\r\n" + 
"	public int getId() {" + "\r\n" + 
"		return id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setId(int id) {" + "\r\n" + 
"		this.id = id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getName() {" + "\r\n" + 
"		return name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setName(String name) {" + "\r\n" + 
"		this.name = name;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getDepartment() {" + "\r\n" + 
"		return department;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setDepartment(String department) {" + "\r\n" + 
"		this.department = department;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Date getJoining_date() {" + "\r\n" + 
"		return joining_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setJoining_date(Date joining_date) {" + "\r\n" + 
"		this.joining_date = joining_date;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public String getStatus() {" + "\r\n" + 
"		return status;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setStatus(String status) {" + "\r\n" + 
"		this.status = status;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Set<SalesPerson> getSalesPerson() {" + "\r\n" + 
"		return salesPerson;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setSalesPerson(Set<SalesPerson> salesPerson) {" + "\r\n" + 
"		this.salesPerson = salesPerson;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Sales(int id, String name, String department, Date joining_date, String status," + "\r\n" + 
"			Set<SalesPerson> salesPerson) {" + "\r\n" + 
"		super();" + "\r\n" + 
"		this.id = id;" + "\r\n" + 
"		this.name = name;" + "\r\n" + 
"		this.department = department;" + "\r\n" + 
"		this.joining_date = joining_date;" + "\r\n" + 
"		this.status = status;" + "\r\n" + 
"		this.salesPerson = salesPerson;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public Sales() {" + "\r\n" + 
"		super();" + "\r\n" + 
"		// TODO Auto-generated constructor stub" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
