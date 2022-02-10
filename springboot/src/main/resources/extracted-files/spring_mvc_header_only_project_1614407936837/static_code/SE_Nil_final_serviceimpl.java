"" + "\r\n" + 
"package com.realnet.test_module1." + module_name + "." + module_name + ".service;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"import java.util.ArrayList;" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.stereotype.Component;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"import com.realnet.test_module1.dao." + dao_name_first_upper + ";" + "\r\n" + 
"import com.realnet.test_module1.model.Rn_nil_final;" + "\r\n" + 
"@Service" + "\r\n" + 
"public class	Nil_final_serviceimpl	implements	Nil_final_service {" + "\r\n" + 
"" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private 	" + dao_name_first_upper + "	" + dao_name_lower + ";" + "\r\n" + 
"" + "\r\n" + 
"@Override " + "\r\n" + 
"public List<Rn_nil_final> prefield(int u_id) {" + "\r\n" + 
"return	" + dao_name_lower + ".prefield(u_id);" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"@Override" + "\r\n" + 
"public List<Rn_nil_final> userlist() {" + "\r\n" + 
"return	" + dao_name_lower + ".userlist();" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"@Override" + "\r\n" + 
"public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4,	String	textfield5,	String	textfield6,	String	textfield7,	String	textfield8,	String	textfield9) {" + "\r\n" + 
"return	" + dao_name_lower + ".save(rowcount,id,textfield1,	textfield2,	textfield3,	textfield4,	textfield5,	textfield6,	textfield7,	textfield8,	textfield9);" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"public int saveheader(Rn_nil_final	rn_nil_final){" + "\r\n" + 
"	return 	" + dao_name_lower + ".saveheader(rn_nil_final);" + "\r\n" + 
"}" + "\r\n" + 
"}" 
