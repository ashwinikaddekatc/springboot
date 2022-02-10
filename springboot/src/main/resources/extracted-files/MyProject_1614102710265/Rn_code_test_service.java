package com.realnet.service;

import java.util.List;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_code_test_t;

public interface	Rn_code_test_service{
	List<Rn_code_test_t> getAll();
	
Rn_code_test_t	getById(int id);
	
Rn_code_test_t save(Rn_code_test_t rn_code_test_t);
	
Rn_code_test_t updateById(int id,Rn_code_test_t rn_code_test_t) throws ResourceNotFoundException;
	
	void deleteById(int id) throws ResourceNotFoundException;
}