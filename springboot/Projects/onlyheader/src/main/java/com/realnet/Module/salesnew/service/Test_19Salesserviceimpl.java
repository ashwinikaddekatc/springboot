package com.realnet.comm.header.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.comm.entity.sales;
import com.realnet.comm.repository.Salesrepository;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class Test_19Salesserviceimpl{
	@Autowired
	Salesrepository salesrepo;
	

	
	//getting data
	public Page<sales> getlist(Pageable page)
	{
		Page<sales> find = this.salesrepo.findAll(page);
		return find;
	}
	
	//creating sales 
	public sales createsale(sales data)
	{
		sales save = this.salesrepo.save(data);
		return save;
	}
	
	//get by id
	public sales getid(int id)
	{
			
			sales teacher = salesrepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Te not found :: " + id));
			return teacher;

	}
	
	//update by id
	public sales updateById(int id, sales sale) {
		sales old_sale = salesrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));

old_sale.setLabel1(sale.getLabel1());
old_sale.setLabel2(sale.getLabel2());
old_sale.setLabel3(sale.getLabel3());
old_sale.setLabel4(sale.getLabel4());

		final sales updated_sale = salesrepo.save(old_sale);

		return updated_sale;
	}
	
	//delete
	public boolean deleteById(int id) {
		if (!salesrepo.existsById(id)) {
			
			throw new ResourceNotFoundException("Sales not found :: " + id);
		}
		
		sales sale = salesrepo.findById(id).orElse(null);
		salesrepo.delete(sale);
		return true;
	}
}