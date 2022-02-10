package com.realnet.comm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.realnet.comm.entity.author;

public interface authorservice {

	public Page<author> getAll(Pageable page);
	public author getById(int id);
	public author save(author teacher);
	public author updateById(int id, author teacherRequest);
	public boolean deleteById(int id);
}
