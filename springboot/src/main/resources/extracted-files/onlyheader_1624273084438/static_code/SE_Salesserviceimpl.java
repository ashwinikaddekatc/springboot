"package com.realnet.comm." + module_name + ".service;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"import com.realnet.comm.repository.Salesrepository;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class " + sbohserviceimpl1 + "{"+
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	Salesrepository salesrepo;" + "\r\n" + 
"	" + "\r\n" + 
"" + "\r\n" + 
"	" + "\r\n" + 
"	//getting data" + "\r\n" + 
"	public Page<sales> getlist(Pageable page)" + "\r\n" + 
"	{" + "\r\n" + 
"		Page<sales> find = this.salesrepo.findAll(page);" + "\r\n" + 
"		return find;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//creating sales " + "\r\n" + 
"	public sales createsale(sales data)" + "\r\n" + 
"	{" + "\r\n" + 
"		sales save = this.salesrepo.save(data);" + "\r\n" + 
"		return save;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//get by id" + "\r\n" + 
"	public sales getid(int id)" + "\r\n" + 
"	{" + "\r\n" + 
"			" + "\r\n" + 
"			sales teacher = salesrepo.findById(id)" + "\r\n" + 
"					.orElseThrow(() -> new ResourceNotFoundException(\"Te not found :: \" + id));" + "\r\n" + 
"			return teacher;" + "\r\n" + 
"" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//update by id" + "\r\n" + 
"	public sales updateById(int id, sales sale) {" + "\r\n" + 
"		sales old_sale = salesrepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"		old_sale.setExtn1(sale.getExtn1());" + "\r\n" + 
"		old_sale .setExtn2(sale.getExtn2());" + "\r\n" + 
"		old_sale.setExtn3(sale.getExtn3());" + "\r\n" + 
"		old_sale.setExtn4(sale.getExtn4());" + "\r\n" + 
"		old_sale.setExtn5(sale.getExtn5());" + "\r\n" + 
"		old_sale.setExtn6(sale.getExtn6());" + "\r\n" + 
"		old_sale.setExtn7(sale.getExtn7());" + "\r\n" + 
"		old_sale.setExtn8(sale.getExtn8());" + "\r\n" + 
"		old_sale.setExtn9(sale.getExtn9());" + "\r\n" + 
"		old_sale.setExtn10(sale.getExtn10());" + "\r\n" + 
"		old_sale.setExtn11(sale.getExtn11());" + "\r\n" + 
"		old_sale.setExtn12(sale.getExtn12());" + "\r\n" + 
"		old_sale.setExtn13(sale.getExtn13());" + "\r\n" + 
"		old_sale.setExtn14(sale.getExtn14());" + "\r\n" + 
"		old_sale.setExtn15(sale.getExtn15());" + "\r\n" + 
"		old_sale.setFlex1(sale.getFlex1());" + "\r\n" + 
"		old_sale.setFlex2(sale.getFlex2());" + "\r\n" + 
"		old_sale.setFlex3(sale.getFlex3());" + "\r\n" + 
"		old_sale.setFlex4(sale.getFlex4());" + "\r\n" + 
"		old_sale.setFlex5(sale.getFlex5());" + "\r\n" + 
"			" + "\r\n" + 
"		final sales updated_sale = salesrepo.save(old_sale);" + "\r\n" + 
"" + "\r\n" + 
"		return updated_sale;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//delete" + "\r\n" + 
"	public boolean deleteById(int id) {" + "\r\n" + 
"		if (!salesrepo.existsById(id)) {" + "\r\n" + 
"			" + "\r\n" + 
"			throw new ResourceNotFoundException(\"Sales not found :: \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"		sales sale = salesrepo.findById(id).orElse(null);" + "\r\n" + 
"		salesrepo.delete(sale);" + "\r\n" + 
"		return true;" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
