"package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import javax.transaction.Transactional;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Teacher;" + "\r\n" + 
"import com.realnet.comm.entity.author;" + "\r\n" + 
"import com.realnet.comm.repository.TeacherRepository;" + "\r\n" + 
"import com.realnet.comm.repository.authorrepo;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class " + sbhlserviceimpl1 + "{"+
"" + "\r\n" + 
"	" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private authorrepo authrepo;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public Page<author> getAll(Pageable page) {" + "\r\n" + 
"		return authrepo.findAll(page);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public author getById(int id) {" + "\r\n" + 
"		" + "\r\n" + 
"		author teacher = authrepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"" + "\r\n" + 
"		return teacher;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"	public author save(author teacher) {" + "\r\n" + 
"	" + "\r\n" + 
"		author savedTeacher = authrepo.save(teacher);" + "\r\n" + 
"		return savedTeacher;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"	public author updateById(int id, author teacherRequest) {" + "\r\n" + 
"		author old_teacher = authrepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"		old_teacher.setAfname(teacherRequest.getAfname());" + "\r\n" + 
"		old_teacher.setLname(teacherRequest.getLname());" + "\r\n" + 
"		old_teacher.setBook(teacherRequest.getBook());" + "\r\n" + 
"		" + "\r\n" + 
"		" + "\r\n" + 
"		final author updated_teacher = authrepo.save(old_teacher);" + "\r\n" + 
"" + "\r\n" + 
"		return updated_teacher;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	public boolean deleteById(int id) {" + "\r\n" + 
"		if (!authrepo.existsById(id)) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"author not found :: \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"		author teacher = authrepo.findById(id).orElse(null);" + "\r\n" + 
"		authrepo.delete(teacher);" + "\r\n" + 
"		return true;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
