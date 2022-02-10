"package com.realnet.comm." + module_name + ".repository;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.data.jpa.repository.JpaRepository;" + "\r\n" + 
"import org.springframework.stereotype.Repository;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Sales;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface " + salesrepo1 + "{"+
"	" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	//public List<Sales> findAll();" + "\r\n" + 
"    public Page<Sales> findAll(Pageable p);" + "\r\n" + 
"" + "\r\n" + 
"    " + "\r\n" + 
"    " + "\r\n" + 
"    //    public Sales save(Sales sales);" + "\r\n" + 
"//   // public Sales findbyId(int id);" + "\r\n" + 
"//	//private List<Object> items;" + "\r\n" + 
"//    Sales  findById(int id);" + "\r\n" + 
"}" 
