package com.realnet.fnd.service;

import java.util.List;

import com.realnet.fnd.model.Rn_Look_Up;
import com.realnet.fnd.model.Rn_Lookup_Autofill;

public interface Rn_Look_Up_Service
{
	String save_lookup(Rn_Look_Up lookup);
	
	//String save_lookupvalues(Lookup lookup);
	
	int save_lookupvalues(int user_id,int count,String lookup, String[] lookup_code, String[] meaning, String[] description,
			String[] active_start_date, String[] active_end_date);

	List<Rn_Look_Up> listLookUp(String tagName);
	
	public List<Rn_Lookup_Autofill> lookuplist();
	

	//public List<LookupAutofill> lookuplist1();
public List<Rn_Lookup_Autofill> lookuplist1();
public List<Rn_Lookup_Autofill> lookup1(String s1);
public List<Rn_Lookup_Autofill> lookup2(String s1);


}
