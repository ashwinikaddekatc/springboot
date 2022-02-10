package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.exceptions.ResourceNotFoundException;
import com.realnet.fnd.entity.Rn_Dynamic_Transaction;
import com.realnet.fnd.entity.Rn_Dynamic_Transaction_line;
import com.realnet.fnd.repository.Rn_DynamicTransactionRepository;
import com.realnet.fnd.repository.Rn_Dynamic_Tranction_LineRepositary;

@Service
public class Rn_DynamicTransactionLineServiceImpl implements Rn_Dynamic_Transaction_LineService{
	
	@Autowired
	private Rn_Dynamic_Tranction_LineRepositary rn_Dynamic_Tranction_LineRepositary;
	
	@Override
	public List<Rn_Dynamic_Transaction_line> getAll() {
		return rn_Dynamic_Tranction_LineRepositary.findAll();
	}

	@Override
	public Page<Rn_Dynamic_Transaction_line> getAll(Pageable p) {
		// TODO Auto-generated method stub
		return rn_Dynamic_Tranction_LineRepositary.findAll(p);
	}

	@Override
	public List<Rn_Dynamic_Transaction_line> getByFormId(int form_id) {
		// TODO Auto-generated method stub
		List<Rn_Dynamic_Transaction_line> rn_dynamic_transactionLines = rn_Dynamic_Tranction_LineRepositary.findByFormId(form_id);
		return rn_dynamic_transactionLines;

	}

	@Override
	public Rn_Dynamic_Transaction_line getByIdAndFormId(int id, int form_id) {
		// TODO Auto-generated method stub
		Rn_Dynamic_Transaction_line rn_dynamic_transaction = rn_Dynamic_Tranction_LineRepositary.findByIdAndFormId(id, form_id);
				//.orElseThrow(() -> new ResourceNotFoundException("Data not found with id :: " + id + " and form_id ::" + form_id));
		return rn_dynamic_transaction;
	}

	@Override
	public List<Rn_Dynamic_Transaction_line> save(List<Rn_Dynamic_Transaction_line> rn_Dynamic_Transaction_line) {
		// TODO Auto-generated method stub
		List<Rn_Dynamic_Transaction_line> savedRn_Dynamic_Transaction = rn_Dynamic_Tranction_LineRepositary
				.saveAll(rn_Dynamic_Transaction_line);
		return savedRn_Dynamic_Transaction;
	}

	@Override
	public Rn_Dynamic_Transaction_line updateByFormId(int id, int form_id,Rn_Dynamic_Transaction_line txRequest) 
	
	{
		Rn_Dynamic_Transaction_line rn_dynamic_transaction = rn_Dynamic_Tranction_LineRepositary.findByIdAndFormId(id, form_id);
				//.orElseThrow(() -> new ResourceNotFoundException("Extension Field not found :: " + form_id));
		rn_dynamic_transaction.setUpdatedBy(txRequest.getUpdatedBy());
		// rn_dynamic_transaction.setForm_id(txRequest.getForm_id()); // modify needed
		rn_dynamic_transaction.setForm_version(txRequest.getForm_version());

		rn_dynamic_transaction.setComp1(txRequest.getComp1());
		rn_dynamic_transaction.setComp2(txRequest.getComp2());
		rn_dynamic_transaction.setComp3(txRequest.getComp3());
		rn_dynamic_transaction.setComp4(txRequest.getComp4());
		rn_dynamic_transaction.setComp5(txRequest.getComp5());
		rn_dynamic_transaction.setComp6(txRequest.getComp6());
		rn_dynamic_transaction.setComp7(txRequest.getComp7());
		rn_dynamic_transaction.setComp8(txRequest.getComp8());
		rn_dynamic_transaction.setComp9(txRequest.getComp9());
		rn_dynamic_transaction.setComp10(txRequest.getComp10());
		rn_dynamic_transaction.setComp11(txRequest.getComp11());
		rn_dynamic_transaction.setComp12(txRequest.getComp12());
		rn_dynamic_transaction.setComp13(txRequest.getComp13());
		rn_dynamic_transaction.setComp14(txRequest.getComp14());
		rn_dynamic_transaction.setComp15(txRequest.getComp15());
		rn_dynamic_transaction.setComp16(txRequest.getComp16());
		rn_dynamic_transaction.setComp17(txRequest.getComp17());
		rn_dynamic_transaction.setComp18(txRequest.getComp18());
		rn_dynamic_transaction.setComp19(txRequest.getComp19());
		rn_dynamic_transaction.setComp20(txRequest.getComp20());
		rn_dynamic_transaction.setComp21(txRequest.getComp21());
		rn_dynamic_transaction.setComp22(txRequest.getComp22());
		rn_dynamic_transaction.setComp23(txRequest.getComp23());
		rn_dynamic_transaction.setComp24(txRequest.getComp24());
		rn_dynamic_transaction.setComp25(txRequest.getComp25());
		rn_dynamic_transaction.setComp_l26(txRequest.getComp_l26());
		rn_dynamic_transaction.setComp_l27(txRequest.getComp_l27());
		rn_dynamic_transaction.setComp_l28(txRequest.getComp_l28());
		rn_dynamic_transaction.setComp_l29(txRequest.getComp_l29());
		rn_dynamic_transaction.setComp_l30(txRequest.getComp_l30());
		final Rn_Dynamic_Transaction_line updated_ext_field = rn_Dynamic_Tranction_LineRepositary.save(rn_dynamic_transaction);
		return updated_ext_field;

	}

	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		if (!rn_Dynamic_Tranction_LineRepositary.existsById(id)) {
			throw new ResourceNotFoundException("Data not Exist = " + id);
		}
		Rn_Dynamic_Transaction_line rn_dynamic_transaction = rn_Dynamic_Tranction_LineRepositary.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Data not found :: " + id));
		rn_Dynamic_Tranction_LineRepositary.delete(rn_dynamic_transaction);
		return true;
	}

	@Override
	public void buildDynamicForm(int form_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rn_Dynamic_Transaction_line getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
