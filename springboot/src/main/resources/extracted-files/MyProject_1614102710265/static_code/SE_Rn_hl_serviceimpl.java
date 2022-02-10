"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_hl_header_t;" + "\r\n" + 
"import com.realnet.repository.Rn_hl_repository;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class	Rn_hl_serviceimpl	implements	Rn_hl_service {" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private	Rn_hl_repository rn_hl_repository;" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public List<Rn_hl_header_t> getAll() {" + "\r\n" + 
"		return	rn_hl_repository.findAll();" + "\r\n" + 
"}" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public	Rn_hl_header_t getById(int id) throws ResourceNotFoundException {" + "\r\n" + 
"		return	rn_hl_repository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\" Rn_hl_header_t not found :: \" + id));" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"//==================UPDATE DATA VARIABLE WILL BE PASS HERE==========" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public	Rn_hl_header_t updateById(int id, Rn_hl_header_t	value) throws ResourceNotFoundException {" + "\r\n" + 
"		Rn_hl_header_t rn_hl_header_t = rn_hl_repository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\" rn_hl_header_t not found :: \" + id));" + "\r\n" + 
"		" + "\r\n" + 
"rn_hl_header_t.setTextfield1(value.getTextfield1());" + "\r\n" + 
"rn_hl_header_t.setTextfield2(value.getTextfield2());" + "\r\n" + 
"rn_hl_header_t.setTextfield3(value.getTextfield3());" + "\r\n" + 
"rn_hl_header_t.setTextfield4(value.getTextfield4());" + "\r\n" + 
"rn_hl_header_t.setAttribute1(value.getAttribute1());" + "\r\n" + 
"rn_hl_header_t.setAttribute2(value.getAttribute2());" + "\r\n" + 
"rn_hl_header_t.setAttribute3(value.getAttribute3());" + "\r\n" + 
"rn_hl_header_t.setAttribute4(value.getAttribute4());" + "\r\n" + 
"rn_hl_header_t.setAttribute5(value.getAttribute5());" + "\r\n" + 
"rn_hl_header_t.setAttribute6(value.getAttribute6());" + "\r\n" + 
"rn_hl_header_t.setAttribute7(value.getAttribute7());" + "\r\n" + 
"rn_hl_header_t.setAttribute8(value.getAttribute8());" + "\r\n" + 
"rn_hl_header_t.setAttribute9(value.getAttribute9());" + "\r\n" + 
"rn_hl_header_t.setAttribute10(value.getAttribute10());" + "\r\n" + 
"rn_hl_header_t.setAttribute11(value.getAttribute11());" + "\r\n" + 
"rn_hl_header_t.setAttribute12(value.getAttribute12());" + "\r\n" + 
"rn_hl_header_t.setAttribute13(value.getAttribute13());" + "\r\n" + 
"rn_hl_header_t.setAttribute14(value.getAttribute14());" + "\r\n" + 
"rn_hl_header_t.setAttribute15(value.getAttribute15());" + "\r\n" + 
"rn_hl_header_t.setFlex1(value.getFlex1());" + "\r\n" + 
"rn_hl_header_t.setFlex2(value.getFlex2());" + "\r\n" + 
"rn_hl_header_t.setFlex3(value.getFlex3());" + "\r\n" + 
"rn_hl_header_t.setFlex4(value.getFlex4());" + "\r\n" + 
"rn_hl_header_t.setFlex5(value.getFlex5());" + "\r\n" + 
"		Rn_hl_header_t updatedRn_hl_header_t = rn_hl_repository.save(rn_hl_header_t);" + "\r\n" + 
"		return	updatedRn_hl_header_t;" + "\r\n" + 
"}" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public	Rn_hl_header_t save(Rn_hl_header_t rn_hl_header_t) {" + "\r\n" + 
"		return	rn_hl_repository.save(rn_hl_header_t);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public void deleteById(int id) throws ResourceNotFoundException {" + "\r\n" + 
"Rn_hl_header_t rn_hl_header_t = rn_hl_repository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\" rn_hl_header_t not found :: \" + id));" + "\r\n" + 
"		rn_hl_repository.delete(rn_hl_header_t);" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
