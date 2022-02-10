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
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbohentity1 + "{"+
"	" + "\r\n" + 
"	@Id" + "\r\n" + 
"	@GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"	@Column(name = \"sales_id\")" + "\r\n" + 
"	private int sid;" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public int getSid() {" + "\r\n" + 
"		return sid;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	public void setSid(int sid) {" + "\r\n" + 
"		this.sid = sid;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"}" 
