"package com.realnet.comm.entity;" + "\r\n" + 
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
"public class sales extends Rn_AuditEntity  {" + "\r\n" + 
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