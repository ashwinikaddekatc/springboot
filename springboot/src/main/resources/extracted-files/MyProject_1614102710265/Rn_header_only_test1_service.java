package com.realnet.service;

import java.util.List;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_header_only_test1_t;

public interface	Rn_header_only_test1_service{
	List<Rn_header_only_test1_t> getAll();
	
Rn_header_only_test1_t	getById(int id);
	
Rn_header_only_test1_t save(Rn_header_only_test1_t rn_header_only_test1_t);
	
Rn_header_only_test1_t updateById(int id,Rn_header_only_test1_t rn_header_only_test1_t) throws ResourceNotFoundException;
	
	void deleteById(int id) throws ResourceNotFoundException;
}