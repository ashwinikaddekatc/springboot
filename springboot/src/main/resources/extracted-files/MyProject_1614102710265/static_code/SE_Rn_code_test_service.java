"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_code_test_t;" + "\r\n" + 
"" + "\r\n" + 
"public interface	Rn_code_test_service{" + "\r\n" + 
"	List<Rn_code_test_t> getAll();" + "\r\n" + 
"	" + "\r\n" + 
"Rn_code_test_t	getById(int id);" + "\r\n" + 
"	" + "\r\n" + 
"Rn_code_test_t save(Rn_code_test_t rn_code_test_t);" + "\r\n" + 
"	" + "\r\n" + 
"Rn_code_test_t updateById(int id,Rn_code_test_t rn_code_test_t) throws ResourceNotFoundException;" + "\r\n" + 
"	" + "\r\n" + 
"	void deleteById(int id) throws ResourceNotFoundException;" + "\r\n" + 
"}" 
