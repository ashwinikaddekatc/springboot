package com.realnet.fnd.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realnet.fnd.entity.Rn_Dynamic_Transaction;
import com.realnet.fnd.entity.Rn_Dynamic_Transaction_line;

public interface Rn_Dynamic_Transaction_LineService {

	List<Rn_Dynamic_Transaction_line> getAll();
	
	Page<Rn_Dynamic_Transaction_line> getAll(Pageable p);
	
	List<Rn_Dynamic_Transaction_line> getByFormId(int form_id);

	Rn_Dynamic_Transaction_line getByIdAndFormId(int id, int form_id);
	 void buildDynamicForm(int form_id);

	
	
	List<Rn_Dynamic_Transaction_line> save(List<Rn_Dynamic_Transaction_line> rn_Dynamic_Transaction_line);
	
	Rn_Dynamic_Transaction_line updateByFormId(int id, int form_id, Rn_Dynamic_Transaction_line rn_Dynamic_Transaction_line);
	
	boolean deleteById(int id);

	Rn_Dynamic_Transaction_line getById(int id);

	

}
