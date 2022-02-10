"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_header_only_test1_t;" + "\r\n" + 
"import com.realnet.repository.Rn_header_only_test1_repository;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class	Rn_header_only_test1_serviceimpl	implements	Rn_header_only_test1_service {" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private	Rn_header_only_test1_repository rn_header_only_test1_repository;" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public List<Rn_header_only_test1_t> getAll() {" + "\r\n" + 
"		return	rn_header_only_test1_repository.findAll();" + "\r\n" + 
"}" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public	Rn_header_only_test1_t getById(int id) throws ResourceNotFoundException {" + "\r\n" + 
"		return	rn_header_only_test1_repository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\" Rn_header_only_test1_t not found :: \" + id));" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"//==================UPDATE DATA VARIABLE WILL BE PASS HERE==========" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public	Rn_header_only_test1_t updateById(int id, Rn_header_only_test1_t	value) throws ResourceNotFoundException {" + "\r\n" + 
"		Rn_header_only_test1_t rn_header_only_test1_t = rn_header_only_test1_repository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\" rn_header_only_test1_t not found :: \" + id));" + "\r\n" + 
"		" + "\r\n" + 
"rn_header_only_test1_t.setTextfield1(value.getTextfield1());" + "\r\n" + 
"rn_header_only_test1_t.setTextfield2(value.getTextfield2());" + "\r\n" + 
"rn_header_only_test1_t.setTextfield3(value.getTextfield3());" + "\r\n" + 
"rn_header_only_test1_t.setTextfield4(value.getTextfield4());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute1(value.getAttribute1());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute2(value.getAttribute2());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute3(value.getAttribute3());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute4(value.getAttribute4());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute5(value.getAttribute5());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute6(value.getAttribute6());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute7(value.getAttribute7());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute8(value.getAttribute8());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute9(value.getAttribute9());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute10(value.getAttribute10());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute11(value.getAttribute11());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute12(value.getAttribute12());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute13(value.getAttribute13());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute14(value.getAttribute14());" + "\r\n" + 
"rn_header_only_test1_t.setAttribute15(value.getAttribute15());" + "\r\n" + 
"rn_header_only_test1_t.setFlex1(value.getFlex1());" + "\r\n" + 
"rn_header_only_test1_t.setFlex2(value.getFlex2());" + "\r\n" + 
"rn_header_only_test1_t.setFlex3(value.getFlex3());" + "\r\n" + 
"rn_header_only_test1_t.setFlex4(value.getFlex4());" + "\r\n" + 
"rn_header_only_test1_t.setFlex5(value.getFlex5());" + "\r\n" + 
"		Rn_header_only_test1_t updatedRn_header_only_test1_t = rn_header_only_test1_repository.save(rn_header_only_test1_t);" + "\r\n" + 
"		return	updatedRn_header_only_test1_t;" + "\r\n" + 
"}" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public	Rn_header_only_test1_t save(Rn_header_only_test1_t rn_header_only_test1_t) {" + "\r\n" + 
"		return	rn_header_only_test1_repository.save(rn_header_only_test1_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public void deleteById(int id) throws ResourceNotFoundException {" + "\r\n" + 
"Rn_header_only_test1_t rn_header_only_test1_t = rn_header_only_test1_repository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\" rn_header_only_test1_t not found :: \" + id));" + "\r\n" + 
"		rn_header_only_test1_repository.delete(rn_header_only_test1_t);" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
