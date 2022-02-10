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
"public class " + salesperson + "{"
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
"" 
