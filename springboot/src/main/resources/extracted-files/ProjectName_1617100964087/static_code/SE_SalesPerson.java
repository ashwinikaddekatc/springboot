"package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
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
"import com.fasterxml.jackson.annotation.JsonBackReference;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"Sales_person\")" + "\r\n" + 
"public class " + salesperson + "{"+
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	private int id;" + "\r\n" + 
"	" + "\r\n" + 
"	@Column(name=\"NAME\")" + "\r\n" + 
"	private String name;" + "\r\n" + 
"	" + "\r\n" + 
"	@ManyToOne(fetch = FetchType.LAZY,optional = false)" + "\r\n" + 
"	@JoinColumn(name=\"sales_id\",nullable=false)" + "\r\n" + 
"	@JsonBackReference" + "\r\n" + 
"	private Sales sales;" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"//package com.realnet.comm.entity;" + "\r\n" + 
"//" + "\r\n" + 
"//import javax.persistence.Entity;" + "\r\n" + 
"//import javax.persistence.FetchType;" + "\r\n" + 
"//import javax.persistence.GeneratedValue;" + "\r\n" + 
"//import javax.persistence.GenerationType;" + "\r\n" + 
"//import javax.persistence.Id;" + "\r\n" + 
"//import javax.persistence.JoinColumn;" + "\r\n" + 
"//import javax.persistence.ManyToOne;" + "\r\n" + 
"//import javax.persistence.Table;" + "\r\n" + 
"//" + "\r\n" + 
"//import com.fasterxml.jackson.annotation.JsonBackReference;" + "\r\n" + 
"//" + "\r\n" + 
"//import lombok.Data;" + "\r\n" + 
"//@Data" + "\r\n" + 
"//@Entity" + "\r\n" + 
"//@Table(name = \"Sales_Person\")" + "\r\n" + 
"//public class SalesPerson {" + "\r\n" + 
"//" + "\r\n" + 
"//	@Id" + "\r\n" + 
"//	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"//	private int id;" + "\r\n" + 
"//	" + "\r\n" + 
"//	" + "\r\n" + 
"//	//getter setter" + "\r\n" + 
"//	private String name;" + "\r\n" + 
"//	" + "\r\n" + 
"//	@ManyToOne(fetch = FetchType.LAZY,optional = false)" + "\r\n" + 
"//	@JoinColumn(name = \"sales_fk\" )" + "\r\n" + 
"//	@JsonBackReference" + "\r\n" + 
"//	private Sales sales;" + "\r\n" + 
"//	" + "\r\n" + 
"////	public SalesPerson(){" + "\r\n" + 
"////		super();" + "\r\n" + 
"////	}" + "\r\n" + 
"//	//@ManyToOne(fetch = FetchType.LAZY)" + "\r\n" + 
"//	//@JsonBackReference" + "\r\n" + 
"//	" + "\r\n" + 
"//" + "\r\n" + 
"//	" + "\r\n" + 
"////	//getter setter" + "\r\n" + 
"////	public int getId() {" + "\r\n" + 
"////		return id;" + "\r\n" + 
"////	}" + "\r\n" + 
"////" + "\r\n" + 
"////	public void setId(int id) {" + "\r\n" + 
"////		this.id = id;" + "\r\n" + 
"////	}" + "\r\n" + 
"////" + "\r\n" + 
"////	public String getName() {" + "\r\n" + 
"////		return name;" + "\r\n" + 
"////	}" + "\r\n" + 
"////" + "\r\n" + 
"////	public void setName(String name) {" + "\r\n" + 
"////		this.name = name;" + "\r\n" + 
"////	}" + "\r\n" + 
"//	" + "\r\n" + 
"//	" + "\r\n" + 
"//}" 
