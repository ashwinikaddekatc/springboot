package com.realnet.Module.salesnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.Module.salesnew.entity.Ganesh_uisales;
import com.realnet.Module.salesnew.repository.Ganesh_uiSalesrepository;
import com.realnet.Module.salesnew.responce.Ganesh_uiSalesResponce;
import com.realnet.Module.salesnew.service.Ganesh_uisalesservice;import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class Ganesh_uiSalesserviceimpl implements Ganesh_uisalesservice{ 
	@Autowired
	Ganesh_uiSalesrepository salesrepo;
	

	
	//getting data
	public Page<Ganesh_uisales> getlist(Pageable page)
	{
		Page<Ganesh_uisales> find = this.salesrepo.findAll(page);
		return find;
	}
	
	//creating sales 
	public Ganesh_uisales createsale(Ganesh_uisales data)
	{
		Ganesh_uisales save = this.salesrepo.save(data);
		return save;
	}
	
	//get by id
	public Ganesh_uisales getid(int id)
	{
			
			Ganesh_uisales teacher = salesrepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Te not found :: " + id));
			return teacher;

	}
	
	//update by id
	public Ganesh_uisales updateById(int id, Ganesh_uisales sale) {
		Ganesh_uisales old_sale = salesrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));


		final Ganesh_uisales updated_sale = salesrepo.save(old_sale);

		return updated_sale;
	}
	
	//delete
	public boolean deleteById(int id) {
		if (!salesrepo.existsById(id)) {
			
			throw new ResourceNotFoundException("Sales not found :: " + id);
		}
		
		Ganesh_uisales sale = salesrepo.findById(id).orElse(null);
		salesrepo.delete(sale);
		return true;
	}
}