"package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
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
"" + "\r\n" + 
"import com.fasterxml.jackson.annotation.JsonManagedReference;" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_AuditEntity;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbhlentityheader1 + "{"+
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.IDENTITY)" + "\r\n" + 
"	@Column(name = \"id\")" + "\r\n" + 
"	private Integer id;" + "\r\n" + 
"	private String afname;" + "\r\n" + 
"	private String lname;" + "\r\n" + 
"	" + "\r\n" + 
"	@OneToMany(mappedBy = \"author\", cascade = CascadeType.ALL)" + "\r\n" + 
"	@JsonManagedReference" + "\r\n" + 
"	private List<book> book;" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
