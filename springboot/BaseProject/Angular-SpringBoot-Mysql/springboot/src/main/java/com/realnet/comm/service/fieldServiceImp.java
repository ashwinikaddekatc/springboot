package com.realnet.comm.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.comm.entity.Department;
import com.realnet.comm.entity.Fields;
import com.realnet.comm.repository.FieldsRepo;
import com.realnet.exceptions.ResourceNotFoundException;

@Service
public class fieldServiceImp implements FieldService {
	
	@Autowired
	private FieldsRepo  fieldsRepo;
	
	@Override
	public List<Fields> getAll() {
		// TODO Auto-generated method stub
		return fieldsRepo.findAll();
	}
	
	@Override
	public Page<Fields> getAll(Pageable page) {
		// TODO Auto-generated method stub
		return fieldsRepo.findAll(page);
	}
	@Override
	public Fields getbyid(int id) {
		// TODO Auto-generated method stub
		Fields fields=fieldsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fields not found :: " + id));
		return fields;
		
	}

	@Override
	public List<Fields> save(List<Fields> fields) {
		// TODO Auto-generated method stub
		List<Fields> savedFields = fieldsRepo.saveAll(fields);
		return savedFields;
	}

	public Fields updateById(Integer id,@Valid Fields fields) {
		// TODO Auto-gene
		Fields old_field = fieldsRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("fields  not found :: " + id));
			
		old_field.setAutocomplete(fields.getAutocomplete());
		old_field.setCheckbox(fields.getCheckbox());
		old_field.setContact_field(fields.getContact_field());
		old_field.setCurrency_field(fields.getCurrency_field());
		old_field.setDatetime(fields.getDatetime());
		old_field.setDropdown(fields.getDropdown());
		old_field.setEmail(fields.getEmail());
		old_field.setMasked(fields.getMasked());
		old_field.setMultiselect_autocomplete(fields.getMultiselect_autocomplete());
		old_field.setMultiselect_dropdown(fields.getMultiselect_dropdown());
		old_field.setTextarea(fields.getTextarea());
		old_field.setTextfield(fields.getTextfield());
		old_field.setTogglebutton(fields.getTogglebutton());
		old_field.setUpload_field(fields.getUpload_field());
		old_field.setUrl(fields.getUrl());
		
		//old_sale.setSalesPerson(sale.getSalesPerson());
		final Fields updated_fields = fieldsRepo.save(old_field);
		return updated_fields;
	}

	@Override
	public boolean deleteById(int id) {
		if (!fieldsRepo.existsById(id)) {
			//logger.error("teacher not found with id = " + id);
			throw new ResourceNotFoundException("field not found :: " + id);
		}

		Fields fields = fieldsRepo.findById(id).orElse(null);
		fieldsRepo.delete(fields);
		return true;
	}

}
