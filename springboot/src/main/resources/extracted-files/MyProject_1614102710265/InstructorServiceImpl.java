package com.realnet.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Instructor;
import com.realnet.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService {

	@Autowired
	private InstructorRepository instructorRepository;

	@Override
	public List<Instructor> getAllInstructor() {
		return instructorRepository.findAll();
	}

	@Override
	public Instructor getInstructorById(int id) throws ResourceNotFoundException {
		return instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + id));
	}

	@Override
	public Instructor updateInstructorById(int id, Instructor oldInstructor) throws ResourceNotFoundException {
		Instructor instructor = instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + id));
		instructor.setFirstName(oldInstructor.getFirstName());
		instructor.setLastName(oldInstructor.getLastName());
		instructor.setEmail(oldInstructor.getEmail());
		final Instructor updatedUser = instructorRepository.save(instructor);
		return updatedUser;
	}

	@Override
	public Instructor saveInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}

	@Override
	public void deleteInstructorById(int id) throws ResourceNotFoundException {
		Instructor instructor = instructorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Instructor not found :: " + id));
		instructorRepository.delete(instructor);
	}

}
