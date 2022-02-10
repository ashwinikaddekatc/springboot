"package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.FetchType;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.ManyToOne;" + "\r\n" + 
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonBackReference;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbhlentityline1 + "{"+
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.IDENTITY)" + "\r\n" + 
"	@Column(name = \"id\")" + "\r\n" + 
"	private Integer id;" + "\r\n" + 
"" + "\r\n" + 
"	@Column(name = \"NAME\")" + "\r\n" + 
"	private String bname;" + "\r\n" + 
"" + "\r\n" + 
"	private String btitle;" + "\r\n" + 
"	" + "\r\n" + 
"	@ManyToOne(fetch = FetchType.LAZY, optional = false)" + "\r\n" + 
"	@JsonBackReference" + "\r\n" + 
"	private author author;" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
