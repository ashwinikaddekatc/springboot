package com.realnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_code_test_t;
import com.realnet.repository.Rn_code_test_repository;

@Service
public class	Rn_code_test_serviceimpl	implements	Rn_code_test_service {

	@Autowired
	private	Rn_code_test_repository rn_code_test_repository;

	@Override
	public List<Rn_code_test_t> getAll() {
		return	rn_code_test_repository.findAll();
}
	@Override
	public	Rn_code_test_t getById(int id) throws ResourceNotFoundException {
		return	rn_code_test_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" Rn_code_test_t not found :: " + id));
	}

//==================UPDATE DATA VARIABLE WILL BE PASS HERE==========
	@Override
	public	Rn_code_test_t updateById(int id, Rn_code_test_t	value) throws ResourceNotFoundException {
		Rn_code_test_t rn_code_test_t = rn_code_test_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" rn_code_test_t not found :: " + id));
		

rn_code_test_t.setTextfield1(value.getTextfield1());

rn_code_test_t.setTextfield2(value.getTextfield2());

rn_code_test_t.setTextfield3(value.getTextfield3());

rn_code_test_t.setTextfield4(value.getTextfield4());

rn_code_test_t.setAttribute1(value.getAttribute1());
rn_code_test_t.setAttribute2(value.getAttribute2());
rn_code_test_t.setAttribute3(value.getAttribute3());
rn_code_test_t.setAttribute4(value.getAttribute4());
rn_code_test_t.setAttribute5(value.getAttribute5());
rn_code_test_t.setAttribute6(value.getAttribute6());
rn_code_test_t.setAttribute7(value.getAttribute7());
rn_code_test_t.setAttribute8(value.getAttribute8());
rn_code_test_t.setAttribute9(value.getAttribute9());
rn_code_test_t.setAttribute10(value.getAttribute10());
rn_code_test_t.setAttribute11(value.getAttribute11());
rn_code_test_t.setAttribute12(value.getAttribute12());
rn_code_test_t.setAttribute13(value.getAttribute13());
rn_code_test_t.setAttribute14(value.getAttribute14());
rn_code_test_t.setAttribute15(value.getAttribute15());
rn_code_test_t.setFlex1(value.getFlex1());
rn_code_test_t.setFlex2(value.getFlex2());
rn_code_test_t.setFlex3(value.getFlex3());
rn_code_test_t.setFlex4(value.getFlex4());
rn_code_test_t.setFlex5(value.getFlex5());		Rn_code_test_t updatedRn_code_test_t = rn_code_test_repository.save(rn_code_test_t);
		return	updatedRn_code_test_t;
}
	@Override
	public	Rn_code_test_t save(Rn_code_test_t rn_code_test_t) {
		return	rn_code_test_repository.save(rn_code_test_t);
	}

	@Override
	public void deleteById(int id) throws ResourceNotFoundException {
Rn_code_test_t rn_code_test_t = rn_code_test_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" rn_code_test_t not found :: " + id));
		rn_code_test_repository.delete(rn_code_test_t);
	}
}