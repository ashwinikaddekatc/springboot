"package com.realnet.comm." + module_name + "." + module_name + ".entity;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"import javax.persistence.Entity;" + "\r\n" + 
"import javax.persistence.GeneratedValue;" + "\r\n" + 
"import javax.persistence.GenerationType;" + "\r\n" + 
"import javax.persistence.Id;" + "\r\n" + 
"import javax.persistence.Table;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_AuditEntity;" + "\r\n" + 
"import com.realnet.fnd.entity.Rn_Who_Columns;" + "\r\n" + 
"" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@Entity" + "\r\n" + 
"public class " + sbolentity1 + "{"+
"    " + "\r\n" + 
"    @Id" + "\r\n" + 
"    @GeneratedValue(strategy = GenerationType.AUTO)" + "\r\n" + 
"    private int Did;" + "\r\n" + 
"    private String Dname;" + "\r\n" + 
"    private String Dhead;" + "\r\n" + 
"    private long Dcontact;" + "\r\n" + 
"    private int No_ofEmp;" + "\r\n" + 
"}" 
