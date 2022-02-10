package com.realnet.fnd.service;

import java.util.Date;
import java.util.List;

import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_OTP;
import com.realnet.fnd.model.Rn_User_Assignment;
import com.realnet.fnd.model.Rn_User_Role_Assignment;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.rb.model.Rn_Report_Group_Header;
import com.realnet.wfb.model.Rn_Fb_Header;


public interface Rn_User_Registration_Service {
	
	public int CreateUser( Rn_Users Koel_user,int userid);

	 List<Rn_Users> SearchUser(String user_name);
	 
	 
	 List<Rn_Lookup_Autofill> findrole(int newuserId);
     
	 public List<Rn_Users> new_view_report(int u_id);
	 
	 public List<Rn_Users> rn_userlist();

	 
	 
	public int addReport( int rowcount,String[] user_id, String[] user_name,Date start_date,Date end_date,
			  String[] first_name,String[] middle_name,String[] last_name,String[] contact_number,String[] email_address );
	
	
	public List<Rn_Users> role_new_view_report(int u_id);
	public List<Rn_Menu_Group_Header> view_menu_group(int u_id);
	public List<Rn_Report_Group_Header> view_report_group(int u_id);
	
	public List<Rn_User_Role_Assignment> view_role_group(int u_id);
	//public List<LookupValues> view_lookup_values(int u_id);
	public int saveroll(Rn_User_Assignment userassign);
	
	public int addRoll(int rowcount, String[] user_id,int menu_group_id, int report_group_id);
	public List<Rn_User_Assignment> view_assignment_id(int u_id) ;
	//public List<User_Role_Assignment> view_role_assignment_id(int u_id) ;
	
	public int CreateUser2(Rn_OTP rn_otp,int id);
	
	public List<Rn_OTP> otp_report(int otp_id);
	public List<Rn_OTP> getUid() ;
	
	public int resetpass(int rowcount, String[] user_id,String[] user_name, String[] password);
	 public String writeViewData() ;	
	 
	 
	 public List<Rn_Ext_Fields> update_fields(int f_id);
	 public List<Rn_Ext_Fields> update_fields_2(int f_id);

	 public int addFields(int rowcount, String[] id,String[] field_name, String[] mapping,String[] data_type);
	 public int addFields(Rn_Ext_Fields rn);
	 
	 
	 /*public int addFields1(int rowcount, String[] id,String[] field_name,String[] mapping,String[] data_type,String[] form_code,String[] mandatory,String[] hidden,
			 String[] readonly,String[] dependent,String[] dependent_on,String[] dependent_sp,String[] dependent_sp_param,String[] validation,String[] val_type,String[] val_sp,String[] val_sp_param,String[] sequence,String[] seq_name,String[] seq_sp,String[] seq_sp_param,String[] default1,String[] default_value,String[] default_sp,String[] default_sp_param,String[] cal_field,String[] cal_sp,String[] cal_sp_param,String[] add_to_grid,String[] dropdown,String[] sp_name,String[] sp_for_autocomplete);
	 */
	 
		 public int addRn(Rn_Ext_Fields rn);
		/* public List<Rn_Two_Jsp> grid_view(int u_id);*/
		 
		 public List<Rn_Fb_Header> get_Autocompelete(String itemvalauto);

		public List<Rn_Fb_Header> fb_header(String f_code);
		
		public int addtables(int rowcount,int report_id, String tables_id[], String table_name[]);

		
		public int addmenuGroupLine(int rowcount,int id,String tables_id[],String table_name[],String table_allias_name[]);

		
		public int addmenuGroupLine2(int rowcount2,int id,String column_id[],String column_name[],String table_allies_name[],String functions[],String column_alias_name_query[],String desc[]);
		
		public int addmenuGroupLine3(int rowcount3,int report_id,String where_id[],String explecity[],String table_column_name1[],String column1[],String condition[],String switch_control[],String table_column_name2[],String column2[]);
		
		public int addmenuGroupLine4(int rowcount4,int id,String date_id[],String table_allias_name3[],String column_name3[],String column_allias_name3[]);
		public int addmenuGroupLine5(int rowcount5,int id,String adhoc_id[],String table_allias_name4[],String column_name4[],String column_allias_name4[]);
		public int addmenuGroupLine6(int rowcount6,int id,String std_id[],String table_alias_name[],String column_name5[],String field_type[]);
		
		public int addcolumns(int rowcount2,int report_id, String column_id[], String column_name[]);
 }
