package com.realnet.comm.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realnet.comm.entity.Department;
import com.realnet.comm.entity.Fields;
import com.realnet.comm.entity.Teacher;

public interface FieldService {
	List<Fields> getAll();
	Page<Fields> getAll(Pageable p);
	Fields getbyid(int id);
	List<Fields> save(List<Fields> fields);
	boolean deleteById(int id);

}
