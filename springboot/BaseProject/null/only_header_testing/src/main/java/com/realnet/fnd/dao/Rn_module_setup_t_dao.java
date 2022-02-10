
package com.realnet.fnd.dao;

import java.util.List;

import com.realnet.fnd.model.Rn_Project_Setup;
import com.realnet.fnd.model.Rn_module_setup_t;
import com.realnet.wfb.model.Rn_Fb_Header;

public interface Rn_module_setup_t_dao {
	public List<Rn_module_setup_t> rn_module_values();

	public List<Rn_module_setup_t> prefield(int u_id);

	public List<Rn_module_setup_t> prefield_module(int m_id);

	public int save(int rowcount, String id, String module_name, String description, String module_prefix,
			String project_id, String copy_to);

	public int saveheader(Rn_module_setup_t rn_module_setup_t);

	public List<Rn_module_setup_t> rn_module_values(int module_id);

	public List<Rn_module_setup_t> rn_module_values_for_pid(int p_id);
	
	public Rn_module_setup_t getOneById(int module_id);
	
	
	public Rn_module_setup_t copyModule(int project_id, int module_id);
	
	// not working
	public Rn_Fb_Header copyHeader(int project_id, int new_module_id, int module_id);
	
	// working
	public List<Rn_Fb_Header> copyHeaders(int project_id, int module_id);
	
}