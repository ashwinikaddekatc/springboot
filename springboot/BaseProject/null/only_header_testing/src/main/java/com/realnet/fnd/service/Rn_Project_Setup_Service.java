
package com.realnet.fnd.service;
import java.util.List;

import com.realnet.fnd.model.Rn_Project_Setup;
public interface	Rn_Project_Setup_Service
{
public List<Rn_Project_Setup> prefield(int u_id);

public List<Rn_Project_Setup> userlist();
public int save(int rowcount,String[]	id,String[]	project_name,String[] technology_stack,	String[]	description,String[]	project_prefix,String[]	db_name,String[]	db_user,String[]	db_password,String[]	port_no,String[] copy_to) ;
public int saveheader(Rn_Project_Setup	rn_project_setup_t);
/*public List<Rn_Project_Setup> prefield(int u_id, int module_id);*/
}