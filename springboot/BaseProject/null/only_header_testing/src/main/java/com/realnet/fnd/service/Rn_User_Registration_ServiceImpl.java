package com.realnet.fnd.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Distributor_Details_Dao;
import com.realnet.fnd.dao.Rn_Forgot_Password_Dao;
import com.realnet.fnd.dao.Rn_User_Registration_Dao;
import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_OTP;
import com.realnet.fnd.model.Rn_User_Assignment;
import com.realnet.fnd.model.Rn_User_Role_Assignment;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.rb.model.Rn_Report_Group_Header;
import com.realnet.wfb.model.Rn_Fb_Header;

@Service
public class Rn_User_Registration_ServiceImpl implements Rn_User_Registration_Service {
	
	@Autowired
	private Rn_User_Registration_Dao rn_user_registration_dao; 
	
	@Autowired
	private Rn_Forgot_Password_Dao forgotdao;
	
	@Autowired
	private Rn_Distributor_Details_Dao rn_distributor_details_dao;
	
   
	public int CreateUser( Rn_Users user,int userid)
	{ 
		System.out.println("------------service CreateUser methode");
		return rn_user_registration_dao.createUser(user,userid);
	}
	
	
	public List<Rn_Users> SearchUser(String user_name)
	 {
		 return rn_user_registration_dao.SearchUser(user_name);
	 }
	public List<Rn_Lookup_Autofill> findrole(int newuserId)
	{
		return rn_user_registration_dao.findrole(newuserId);
	}
	@Override
	public List<Rn_Users> new_view_report(int u_id) {
		return rn_distributor_details_dao.new_view_report(u_id);

	}
	
	@Override
	public List<Rn_Users> rn_userlist() 
	{
		return rn_distributor_details_dao.rn_userlist();
	}
	
	
	

	@Override
	 public int addReport(int rowcount, String[] user_id, String[] user_name,Date start_date,Date end_date,
			 String[] first_name,String[] middle_name,String[] last_name,String[] contact_number,String[] email_address ) 
	{
		return rn_distributor_details_dao.addReport(rowcount, user_id, user_name,start_date,end_date,first_name,middle_name, last_name,contact_number,email_address);
	}
	
	@Override
	public List<Rn_Users> role_new_view_report(int u_id) 
	{
		return rn_distributor_details_dao.role_new_view_report(u_id);
	}
	
	@Override
	public List<Rn_Menu_Group_Header> view_menu_group(int u_id)	
	{
		return rn_distributor_details_dao.view_menu_group(u_id);
	}
	
	@Override
	public List<Rn_Report_Group_Header> view_report_group(int u_id)	
	{
		return rn_distributor_details_dao.view_report_group(u_id);
	}
	
	@Override
	public List<Rn_User_Role_Assignment> view_role_group(int u_id)	
	{
		return rn_distributor_details_dao.view_role_group(u_id);
	}
	
	
	
	@Override
	public int saveroll(Rn_User_Assignment userassign)	
	{
		return rn_distributor_details_dao.saveroll(userassign);
	}
	
	public int addRoll(int rowcount, String[] user_id,int menu_group_id, int report_group_id)
	{
		return rn_distributor_details_dao.addRoll(rowcount,user_id,menu_group_id,report_group_id);	
	}
	
	
	@Override
	public List<Rn_User_Assignment> view_assignment_id(int u_id) 	
	{
		return rn_distributor_details_dao.view_assignment_id(u_id);
	}

	
	@Override
	public int CreateUser2(Rn_OTP rn_otp,int id)	
	{
		return rn_user_registration_dao.CreateUser2(rn_otp,id);
	}
	
	

	@Override
	public List<Rn_OTP> otp_report(int otp_id)
	{
		return forgotdao.otp_report(otp_id);
	}
	
	
	
	@Override
	public List<Rn_OTP> getUid() 

	{
		return forgotdao.getUid();
	}
	
	@Override
	public int resetpass(int rowcount, String[] user_id, String[] user_name,String[] password)
	  {  
		return forgotdao.resetpass(rowcount,user_id,user_name,password);
	  }
	
	
	 @Override
	 public String writeViewData()
	 {
		return rn_user_registration_dao.writeViewData();
		 
		 
	 }
	 
	 
	 public List<Rn_Ext_Fields> update_fields(int f_id)
	 {
		return rn_distributor_details_dao.update_fields(f_id);
		 
	 }
	 
	 public List<Rn_Ext_Fields> update_fields_2(int f_id)
	 {
		return rn_distributor_details_dao.update_fields(f_id);
		 
	 }
	 
	    @Override
		public int addFields(int rowcount,String[] id,String[] field_name, String[] mapping,String[] data_type)
		{
			return rn_distributor_details_dao.addFields(rowcount,id,field_name,mapping,data_type);
			
		}
	    
	    
	    
	    @Override
		public int addFields(Rn_Ext_Fields rn) {
		
			return rn_distributor_details_dao.addFields(rn);
		}
	    
	   
	    
	    public int addRn(Rn_Ext_Fields rn){
			return rn_distributor_details_dao.addRn(rn);
	    	
	    }
	    
	   /* 
	    public List<Rn_Two_Jsp> grid_view(int u_id)
	    {
	    	return rn_distributor_details_dao.grid_view(u_id);
	    	
	    }*/
	    
	    
	    public List<Rn_Fb_Header> get_Autocompelete(String itemvalauto){
			return rn_user_registration_dao.get_Autocompelete(itemvalauto);
	    	
	    }
	    
	    
	    public List<Rn_Fb_Header> fb_header(String  f_code)
	    {
			return rn_distributor_details_dao.fb_header(f_code);
	    	
	    }
	    
	    
	    
	  
	    
		public int addtables(int rowcount,int report_id, String tables_id[], String table_name[])
		{
			return rn_user_registration_dao.addtables(rowcount, report_id, tables_id, table_name);
		} 
		
		
		
		public int addcolumns(int rowcount2,int report_id, String column_id[], String column_name[])
		{
			return rn_user_registration_dao.addcolumns(rowcount2, report_id, column_id, column_name);
		}
		
		
		
		@Override
		public int addmenuGroupLine(int rowcount,int id,String tables_id[],String table_name[],String table_allias_name[]){
		
			return rn_user_registration_dao.addmenuGroupLine(rowcount,id,tables_id,table_name,table_allias_name);
		}	
		
		public int addmenuGroupLine2(int rowcount2,int id,String column_id[],String column_name[],String table_allies_name[],String functions[],String column_alias_name_query[],String desc[])
		{
			return rn_user_registration_dao.addmenuGroupLine2(rowcount2,id,column_id,column_name,table_allies_name, functions, column_alias_name_query, desc);
		}
		
		public int addmenuGroupLine3(int rowcount3,int report_id,String where_id[],String explecity[],String table_column_name1[],String column1[],String condition[],String switch_control[],String table_column_name2[],String column2[])
		{

			return rn_user_registration_dao.addmenuGroupLine3( rowcount3, report_id, where_id, explecity, table_column_name1, column1,condition, switch_control,table_column_name2,column2);
		}
		

		public int addmenuGroupLine4(int rowcount4,int id,String date_id[],String table_allias_name3[],String column_name3[],String column_allias_name3[])
		{
			
			return rn_user_registration_dao.addmenuGroupLine4( rowcount4, id, date_id, table_allias_name3, column_name3, column_allias_name3);
			
		}
		
		public int addmenuGroupLine5(int rowcount5,int id,String adhoc_id[],String table_allias_name4[],String column_name4[],String column_allias_name4[])
		{
			
			return rn_user_registration_dao.addmenuGroupLine5( rowcount5, id, adhoc_id,table_allias_name4, column_name4, column_allias_name4);
			
		}
		
		
		public int addmenuGroupLine6(int rowcount6,int id,String std_id[],String table_alias_name[],String column_name5[],String field_type[])
		{
			
			return rn_user_registration_dao.addmenuGroupLine6( rowcount6, id, std_id,table_alias_name, column_name5, field_type);
			
		}
		
		
		
	    
	    

	}
	

