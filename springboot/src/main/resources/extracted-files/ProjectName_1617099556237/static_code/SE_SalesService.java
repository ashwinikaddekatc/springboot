"package com.realnet.comm.service;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.data.domain.Page;" + "\r\n" + 
"import org.springframework.data.domain.Pageable;" + "\r\n" + 
"import org.springframework.stereotype.Service;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Sales;" + "\r\n" + 
"import com.realnet.comm.entity.Teacher;" + "\r\n" + 
"import com.realnet.comm.repository.SalesRepo;" + "\r\n" + 
"import com.realnet.exceptions.ResourceNotFoundException;" + "\r\n" + 
"" + "\r\n" + 
"import antlr.collections.List;" + "\r\n" + 
"" + "\r\n" + 
"@Service" + "\r\n" + 
"public class SalesService implements SaleServiceInterface{" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	SalesRepo salesRepo;" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	//creating sales" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public Sales createsale(Sales data)" + "\r\n" + 
"	{" + "\r\n" + 
"		Sales save = this.salesRepo.save(data);" + "\r\n" + 
"		return save;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	@Override" + "\r\n" + 
"	public Page<Sales> getAll(Pageable page) {" + "\r\n" + 
"		return salesRepo.findAll(page);" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	//byid" + "\r\n" + 
"	public Sales getById(int id) {" + "\r\n" + 
"		//logger.info(\"getById() method start\");" + "\r\n" + 
"		Sales sales = salesRepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Sales not found :: \" + id));" + "\r\n" + 
"//		if (teacher == null || id != teacher.getId()) {" + "\r\n" + 
"//			logger.error(\"teacher not found with id = \" + id);" + "\r\n" + 
"//			logger.debug(teacher.toString());" + "\r\n" + 
"//		}" + "\r\n" + 
"//		logger.debug(teacher.toString());" + "\r\n" + 
"//		logger.info(\"getById() method end\");" + "\r\n" + 
"		return sales;" + "\r\n" + 
"	}" + "\r\n" + 
"//	//update method" + "\r\n" + 
"//	public Sales updateById(int id, Sales salesRequest) {" + "\r\n" + 
"//		Sales old_Sales = salesRepo.findById(id)" + "\r\n" + 
"//				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"//		old_Sales.setName(salesRequest.getName());" + "\r\n" + 
"//		old_Sales.setDepartment(salesRequest.getDepartment());" + "\r\n" + 
"//		old_Sales.setJoining_date(salesRequest.getJoining_date());" + "\r\n" + 
"//		old_Sales.setStatus(salesRequest.getStatus());" + "\r\n" + 
"//		final Sales updated_sales = salesRepo.save(old_Sales);" + "\r\n" + 
"////		logger.debug(\"updated data = \" + updated_teacher.toString());" + "\r\n" + 
"////		logger.info(this.className + \" updateById() method end\");" + "\r\n" + 
"//		return updated_sales;" + "\r\n" + 
"//	}" + "\r\n" + 
"	" + "\r\n" + 
"	" + "\r\n" + 
"	public Sales updateById(int id, Sales sale) {" + "\r\n" + 
"		Sales old_sale = salesRepo.findById(id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"Teacher not found :: \" + id));" + "\r\n" + 
"			" + "\r\n" + 
"		old_sale.setName(sale.getName());" + "\r\n" + 
"		old_sale.setDepartment(sale.getDepartment());" + "\r\n" + 
"		old_sale.setJoining_date(sale.getJoining_date());" + "\r\n" + 
"		old_sale.setStatus(sale.getStatus());" + "\r\n" + 
"		" + "\r\n" + 
"		old_sale.setSalesPerson(sale.getSalesPerson());" + "\r\n" + 
"		final Sales updated_sale = salesRepo.save(old_sale);" + "\r\n" + 
"		return updated_sale;" + "\r\n" + 
"	}" + "\r\n" + 
"	" + "\r\n" + 
"	public boolean deleteById(int id) {" + "\r\n" + 
"		if (!salesRepo.existsById(id)) {" + "\r\n" + 
"			//logger.error(\"teacher not found with id = \" + id);" + "\r\n" + 
"			throw new ResourceNotFoundException(\"Teacher not found :: \" + id);" + "\r\n" + 
"		}" + "\r\n" + 
"		" + "\r\n" + 
"//		logger.info(this.className + \" deleteById() method start\");" + "\r\n" + 
"//		logger.debug(\"Input id = \" + id);" + "\r\n" + 
"		Sales sales = salesRepo.findById(id).orElse(null);" + "\r\n" + 
"//		logger.debug(\"deleted data = \" + teacher.toString());" + "\r\n" + 
"//		logger.info(this.className + \" deleteById() method end\");" + "\r\n" + 
"		salesRepo.delete(sales);" + "\r\n" + 
"		return true;" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"}" 