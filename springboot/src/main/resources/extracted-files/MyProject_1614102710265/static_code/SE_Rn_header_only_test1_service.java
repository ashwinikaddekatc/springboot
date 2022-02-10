"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_header_only_test1_t;" + "\r\n" + 
"" + "\r\n" + 
"public interface	Rn_header_only_test1_service{" + "\r\n" + 
"	List<Rn_header_only_test1_t> getAll();" + "\r\n" + 
"	" + "\r\n" + 
"Rn_header_only_test1_t	getById(int id);" + "\r\n" + 
"	" + "\r\n" + 
"Rn_header_only_test1_t save(Rn_header_only_test1_t rn_header_only_test1_t);" + "\r\n" + 
"	" + "\r\n" + 
"Rn_header_only_test1_t updateById(int id,Rn_header_only_test1_t rn_header_only_test1_t) throws ResourceNotFoundException;" + "\r\n" + 
"	" + "\r\n" + 
"	void deleteById(int id) throws ResourceNotFoundException;" + "\r\n" + 
"}" 
