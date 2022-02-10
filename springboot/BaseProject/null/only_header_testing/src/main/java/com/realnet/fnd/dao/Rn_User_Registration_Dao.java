package com.realnet.fnd.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Lookup_Autofill;
import com.realnet.fnd.model.Rn_OTP;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.wfb.model.Rn_Fb_Header;
import com.realnet.wfb.model.Rn_Fb_Line;

@Transactional(readOnly = false)
public interface Rn_User_Registration_Dao {
	
	 public int createUser( Rn_Users user,int userid);

	 List<Rn_Users> SearchUser(String user_name);
	 public List<Rn_Lookup_Autofill> findrole(int newuserId);
	 public int CreateUser2(Rn_OTP rn_otp,int id);
	 
	 public List<Rn_Ext_Fields> ext_userlist();
	 //public List<Koel_Users> role_new_view_report(String u_name);
     public String writeViewData() ;

     public List<Rn_Fb_Header> get_Autocompelete(String itemvalauto);
     
 	 public List<Rn_Ext_Fields> ext_userlist1(String f_code) ;//for ext grid view only
     public List<Rn_Ext_Fields> ext_userlist2(String f_code) ;//for get header fields
     public List<Rn_Ext_Fields> ext_userlist3(String f_code);//for get line field only
     public List<Rn_Ext_Fields> ext_userlist_non_radio() ;
     public List<Rn_Ext_Fields> ext_userlist_radio() ;
     public List<Rn_Users> rn_reportlist(String id) ;
     public List<Rn_Users> rn_reportlist();
    
		public int addtables(int rowcount,int report_id, String tables_id[], String table_name[]);
		public int addcolumns(int rowcount2,int report_id, String column_id[], String column_name[]);
		public List<Rn_Fb_Line> property_field(int f_id) ;	
		public int addFields1(Rn_Fb_Line rn_users) ;
		public int addmenuGroupLine(int rowcount,int id,String tables_id[],String table_name[],String table_allias_name[]);
		public int addmenuGroupLine2(int rowcount2,int id,String column_id[],String column_name[],String table_allies_name[],String functions[],String column_alias_name_query[],String desc[]);
		public int addmenuGroupLine3(int rowcount3,int id,String where_id[],String explecity[],String table_column_name1[],String column1[],String condition[],String switch_control[],String table_column_name2[],String column2[]);
		public int addmenuGroupLine4(int rowcount4,int id,String date_id[],String table_allias_name3[],String column_name3[],String column_allias_name3[]);
		public int addmenuGroupLine5(int rowcount5,int id,String adhoc_id[],String table_allias_name4[],String column_name4[],String column_allias_name4[]);
		public int addmenuGroupLine6(int rowcount6,int id,String std_id[],String table_alias_name[],String column_name5[],String field_type[]);

		public int addUpdate(int id,String date_string[],String report_name[]);
		public int addUpdate2(int id,String date_string[],String report_name[],String add_param_string[]);
		public int addUpdate3(int id,String date_string[],String report_name[],String add_param_string[],String grid_headers[],String grid_values[]);
		public int addUpdateReport3(int id,String report_name[],String description[],String report_tags[],String date_string[],String add_param_string[],String master_select[],String grid_headers[],String std_param_view[],String grid_values[],String model_string[],String query[]);



}

