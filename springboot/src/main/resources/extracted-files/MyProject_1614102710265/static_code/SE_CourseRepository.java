"package com.realnet." + module_name + ".repository;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import java.util.Optional;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.jpa.repository.JpaRepository;" + "\r\n" + 
"import org.springframework.stereotype.Repository;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.model.Course;" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface CourseRepository extends JpaRepository<Course, Integer> {" + "\r\n" + 
"	List<Course> findByInstructorId(Integer instructorId);" + "\r\n" + 
"	Optional<Course> findByIdAndInstructorId(Integer courseId, Integer instructorId);" + "\r\n" + 
"}" 
