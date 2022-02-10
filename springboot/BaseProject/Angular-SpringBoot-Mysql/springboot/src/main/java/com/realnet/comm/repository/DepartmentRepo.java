package com.realnet.comm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.realnet.comm.entity.Customer;
import com.realnet.comm.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	public List<Department> findAll();
	public Page<Department> findAll(Pageable p);
	//public Department save(Department department); 
}
