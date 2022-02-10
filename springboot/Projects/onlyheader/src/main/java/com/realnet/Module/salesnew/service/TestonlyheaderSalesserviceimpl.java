package com.realnet.Module.salesnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import  com.realnet.Module.salesnew.entity.*;
import  com.realnet.Module.salesnew.repository.*;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class TestonlyheaderSalesserviceimpl implements Testonlyheadersalesservice{
	@Autowired
   private	TestonlyheaderSalesrepository salesrepo;
	

	
	//getting data
	public Page<Testonlyheadersales> getlist(Pageable page)
	{
		Page<Testonlyheadersales> find = this.salesrepo.findAll(page);
		return find;
	}
	
	//creating sales 
	public Testonlyheadersales createsale(Testonlyheadersales data)
	{
		Testonlyheadersales save = this.salesrepo.save(data);
		return save;
	}
	
	//get by id
	public Testonlyheadersales getid(int id)
	{
			
		Testonlyheadersales teacher = salesrepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Te not found :: " + id));
			return teacher;

	}
	
	//update by id
	public Testonlyheadersales updateById(int id, Testonlyheadersales sale) {
		Testonlyheadersales old_sale = salesrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));

old_sale.setLabel1(sale.getLabel1());
old_sale.setLabel2(sale.getLabel2());
old_sale.setLabel3(sale.getLabel3());
old_sale.setLabel4(sale.getLabel4());

		final Testonlyheadersales updated_sale = salesrepo.save(old_sale);

		return updated_sale;
	}
	
	//delete
	public boolean deleteById(int id) {
		if (!salesrepo.existsById(id)) {
			
			throw new ResourceNotFoundException("Sales not found :: " + id);
		}
		
		Testonlyheadersales sale = salesrepo.findById(id).orElse(null);
		salesrepo.delete(sale);
		return true;
	}
}