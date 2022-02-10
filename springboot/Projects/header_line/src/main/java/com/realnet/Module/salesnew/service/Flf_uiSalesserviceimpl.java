package com.realnet.Module.salesnew.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.Module.salesnew.entity.*;
import com.realnet.Module.salesnew.repository.*;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class Flf_uiSalesserviceimpl implements Flf_uisalesservice {
	@Autowired
	Flf_uiSalesrepository salesrepo;

	// getting data
	public Page<Flf_uisales> getlist(Pageable page) {
		Page<Flf_uisales> find = this.salesrepo.findAll(page);
		return find;
	}

	// creating sales
	public Flf_uisales createsale(Flf_uisales data) {
		Flf_uisales save = this.salesrepo.save(data);
		return save;
	}

	// get by id
	public Flf_uisales getid(int id) {

		Flf_uisales teacher = salesrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Te not found :: " + id));
		return teacher;

	}

	// update by id
	public Flf_uisales updateById(int id, Flf_uisales sale) {
		Flf_uisales old_sale = salesrepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Teacher not found :: " + id));
		old_sale.setTech_stack(sale.getTech_stack());
		old_sale.setObject_type(sale.getObject_type());
		old_sale.setSub_object_type(sale.getSub_object_type());
		old_sale.setFlf_uisalesperson(sale.getFlf_uisalesperson());

		final Flf_uisales updated_sale = salesrepo.save(old_sale);

		return updated_sale;
	}

	// delete
	public boolean deleteById(int id) {
		if (!salesrepo.existsById(id)) {

			throw new ResourceNotFoundException("Sales not found :: " + id);
		}

		Flf_uisales sale = salesrepo.findById(id).orElse(null);
		salesrepo.delete(sale);
		return true;
	}
}