package com.realnet.comm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.comm.entity.Teacher;
import com.realnet.comm.entity.author;
import com.realnet.comm.repository.TeacherRepository;
import com.realnet.comm.repository.authorrepo;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class authorserviceimpl implements authorservice {

	
	@Autowired
	private authorrepo authrepo;




	public Page<author> getAll(Pageable page) {
		return authrepo.findAll(page);
	}


	public author getById(int id) {
		
		author teacher = authrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));

		return teacher;
	}

	
	public author save(author teacher) {
	
		author savedTeacher = authrepo.save(teacher);
		return savedTeacher;
	}


	public author updateById(int id, author teacherRequest) {
		author old_teacher = authrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
		old_teacher.setAfname(teacherRequest.getAfname());
		old_teacher.setLname(teacherRequest.getLname());
		old_teacher.setBook(teacherRequest.getBook());
		
		
		final author updated_teacher = authrepo.save(old_teacher);

		return updated_teacher;
	}
	
	
	public boolean deleteById(int id) {
		if (!authrepo.existsById(id)) {
			throw new ResourceNotFoundException("author not found :: " + id);
		}
		
		author teacher = authrepo.findById(id).orElse(null);
		authrepo.delete(teacher);
		return true;
	}

}
