package com.realnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_header_only_test2_t;
import com.realnet.repository.Rn_header_only_test2_repository;

@Service
public class	Rn_header_only_test2_serviceimpl	implements	Rn_header_only_test2_service {

	@Autowired
	private	Rn_header_only_test2_repository rn_header_only_test2_repository;

	@Override
	public List<Rn_header_only_test2_t> getAll() {
		return	rn_header_only_test2_repository.findAll();
}
	@Override
	public	Rn_header_only_test2_t getById(int id) throws ResourceNotFoundException {
		return	rn_header_only_test2_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" Rn_header_only_test2_t not found :: " + id));
	}

//==================UPDATE DATA VARIABLE WILL BE PASS HERE==========
	@Override
	public	Rn_header_only_test2_t updateById(int id, Rn_header_only_test2_t	value) throws ResourceNotFoundException {
		Rn_header_only_test2_t rn_header_only_test2_t = rn_header_only_test2_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" rn_header_only_test2_t not found :: " + id));
		
rn_header_only_test2_t.setTextfield1(value.getTextfield1());
rn_header_only_test2_t.setTextfield2(value.getTextfield2());
rn_header_only_test2_t.setTextfield3(value.getTextfield3());
rn_header_only_test2_t.setTextfield4(value.getTextfield4());
rn_header_only_test2_t.setAttribute1(value.getAttribute1());
rn_header_only_test2_t.setAttribute2(value.getAttribute2());
rn_header_only_test2_t.setAttribute3(value.getAttribute3());
rn_header_only_test2_t.setAttribute4(value.getAttribute4());
rn_header_only_test2_t.setAttribute5(value.getAttribute5());
rn_header_only_test2_t.setAttribute6(value.getAttribute6());
rn_header_only_test2_t.setAttribute7(value.getAttribute7());
rn_header_only_test2_t.setAttribute8(value.getAttribute8());
rn_header_only_test2_t.setAttribute9(value.getAttribute9());
rn_header_only_test2_t.setAttribute10(value.getAttribute10());
rn_header_only_test2_t.setAttribute11(value.getAttribute11());
rn_header_only_test2_t.setAttribute12(value.getAttribute12());
rn_header_only_test2_t.setAttribute13(value.getAttribute13());
rn_header_only_test2_t.setAttribute14(value.getAttribute14());
rn_header_only_test2_t.setAttribute15(value.getAttribute15());
rn_header_only_test2_t.setFlex1(value.getFlex1());
rn_header_only_test2_t.setFlex2(value.getFlex2());
rn_header_only_test2_t.setFlex3(value.getFlex3());
rn_header_only_test2_t.setFlex4(value.getFlex4());
rn_header_only_test2_t.setFlex5(value.getFlex5());
		Rn_header_only_test2_t updatedRn_header_only_test2_t = rn_header_only_test2_repository.save(rn_header_only_test2_t);
		return	updatedRn_header_only_test2_t;
}
	@Override
	public	Rn_header_only_test2_t save(Rn_header_only_test2_t rn_header_only_test2_t) {
		return	rn_header_only_test2_repository.save(rn_header_only_test2_t);
	}

	@Override
	public void deleteById(int id) throws ResourceNotFoundException {
Rn_header_only_test2_t rn_header_only_test2_t = rn_header_only_test2_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(" rn_header_only_test2_t not found :: " + id));
		rn_header_only_test2_repository.delete(rn_header_only_test2_t);
	}
}