
package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Project_Setup_Dao;
import com.realnet.fnd.model.Rn_Project_Setup;

@Service
public class Rn_Project_Setup_ServiceImpl implements Rn_Project_Setup_Service {

	@Autowired
	private Rn_Project_Setup_Dao rn_project_setup_dao;

	@Override
	public List<Rn_Project_Setup> prefield(int u_id) {
		return rn_project_setup_dao.prefield(u_id);
	}

	@Override
	public List<Rn_Project_Setup> userlist() {
		return rn_project_setup_dao.userlist();
	}

	@Override
	public int save(int rowcount, String[] id, String[] project_name, String[] technology_stack, String[] description,
			String[] project_prefix, String[] db_name, String[] db_user, String[] db_password, String[] port_no,
			String[] copy_to) {
		return rn_project_setup_dao.save(rowcount, id, project_name, technology_stack, description, project_prefix,
				db_name, db_user, db_password, port_no, copy_to);
	}

	public int saveheader(Rn_Project_Setup rn_project_setup_t) {
		return rn_project_setup_dao.saveheader(rn_project_setup_t);
	}
	/*
	 * @Override public List<Rn_Project_Setup> prefield(int u_id, int module_id) {
	 * return rn_project_setup_dao.prefield(u_id,module_id); }
	 */

}