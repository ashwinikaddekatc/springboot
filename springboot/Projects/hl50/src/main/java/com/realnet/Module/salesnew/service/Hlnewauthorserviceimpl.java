package com.realnet.Module.salesnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.realnet.Module.salesnew.entity.*;
import com.realnet.Module.salesnew.repository.*;

import com.realnet.Module.salesnew.entity.Hlnewauthor;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class Hlnewauthorserviceimpl  implements Hlnewauthorservice{
	
	@Autowired
	private Hlnewauthorrepo authrepo;




	public Page<Hlnewauthor> getAll(Pageable page) {
		return authrepo.findAll(page);
	}


	public Hlnewauthor getById(int id) {
		
		Hlnewauthor teacher = authrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));

		return teacher;
	}

	
	public Hlnewauthor save(Hlnewauthor teacher) {
	
		Hlnewauthor savedTeacher = authrepo.save(teacher);
		return savedTeacher;
	}


	public Hlnewauthor updateById(int id, Hlnewauthor teacherRequest) {
		Hlnewauthor old_teacher = authrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
		old_teacher.setExtn1(teacherRequest.getExtn1());
		old_teacher .setExtn2(teacherRequest.getExtn2());
		old_teacher.setExtn3(teacherRequest.getExtn3());
		old_teacher.setExtn4(teacherRequest.getExtn4());
		old_teacher.setExtn5(teacherRequest.getExtn5());
		old_teacher.setExtn6(teacherRequest.getExtn6());
		old_teacher.setExtn7(teacherRequest.getExtn7());
		old_teacher.setExtn8(teacherRequest.getExtn8());
		old_teacher.setExtn9(teacherRequest.getExtn9());
		old_teacher.setExtn10(teacherRequest.getExtn10());
		old_teacher.setExtn11(teacherRequest.getExtn11());
		old_teacher.setExtn12(teacherRequest.getExtn12());
		old_teacher.setExtn13(teacherRequest.getExtn13());
		old_teacher.setExtn14(teacherRequest.getExtn14());
		old_teacher.setExtn15(teacherRequest.getExtn15());
		old_teacher.setFlex1(teacherRequest.getFlex1());
		old_teacher.setFlex2(teacherRequest.getFlex2());
		old_teacher.setFlex3(teacherRequest.getFlex3());
		old_teacher.setFlex4(teacherRequest.getFlex4());
		old_teacher.setFlex5(teacherRequest.getFlex5());
old_teacher.setLabel1(teacherRequest.getLabel1());
old_teacher.setLabel2(teacherRequest.getLabel2());
old_teacher.setLabel3(teacherRequest.getLabel3());
old_teacher.setBook(teacherRequest.getBook());
		
		
		final Hlnewauthor updated_teacher = authrepo.save(old_teacher);

		return updated_teacher;
	}
	
	
	public boolean deleteById(int id) {
		if (!authrepo.existsById(id)) {
			throw new ResourceNotFoundException("author not found :: " + id);
		}
		
		Hlnewauthor teacher = authrepo.findById(id).orElse(null);
		authrepo.delete(teacher);
		return true;
	}

}