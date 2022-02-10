package com.realnet.comm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.realnet.comm.entity.Department;
import com.realnet.comm.entity.Fields;

//import antlr.collections.List;

public interface FieldsRepo extends JpaRepository<Fields, Integer> {
	public List<Fields> findAll();
	public Page<Fields> findAll(Pageable p);
	
}
