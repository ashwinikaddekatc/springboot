package com.realnet.service;

import java.util.List;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_hl_test3_header_t;

public interface	Rn_hl_test3_service{
	List<Rn_hl_test3_header_t> getAll();
	
Rn_hl_test3_header_t	getById(int id);
	
Rn_hl_test3_header_t save(Rn_hl_test3_header_t rn_hl_test3_header_t);
	
Rn_hl_test3_header_t updateById(int id,Rn_hl_test3_header_t rn_hl_test3_header_t) throws ResourceNotFoundException;
	
	void deleteById(int id) throws ResourceNotFoundException;
}