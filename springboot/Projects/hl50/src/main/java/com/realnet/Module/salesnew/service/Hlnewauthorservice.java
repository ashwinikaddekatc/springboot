package com.realnet.Module.salesnew.service;

import com.realnet.Module.salesnew.entity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Hlnewauthorservice{
	public Page<Hlnewauthor> getAll(Pageable page);
	public Hlnewauthor getById(int id);
	public Hlnewauthor save(Hlnewauthor teacher);
	public Hlnewauthor updateById(int id, Hlnewauthor teacherRequest);
	public boolean deleteById(int id);
}