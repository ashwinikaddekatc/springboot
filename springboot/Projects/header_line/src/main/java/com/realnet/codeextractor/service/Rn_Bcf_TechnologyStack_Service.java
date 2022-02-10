package com.realnet.codeextractor.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realnet.codeextractor.entity.ActiveTechStack_DTO;
import com.realnet.codeextractor.entity.Rn_Bcf_Technology_Stack;

public interface Rn_Bcf_TechnologyStack_Service {
	List<Rn_Bcf_Technology_Stack> getAll();
	Page<Rn_Bcf_Technology_Stack> getAll(Pageable p);
	Rn_Bcf_Technology_Stack getById(int id);
	Rn_Bcf_Technology_Stack save(Rn_Bcf_Technology_Stack bcf_tech_stack);
	Rn_Bcf_Technology_Stack updateById(int id, Rn_Bcf_Technology_Stack bcf_tech_stack);
	boolean deleteById(int id);
	
	List<ActiveTechStack_DTO> getListOfActivateTechnology();

}
