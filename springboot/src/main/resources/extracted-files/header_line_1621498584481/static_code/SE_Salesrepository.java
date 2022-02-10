"package com.realnet.comm." + module_name + ".repository;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.data.jpa.repository.JpaRepository;" + "\r\n" + 
"import org.springframework.stereotype.Repository;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface " + sbhlrepository1 + "{"+
"" + "\r\n" + 
"	// for pagination" + "\r\n" + 
"		Page<sales> findAll(Pageable p);" + "\r\n" + 
"}" 
