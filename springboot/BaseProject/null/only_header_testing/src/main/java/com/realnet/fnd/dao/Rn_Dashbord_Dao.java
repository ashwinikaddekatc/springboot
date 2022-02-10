package com.realnet.fnd.dao;

import java.util.List;

import com.realnet.fnd.model.Rn_Admin_Count;
import com.realnet.fnd.model.Rn_Dashbord;

public interface Rn_Dashbord_Dao {
	
	
	
	
	public List<Rn_Dashbord> DashbordApprover_List(int user_id);
	public List<Rn_Dashbord> DashbordApproverCount_List(int user_id);
	public List<Rn_Dashbord> DashbordDealerCount_List(int user_id);
	public List<Rn_Dashbord> DashbordSalesCount_List(int user_id);
	public List<Rn_Admin_Count> AdminCount_List();
	
	

}