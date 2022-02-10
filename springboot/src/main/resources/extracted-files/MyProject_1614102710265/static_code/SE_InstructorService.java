"package com.realnet." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Instructor;" + "\r\n" + 
"" + "\r\n" + 
"public interface InstructorService {" + "\r\n" + 
"" + "\r\n" + 
"	List<Instructor> getAllInstructor();" + "\r\n" + 
"" + "\r\n" + 
"	Instructor getInstructorById(int id);" + "\r\n" + 
"" + "\r\n" + 
"	Instructor saveInstructor(Instructor instructor);" + "\r\n" + 
"" + "\r\n" + 
"	Instructor updateInstructorById(int id, Instructor instructor) throws ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"	void deleteInstructorById(int id) throws ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"}" 
