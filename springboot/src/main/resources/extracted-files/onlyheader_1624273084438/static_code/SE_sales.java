"package com.realnet.comm." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"import java.util.Set;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Column;" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_AuditEntity;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"@Table(name = \"sales\")" + "\r\n" + 
"public class " + sbohentity1 + "{"+
"	" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	@Column(name = \"id\")" + "\r\n" + 
"	private int id;" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public int getId() {" + "\r\n" + 
"		return id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setId(int id) {" + "\r\n" + 
"		this.id = id;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
