package com.realnet.Module.salesnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.Module.salesnew.entity.*;
import com.realnet.Module.salesnew.repository.*;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class Flf_builderauthorserviceimpl  implements Flf_builderauthorservice{
	
	@Autowired
	private Flf_builderauthorrepo authrepo;




	public Page<Flf_builderauthor> getAll(Pageable page) {
		return authrepo.findAll(page);
	}


	public Flf_builderauthor getById(int id) {
		
		Flf_builderauthor teacher = authrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));

		return teacher;
	}

	
	public Flf_builderauthor save(Flf_builderauthor teacher) {
	
		Flf_builderauthor savedTeacher = authrepo.save(teacher);
		return savedTeacher;
	}


	public Flf_builderauthor updateById(int id, Flf_builderauthor teacherRequest) {
		Flf_builderauthor old_teacher = authrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
old_teacher.setTechstack(teacherRequest.getTechstack());
old_teacher.setObject_type(teacherRequest.getObject_type());
old_teacher.setSub_object_type(teacherRequest.getSub_object_type());
old_teacher.setIsbuild(teacherRequest.getIsbuild());
old_teacher.setBook(teacherRequest.getBook());
		
		
		final Flf_builderauthor updated_teacher = authrepo.save(old_teacher);

		return updated_teacher;
	}
	
	
	public boolean deleteById(int id) {
		if (!authrepo.existsById(id)) {
			throw new ResourceNotFoundException("author not found :: " + id);
		}
		
		Flf_builderauthor teacher = authrepo.findById(id).orElse(null);
		authrepo.delete(teacher);
		return true;
	}

}