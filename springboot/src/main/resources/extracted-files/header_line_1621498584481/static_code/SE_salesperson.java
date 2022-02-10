"package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.FetchType;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.ManyToOne;" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonBackReference;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbhlentityline1 + "{"+
"" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	private int Pid;" + "\r\n" + 
"	private String 	Pname;" + "\r\n" + 
"" + "\r\n" + 
"	@ManyToOne(fetch = FetchType.LAZY)" + "\r\n" + 
"	@JsonBackReference" + "\r\n" + 
"	private sales sales;" + "\r\n" + 
"}" 
