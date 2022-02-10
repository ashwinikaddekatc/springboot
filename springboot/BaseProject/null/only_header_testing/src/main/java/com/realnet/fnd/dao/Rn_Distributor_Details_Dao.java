package com.realnet.fnd.dao;

import java.util.Date;
import java.util.List;

import com.realnet.fnd.model.Rn_Ext_Fields;
import com.realnet.fnd.model.Rn_Lookup_Values;
import com.realnet.fnd.model.Rn_Menu_Group_Header;
import com.realnet.fnd.model.Rn_Test12;
import com.realnet.fnd.model.Rn_Two_Jsp;
import com.realnet.fnd.model.Rn_User_Assignment;
import com.realnet.fnd.model.Rn_User_Role_Assignment;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.model.Rn_state_t;
import com.realnet.rb.model.Rn_Report_Group_Header;
import com.realnet.wfb.model.Rn_Fb_Header;
import com.realnet.wfb.model.Rn_Fb_Line;

public interface Rn_Distributor_Details_Dao {


/*	public List<Rn_Customer_Details_Grid> CustomerDetails_list(String id);
	public List<Rn_Customer_Site_Details_Grid> CustomrSiteDetails_List(String id);
*/	public List<Rn_Users> rn_userlist();
	public List<Rn_Users> new_view_report(int u_id);
	
	public int addReport( int rowcount,String[] user_id, String[] user_name,
			 Date start_date,Date end_date,String[] first_name,String[] middle_name,String[] last_name,String[] contact_number,String[] email_address);
	
	
	public List<Rn_Users> role_new_view_report(int u_id);
	public List<Rn_Menu_Group_Header> view_menu_group(int u_id);
	public List<Rn_Report_Group_Header> view_report_group(int u_id);
	
	public List<Rn_User_Role_Assignment> view_role_group(int u_id);
	
	public int saveroll(Rn_User_Assignment userassign);
	
	public int addRoll(int rowcount, String[] user_id,int menu_group_id, int report_group_id);
	
	public List<Rn_User_Assignment> view_assignment_id(int u_id) ;
	
	
	public List<Rn_Ext_Fields> update_fields(int f_id);
	public int addFields(int rowcount, String[] id,String[] field_name, String[] mapping,String[] data_type);
	public List<Rn_Ext_Fields> update_fields_2(int f_id) ;
	public int addFields(Rn_Ext_Fields rn);
	
	public int addRn(Rn_Ext_Fields rn);
	
	
	public List<Rn_Two_Jsp> gridview_three();
	/*public List<Rn_Two_Jsp> grid_view(int u_id);*/
	public List<Rn_Fb_Header> entry_list() ;
	public List<Rn_Fb_Header> fb_headerlist(int id);
	public List<Rn_Fb_Header> fb_headerlist(int project_id,int module_id);

	public List<Rn_Fb_Header> fb_headerlistByProjectId(int p_id);
	public List<Rn_Fb_Header> fbHeaderlistByProjectIdSource(int p_id);
	public List<Rn_Ext_Fields> fb_linelist(String fc) ;
	public List<Rn_Fb_Line> fb_linelist2(String fc) ;
	List<Rn_Fb_Header> fb_header(String f_code);
	
	public int saveTrsting(Rn_Test12 t12,int id);
	public List<Rn_Ext_Fields> fb_listForId(String fc);
	
	public List<Rn_Fb_Line> fb_listForId_not_pri(String fc) ;
	public List<Rn_Fb_Line> fb_listDatetime(String fc) ;
	
	public List<Rn_Ext_Fields> property_field(int f_id) ;
	public List<Rn_Fb_Line> for_line_part(String fc) ;
	public List<Rn_Ext_Fields> line_primary_id(String fc);
	public List<Rn_Ext_Fields> lineForId_not_pri(String fc) ;
	public int addFields1(Rn_Ext_Fields rn_users);
	public List<Rn_Ext_Fields> property_field2(int f_id) ;
	public int addFields2(Rn_Ext_Fields rn_users);
	public List<Rn_Lookup_Values> attribute_values();

	public List<Rn_state_t> findAllEmployees();
	public Rn_state_t findById(int id) ;
	
	
}