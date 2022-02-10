package com.realnet.comm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.comm.entity.Department;
import com.realnet.comm.entity.Sales;
import com.realnet.comm.entity.Teacher;
import com.realnet.comm.repository.DepartmentRepo;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class Department_Service implements  DepartmentServiceImp {
	@Autowired
	private DepartmentRepo departmentRepo;

	@Override
	public List<Department> getAll() {
		// TODO Auto-generated method stub
		return departmentRepo.findAll();
	}

	@Override
	public Page<Department> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return departmentRepo.findAll(page);
	}

	@Override
	public Department getbyid(int id) {
		// TODO Auto-generated method stub
		Department department=departmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
		return department;
		
	}

	@Override
	public List<Department> save(List<Department> department) {
		// TODO Auto-generated method stub
		List<Department> savedDepartment = departmentRepo.saveAll(department);
		return savedDepartment;
	}

	public Department updateById(Integer id,@Valid Department department) {
		// TODO Auto-gene
		Department old_dept = departmentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found :: " + id));
			
		old_dept.setDname(department.getDname());
		old_dept.setDhead(department.getDhead());
		old_dept.setDcontact(department.getDcontact());
		old_dept.setEmpCount(department.getEmpCount());
		
		//old_sale.setSalesPerson(sale.getSalesPerson());
		final Department updated_dept = departmentRepo.save(old_dept);
		return updated_dept;
	}

	@Override
	public boolean deleteById(int id) {
		if (!departmentRepo.existsById(id)) {
			//logger.error("teacher not found with id = " + id);
			throw new ResourceNotFoundException("Teacher not found :: " + id);
		}

		Department department = departmentRepo.findById(id).orElse(null);
		departmentRepo.delete(department);
		return true;
	}

}
