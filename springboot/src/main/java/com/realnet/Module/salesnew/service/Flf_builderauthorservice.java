package com.realnet.Module.salesnew.service;

import com.realnet.Module.salesnew.entity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Flf_builderauthorservice{
	public Page<Flf_builderauthor> getAll(Pageable page);
	public Flf_builderauthor getById(int id);
	public Flf_builderauthor save(Flf_builderauthor teacher);
	public Flf_builderauthor updateById(int id, Flf_builderauthor teacherRequest);
	public boolean deleteById(int id);
}