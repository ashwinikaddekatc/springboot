package com.realnet.comm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.comm.entity.Sales;
import com.realnet.comm.entity.Teacher;
import com.realnet.comm.repository.SalesRepo;
import com.realnet.exceptions.ResourceNotFoundException;

import antlr.collections.List;

@Service
public class SalesService implements SaleServiceInterface{
	
	
	@Autowired
	SalesRepo salesRepo;
	
	
	//creating sales
	@Override
	public Sales createsale(Sales data)
	{
		Sales save = this.salesRepo.save(data);
		return save;
	}
	
	@Override
	public Page<Sales> getAll(Pageable page) {
		return salesRepo.findAll(page);
	}
	
	//byid
	public Sales getById(int id) {
		//logger.info("getById() method start");
		Sales sales = salesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Sales not found :: " + id));
//		if (teacher == null || id != teacher.getId()) {
//			logger.error("teacher not found with id = " + id);
//			logger.debug(teacher.toString());
//		}
//		logger.debug(teacher.toString());
//		logger.info("getById() method end");
		return sales;
	}
//	//update method
//	public Sales updateById(int id, Sales salesRequest) {
//		Sales old_Sales = salesRepo.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
//		old_Sales.setName(salesRequest.getName());
//		old_Sales.setDepartment(salesRequest.getDepartment());
//		old_Sales.setJoining_date(salesRequest.getJoining_date());
//		old_Sales.setStatus(salesRequest.getStatus());
//		final Sales updated_sales = salesRepo.save(old_Sales);
////		logger.debug("updated data = " + updated_teacher.toString());
////		logger.info(this.className + " updateById() method end");
//		return updated_sales;
//	}
	
	
	public Sales updateById(int id, Sales sale) {
		Sales old_sale = salesRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
			
		old_sale.setName(sale.getName());
		old_sale.setDepartment(sale.getDepartment());
		old_sale.setJoining_date(sale.getJoining_date());
		old_sale.setStatus(sale.getStatus());
		
		old_sale.setSalesPerson(sale.getSalesPerson());
		final Sales updated_sale = salesRepo.save(old_sale);
		return updated_sale;
	}
	
	public boolean deleteById(int id) {
		if (!salesRepo.existsById(id)) {
			//logger.error("teacher not found with id = " + id);
			throw new ResourceNotFoundException("Teacher not found :: " + id);
		}
		
//		logger.info(this.className + " deleteById() method start");
//		logger.debug("Input id = " + id);
		Sales sales = salesRepo.findById(id).orElse(null);
//		logger.debug("deleted data = " + teacher.toString());
//		logger.info(this.className + " deleteById() method end");
		salesRepo.delete(sales);
		return true;
	}


}
