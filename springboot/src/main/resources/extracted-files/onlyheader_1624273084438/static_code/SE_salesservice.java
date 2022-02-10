"package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"" + "\r\n" + 
"public interface " + sbohservice1 + "{"+
"" + "\r\n" + 
"    public Page<sales> getlist(Pageable page);" + "\r\n" + 
"    public sales createsale(sales data);" + "\r\n" + 
"    public sales getid(int id);" + "\r\n" + 
"    public sales updateById(int id, sales sale);" + "\r\n" + 
"    public boolean deleteById(int id);" + "\r\n" + 
"    " + "\r\n" + 
"}" 
