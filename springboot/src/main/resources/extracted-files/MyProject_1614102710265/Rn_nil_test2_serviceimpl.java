package com.realnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_nil_test2_header_t;
import com.realnet.repository.Rn_nil_test2_repository;

@Service
public class	Rn_nil_test2_serviceimpl	implements	Rn_nil_test2_service {

	@Autowired
	private	Rn_nil_test2_repository rn_nil_test2_repository;

	@Override
	public List<Rn_nil_test2_header_t> getAll() {
		return	rn_nil_test2_repository.findAll();
}
	@Override
	public	Rn_nil_test2_header_t getById(int id) throws ResourceNotFoundException {
		return	rn_nil_test2_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" Rn_nil_test2_header_t not found :: " + id));
	}

//==================UPDATE DATA VARIABLE WILL BE PASS HERE==========
	@Override
	public	Rn_nil_test2_header_t updateById(int id, Rn_nil_test2_header_t	value) throws ResourceNotFoundException {
		Rn_nil_test2_header_t rn_nil_test2_header_t = rn_nil_test2_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" rn_nil_test2_header_t not found :: " + id));
		
rn_nil_test2_header_t.setTextfield1(value.getTextfield1());
rn_nil_test2_header_t.setTextfield2(value.getTextfield2());
rn_nil_test2_header_t.setTextfield3(value.getTextfield3());
rn_nil_test2_header_t.setTextfield4(value.getTextfield4());
rn_nil_test2_header_t.setAttribute1(value.getAttribute1());
rn_nil_test2_header_t.setAttribute2(value.getAttribute2());
rn_nil_test2_header_t.setAttribute3(value.getAttribute3());
rn_nil_test2_header_t.setAttribute4(value.getAttribute4());
rn_nil_test2_header_t.setAttribute5(value.getAttribute5());
rn_nil_test2_header_t.setAttribute6(value.getAttribute6());
rn_nil_test2_header_t.setAttribute7(value.getAttribute7());
rn_nil_test2_header_t.setAttribute8(value.getAttribute8());
rn_nil_test2_header_t.setAttribute9(value.getAttribute9());
rn_nil_test2_header_t.setAttribute10(value.getAttribute10());
rn_nil_test2_header_t.setAttribute11(value.getAttribute11());
rn_nil_test2_header_t.setAttribute12(value.getAttribute12());
rn_nil_test2_header_t.setAttribute13(value.getAttribute13());
rn_nil_test2_header_t.setAttribute14(value.getAttribute14());
rn_nil_test2_header_t.setAttribute15(value.getAttribute15());
rn_nil_test2_header_t.setFlex1(value.getFlex1());
rn_nil_test2_header_t.setFlex2(value.getFlex2());
rn_nil_test2_header_t.setFlex3(value.getFlex3());
rn_nil_test2_header_t.setFlex4(value.getFlex4());
rn_nil_test2_header_t.setFlex5(value.getFlex5());
		Rn_nil_test2_header_t updatedRn_nil_test2_header_t = rn_nil_test2_repository.save(rn_nil_test2_header_t);
		return	updatedRn_nil_test2_header_t;
}
	@Override
	public	Rn_nil_test2_header_t save(Rn_nil_test2_header_t rn_nil_test2_header_t) {
		return	rn_nil_test2_repository.save(rn_nil_test2_header_t);
	}

	@Override
	public void deleteById(int id) throws ResourceNotFoundException {
Rn_nil_test2_header_t rn_nil_test2_header_t = rn_nil_test2_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" rn_nil_test2_header_t not found :: " + id));
		rn_nil_test2_repository.delete(rn_nil_test2_header_t);
	}
}