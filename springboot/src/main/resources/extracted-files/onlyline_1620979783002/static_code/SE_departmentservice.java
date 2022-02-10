"package com.realnet.comm." + module_name + "." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Department;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"" + "\r\n" + 
"public interface " + sbolservice1 + "{"+
"    Page<Department> getAll(Pageable paging);" + "\r\n" + 
"    List<Department> save(List<Department> department);" + "\r\n" + 
"    Department getone(int id);" + "\r\n" + 
"    Department updatedept(int id,Department dept);" + "\r\n" + 
"    boolean delete(int id);" + "\r\n" + 
"" + "\r\n" + 
"}" 
