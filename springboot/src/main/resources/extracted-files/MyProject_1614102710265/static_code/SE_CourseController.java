"package com.realnet." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.web.bind.annotation.CrossOrigin;" + "\r\n" + 
"import org.springframework.web.bind.annotation.DeleteMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PathVariable;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PutMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestBody;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Course;" + "\r\n" + 
"import com.realnet.repository.CourseRepository;" + "\r\n" + 
"import com.realnet.repository.InstructorRepository;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class CourseController {" + "\r\n" + 
"" + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    private CourseRepository courseRepository;" + "\r\n" + 
"" + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    private InstructorRepository instructorRepository;" + "\r\n" + 
"" + "\r\n" + 
"    @GetMapping(\"/instructors/{instructorId}/courses\")" + "\r\n" + 
"    public List <Course> getCoursesByInstructor(@PathVariable(value = \"instructorId\") Integer instructorId) {" + "\r\n" + 
"        return courseRepository.findByInstructorId(instructorId);" + "\r\n" + 
"    }" + "\r\n" + 
"    @GetMapping(\"/instructors/{instructorId}/courses/{courseId}\")" + "\r\n" + 
"    public Course getCoursesByInstructorAndCourseId(" + "\r\n" + 
"    		@PathVariable(value = \"instructorId\") Integer instructorId," + "\r\n" + 
"            @PathVariable(value = \"courseId\") Integer courseId) {" + "\r\n" + 
"    	if (!instructorRepository.existsById(instructorId)) {" + "\r\n" + 
"            throw new ResourceNotFoundException(\"instructorId not found\");" + "\r\n" + 
"        }" + "\r\n" + 
"        return courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException(\"course id not found\"));" + "\r\n" + 
"    }" + "\r\n" + 
"    " + "\r\n" + 
"    @PostMapping(\"/instructors/{instructorId}/courses\")" + "\r\n" + 
"    public Course createCourse(@PathVariable(value = \"instructorId\") Integer instructorId," + "\r\n" + 
"        @Valid @RequestBody Course course) throws ResourceNotFoundException {" + "\r\n" + 
"        return instructorRepository.findById(instructorId).map(instructor -> {" + "\r\n" + 
"            course.setInstructor(instructor);" + "\r\n" + 
"            return courseRepository.save(course);" + "\r\n" + 
"        }).orElseThrow(() -> new ResourceNotFoundException(\"instructor not found\"));" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @PutMapping(\"/instructors/{instructorId}/courses/{courseId}\")" + "\r\n" + 
"    public Course updateCourse(@PathVariable(value = \"instructorId\") Integer instructorId," + "\r\n" + 
"        @PathVariable(value = \"courseId\") Integer courseId, @Valid @RequestBody Course courseRequest)" + "\r\n" + 
"    throws ResourceNotFoundException {" + "\r\n" + 
"        if (!instructorRepository.existsById(instructorId)) {" + "\r\n" + 
"            throw new ResourceNotFoundException(\"instructorId not found\");" + "\r\n" + 
"        }" + "\r\n" + 
"" + "\r\n" + 
"        return courseRepository.findById(courseId).map(course -> {" + "\r\n" + 
"            course.setTitle(courseRequest.getTitle());" + "\r\n" + 
"            return courseRepository.save(course);" + "\r\n" + 
"        }).orElseThrow(() -> new ResourceNotFoundException(\"course id not found\"));" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"    @DeleteMapping(\"/instructors/{instructorId}/courses/{courseId}\")" + "\r\n" + 
"    public ResponseEntity<?> deleteCourse(@PathVariable(value = \"instructorId\") Integer instructorId," + "\r\n" + 
"        @PathVariable(value = \"courseId\") Integer courseId) throws ResourceNotFoundException {" + "\r\n" + 
"        return courseRepository.findByIdAndInstructorId(courseId, instructorId).map(course -> {" + "\r\n" + 
"            courseRepository.delete(course);" + "\r\n" + 
"            return ResponseEntity.ok().build();" + "\r\n" + 
"        }).orElseThrow(() -> new ResourceNotFoundException(" + "\r\n" + 
"            \"Course not found with id = \" + courseId + \" and instructorId \" + instructorId));" + "\r\n" + 
"    }" + "\r\n" + 
"}" 
