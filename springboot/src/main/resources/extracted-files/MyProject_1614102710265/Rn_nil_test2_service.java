package com.realnet.service;

import java.util.List;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_nil_test2_header_t;

public interface	Rn_nil_test2_service{
	List<Rn_nil_test2_header_t> getAll();
	
Rn_nil_test2_header_t	getById(int id);
	
Rn_nil_test2_header_t save(Rn_nil_test2_header_t rn_nil_test2_header_t);
	
Rn_nil_test2_header_t updateById(int id,Rn_nil_test2_header_t rn_nil_test2_header_t) throws ResourceNotFoundException;
	
	void deleteById(int id) throws ResourceNotFoundException;
}