
package com.realnet.fnd.dao;

import java.util.List;

import com.realnet.fnd.model.Rn_Patch;
import com.realnet.fnd.model.Rn_Project_Setup;
import com.realnet.fnd.model.Rn_module_setup_t;
import com.realnet.wfb.model.Rn_Fb_Header;

public interface Rn_Project_Setup_Dao {
	public String getProjectPath();

	public String getProjectPath(String technology_stack);

	public List<Rn_Project_Setup> userlist();

	public List<Rn_Project_Setup> prefield(int u_id);

	public List<Rn_Patch> patchDetails();

	public int save(int rowcount, String[] id, String[] project_name, String[] technology_stack, String[] description,
			String[] project_prefix, String[] db_name, String[] db_user, String[] db_password, String[] port_no,
			String[] copy_to);

	public int saveheader(Rn_Project_Setup rn_project_setup_t);
	/* public List<Rn_Project_Setup> prefield(int u_id, int module_id); */

	public Rn_Project_Setup copyProject(int project_id);
	public List<Rn_module_setup_t> copyModules(int project_id);
	public List<Rn_Fb_Header> copyHeaders(int project_id, int module_id);
	

}