package com.realnet.Module.salesnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.Module.salesnew.entity.Cust_detailssales;
import com.realnet.Module.salesnew.repository.Cust_detailsSalesrepository;
import com.realnet.Module.salesnew.responce.Cust_detailsSalesResponce;
import com.realnet.Module.salesnew.service.Cust_detailssalesservice;import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class Cust_detailsSalesserviceimpl implements Cust_detailssalesservice{ 
	@Autowired
	Cust_detailsSalesrepository salesrepo;
	

	
	//getting data
	public Page<Cust_detailssales> getlist(Pageable page)
	{
		Page<Cust_detailssales> find = this.salesrepo.findAll(page);
		return find;
	}
	
	//creating sales 
	public Cust_detailssales createsale(Cust_detailssales data)
	{
		Cust_detailssales save = this.salesrepo.save(data);
		return save;
	}
	
	//get by id
	public Cust_detailssales getid(int id)
	{
			
			Cust_detailssales teacher = salesrepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Te not found :: " + id));
			return teacher;

	}
	
	//update by id
	public Cust_detailssales updateById(int id, Cust_detailssales sale) {
		Cust_detailssales old_sale = salesrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));


		final Cust_detailssales updated_sale = salesrepo.save(old_sale);

		return updated_sale;
	}
	
	//delete
	public boolean deleteById(int id) {
		if (!salesrepo.existsById(id)) {
			
			throw new ResourceNotFoundException("Sales not found :: " + id);
		}
		
		Cust_detailssales sale = salesrepo.findById(id).orElse(null);
		salesrepo.delete(sale);
		return true;
	}
}