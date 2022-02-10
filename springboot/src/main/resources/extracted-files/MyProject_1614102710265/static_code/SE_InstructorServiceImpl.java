"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Instructor;" + "\r\n" + 
"import com.realnet.repository.InstructorRepository;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class InstructorServiceImpl implements InstructorService {" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private InstructorRepository instructorRepository;" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public List<Instructor> getAllInstructor() {" + "\r\n" + 
"		return instructorRepository.findAll();" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public Instructor getInstructorById(int id) throws ResourceNotFoundException {" + "\r\n" + 
"		return instructorRepository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Instructor not found :: \" + id));" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public Instructor updateInstructorById(int id, Instructor oldInstructor) throws ResourceNotFoundException {" + "\r\n" + 
"		Instructor instructor = instructorRepository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Instructor not found :: \" + id));" + "\r\n" + 
"		instructor.setFirstName(oldInstructor.getFirstName());" + "\r\n" + 
"		instructor.setLastName(oldInstructor.getLastName());" + "\r\n" + 
"		instructor.setEmail(oldInstructor.getEmail());" + "\r\n" + 
"		final Instructor updatedUser = instructorRepository.save(instructor);" + "\r\n" + 
"		return updatedUser;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public Instructor saveInstructor(Instructor instructor) {" + "\r\n" + 
"		return instructorRepository.save(instructor);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public void deleteInstructorById(int id) throws ResourceNotFoundException {" + "\r\n" + 
"		Instructor instructor = instructorRepository.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Instructor not found :: \" + id));" + "\r\n" + 
"		instructorRepository.delete(instructor);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
