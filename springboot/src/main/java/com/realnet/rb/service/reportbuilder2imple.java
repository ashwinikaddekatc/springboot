package com.realnet.rb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.comm.entity.Rn_Instructor;
import com.realnet.comm.repository.Rn_Instructor_Repository;
import com.realnet.comm.service.Rn_Instructor_Service;

@Service
public class reportbuilder2imple implements reportbuilder2 {

	@Autowired
	private  Rn_Instructor_Service rninstructor;
	
	@Autowired
	private Rn_Instructor_Repository repo;
	@Override
	public  List<Rn_Instructor> getall() {
				
		List<Rn_Instructor> findAll = repo.findAll();
			
		return findAll;
	}


}
