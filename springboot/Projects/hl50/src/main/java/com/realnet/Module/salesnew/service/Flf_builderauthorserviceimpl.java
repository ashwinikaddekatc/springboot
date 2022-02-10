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
		old_teacher.setExtn1(sale.getExtn1());
		old_teacher .setExtn2(sale.getExtn2());
		old_teacher.setExtn3(sale.getExtn3());
		old_teacher.setExtn4(sale.getExtn4());
		old_teacher.setExtn5(sale.getExtn5());
		old_teacher.setExtn6(sale.getExtn6());
		old_teacher.setExtn7(sale.getExtn7());
		old_teacher.setExtn8(sale.getExtn8());
		old_teacher.setExtn9(sale.getExtn9());
		old_teacher.setExtn10(sale.getExtn10());
		old_teacher.setExtn11(sale.getExtn11());
		old_teacher.setExtn12(sale.getExtn12());
		old_teacher.setExtn13(sale.getExtn13());
		old_teacher.setExtn14(sale.getExtn14());
		old_teacher.setExtn15(sale.getExtn15());
		old_teacher.setFlex1(sale.getFlex1());
		old_teacher.setFlex2(sale.getFlex2());
		old_teacher.setFlex3(sale.getFlex3());
		old_teacher.setFlex4(sale.getFlex4());
		old_teacher.setFlex5(sale.getFlex5());
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