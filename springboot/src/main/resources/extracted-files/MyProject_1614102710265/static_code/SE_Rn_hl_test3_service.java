"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_hl_test3_header_t;" + "\r\n" + 
"" + "\r\n" + 
"public interface	Rn_hl_test3_service{" + "\r\n" + 
"	List<Rn_hl_test3_header_t> getAll();" + "\r\n" + 
"	" + "\r\n" + 
"Rn_hl_test3_header_t	getById(int id);" + "\r\n" + 
"	" + "\r\n" + 
"Rn_hl_test3_header_t save(Rn_hl_test3_header_t rn_hl_test3_header_t);" + "\r\n" + 
"	" + "\r\n" + 
"Rn_hl_test3_header_t updateById(int id,Rn_hl_test3_header_t rn_hl_test3_header_t) throws ResourceNotFoundException;" + "\r\n" + 
"	" + "\r\n" + 
"	void deleteById(int id) throws ResourceNotFoundException;" + "\r\n" + 
"}" 
