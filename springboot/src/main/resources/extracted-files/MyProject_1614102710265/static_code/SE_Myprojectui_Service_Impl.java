"" + "\r\n" + 
"package com.realnet.test_module1.MyProjectModule." + module_name + ".service;" + "\r\n" + 
"import java.util.Date;" + "\r\n" + 
"import java.util.ArrayList;" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.stereotype.Component;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"import com.realnet.test_module1.dao.Myprojectui_dao;" + "\r\n" + 
"import com.realnet.test_module1.model.Myprojectui_t;" + "\r\n" + 
"@Service" + "\r\n" + 
"public class	Myprojectui_serviceimpl	implements	Myprojectui_service {" + "\r\n" + 
"" + "\r\n" + 
"@Autowired" + "\r\n" + 
"private 	Myprojectui_dao	myprojectui_dao;" + "\r\n" + 
"" + "\r\n" + 
"@Override " + "\r\n" + 
"public List<Myprojectui_t> prefield(int u_id) {" + "\r\n" + 
"return	myprojectui_dao.prefield(u_id);" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"@Override" + "\r\n" + 
"public List<Myprojectui_t> userlist() {" + "\r\n" + 
"return	myprojectui_dao.userlist();" + "\r\n" + 
"}" + "\r\n" + 
"" + "\r\n" + 
"public int saveheader(Myprojectui_t	myprojectui_t){" + "\r\n" + 
"	return 	myprojectui_dao.saveheader(myprojectui_t);" + "\r\n" + 
"}" + "\r\n" + 
"}" 
