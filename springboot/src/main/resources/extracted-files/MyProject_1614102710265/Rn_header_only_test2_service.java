package com.realnet.service;

import java.util.List;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_header_only_test2_t;

public interface	Rn_header_only_test2_service{
	List<Rn_header_only_test2_t> getAll();
	
Rn_header_only_test2_t	getById(int id);
	
Rn_header_only_test2_t save(Rn_header_only_test2_t rn_header_only_test2_t);
	
Rn_header_only_test2_t updateById(int id,Rn_header_only_test2_t rn_header_only_test2_t) throws ResourceNotFoundException;
	
	void deleteById(int id) throws ResourceNotFoundException;
}