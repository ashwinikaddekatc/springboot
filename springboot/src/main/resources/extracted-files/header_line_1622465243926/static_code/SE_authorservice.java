"package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.author;" + "\r\n" + 
"" + "\r\n" + 
"public interface " + sbhlservice1 + "{"+
"" + "\r\n" + 
"	public Page<author> getAll(Pageable page);" + "\r\n" + 
"	public author getById(int id);" + "\r\n" + 
"	public author save(author teacher);" + "\r\n" + 
"	public author updateById(int id, author teacherRequest);" + "\r\n" + 
"	public boolean deleteById(int id);" + "\r\n" + 
"}" 
