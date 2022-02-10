package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.realnet.fnd.dao.Rn_Look_Up_Dao;
import com.realnet.fnd.model.Rn_Look_Up;
import com.realnet.fnd.model.Rn_Lookup_Autofill;


@Service("Rn_Look_Up_Service")
@Transactional
public class Rn_Look_Up_ServiceImpl implements Rn_Look_Up_Service
{

	@Autowired
	Rn_Look_Up_Dao lookupDao;
	@Override
	public String save_lookup(Rn_Look_Up lookup) {
		return lookupDao.save_lookup(lookup);
	}
	
	@Override
	public int save_lookupvalues(int user_id,int count,String lookup, String[] lookup_code, String[] meaning, String[] description,
			String[] active_start_date, String[] active_end_date) {
		return lookupDao.save_lookupvalues(user_id,count,lookup, lookup_code, meaning, description, active_start_date, active_end_date);
	}

	@Override
	public List<Rn_Look_Up> listLookUp(String tagName) {
		return lookupDao.listLookUp(tagName);
	}

	@Override
	public List<Rn_Lookup_Autofill> lookuplist() {
		return lookupDao.lookuplist();
		}

	@Override
	public List<Rn_Lookup_Autofill> lookuplist1() {
		System.out.println("in service");

		return lookupDao.lookuplist1();	}

	@Override
	public List<Rn_Lookup_Autofill> lookup1(String s1) {
		System.out.println("in service");

		return lookupDao.lookup1(s1);	}
	@Override
	public List<Rn_Lookup_Autofill> lookup2(String s1) {
		return lookupDao.lookup2(s1);
	}

}
