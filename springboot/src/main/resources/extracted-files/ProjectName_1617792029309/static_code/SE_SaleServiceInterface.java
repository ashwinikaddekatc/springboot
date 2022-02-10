"package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Sales;" + "\r\n" + 
"" + "\r\n" + 
"public interface " + salesserviceinterface1 + "{"+
"	public Page<Sales> getAll(Pageable page);" + "\r\n" + 
"	public Sales createsale(Sales data);" + "\r\n" + 
"}" 
