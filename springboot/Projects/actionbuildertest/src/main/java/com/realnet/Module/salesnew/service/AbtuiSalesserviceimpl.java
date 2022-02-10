package com.realnet.Module.salesnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.Module.salesnew.entity.Abtuisales;
import com.realnet.Module.salesnew.repository.AbtuiSalesrepository;
import com.realnet.Module.salesnew.responce.AbtuiSalesResponse;
import com.realnet.Module.salesnew.service.Abtuisalesservice;import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class AbtuiSalesserviceimpl implements Abtuisalesservice{ 
	@Autowired
	AbtuiSalesrepository salesrepo;
	

	
	//getting data
	public Page<Abtuisales> getlist(Pageable page)
	{
		Page<Abtuisales> find = this.salesrepo.findAll(page);
		return find;
	}
	
	//creating sales 
	public Abtuisales save(Abtuisales data)
	{
		Abtuisales save = this.salesrepo.save(data);
		return save;
	}
	
	//ge
	public Abtuisales getid(int id)
	{
			
			Abtuisales teacher = salesrepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Te not found :: " + id));
			return teacher;

	}
	
	//update by id
	public Abtuisales updateById(int id, Abtuisales sale) {
		Abtuisales old_sale = salesrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
		old_sale.setExtn1(sale.getExtn1());
		old_sale .setExtn2(sale.getExtn2());
		old_sale.setExtn3(sale.getExtn3());
		old_sale.setExtn4(sale.getExtn4());
		old_sale.setExtn5(sale.getExtn5());
		old_sale.setExtn6(sale.getExtn6());
		old_sale.setExtn7(sale.getExtn7());
		old_sale.setExtn8(sale.getExtn8());
		old_sale.setExtn9(sale.getExtn9());
		old_sale.setExtn10(sale.getExtn10());
		old_sale.setExtn11(sale.getExtn11());
		old_sale.setExtn12(sale.getExtn12());
		old_sale.setExtn13(sale.getExtn13());
		old_sale.setExtn14(sale.getExtn14());
		old_sale.setExtn15(sale.getExtn15());
		old_sale.setFlex1(sale.getFlex1());
		old_sale.setFlex2(sale.getFlex2());
		old_sale.setFlex3(sale.getFlex3());
		old_sale.setFlex4(sale.getFlex4());
		old_sale.setFlex5(sale.getFlex5());
old_sale.setLabel1(sale.getLabel1());
old_sale.setLabel2(sale.getLabel2());
old_sale.setLabel3(sale.getLabel3());
old_sale.setLabel4(sale.getLabel4());
			
		final Abtuisales updated_sale = salesrepo.save(old_sale);

		return updated_sale;
	}
	
	//delete
	public boolean deleteById(int id) {
		if (!salesrepo.existsById(id)) {
			
			throw new ResourceNotFoundException("Sales not found :: " + id);
		}
		
		Abtuisales sale = salesrepo.findById(id).orElse(null);
		salesrepo.delete(sale);
		return true;
	}
}