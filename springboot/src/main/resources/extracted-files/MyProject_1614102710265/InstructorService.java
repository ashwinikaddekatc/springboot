package com.realnet.service;

import java.util.List;
import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Instructor;

public interface InstructorService {

	List<Instructor> getAllInstructor();

	Instructor getInstructorById(int id);

	Instructor saveInstructor(Instructor instructor);

	Instructor updateInstructorById(int id, Instructor instructor) throws ResourceNotFoundException;

	void deleteInstructorById(int id) throws ResourceNotFoundException;

}
