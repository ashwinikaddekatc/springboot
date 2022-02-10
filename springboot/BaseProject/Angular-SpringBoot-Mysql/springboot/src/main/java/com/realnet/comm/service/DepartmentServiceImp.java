package com.realnet.comm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realnet.comm.entity.Department;

public interface DepartmentServiceImp {
	List<Department> getAll();
	Page<Department> getAll(Pageable p);
	Department getbyid(int id);
	List<Department> save(List<Department> department);
	boolean deleteById(int id);
	
	

}
