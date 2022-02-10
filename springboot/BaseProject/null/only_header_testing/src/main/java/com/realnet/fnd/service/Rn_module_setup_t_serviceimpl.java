
package com.realnet.fnd.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_module_setup_t_dao;
import com.realnet.fnd.model.Rn_module_setup_t;

@Service
public class Rn_module_setup_t_serviceimpl implements Rn_module_setup_t_service {

	@Autowired
	private Rn_module_setup_t_dao rn_module_setup_t_dao;

	@Override
	public List<Rn_module_setup_t> prefield(int u_id) {
		return rn_module_setup_t_dao.prefield(u_id);
	}

	@Override
	public List<Rn_module_setup_t> prefield_module(int m_id) {
		return rn_module_setup_t_dao.prefield(m_id);
	}

	@Override
	public List<Rn_module_setup_t> rn_module_values() {
		return rn_module_setup_t_dao.rn_module_values();
	}

	public int save(int rowcount, String id, String module_name, String description, String module_prefix,
			String project_id, String copy_to) {
		return rn_module_setup_t_dao.save(rowcount, id, module_name, description, module_prefix, project_id, copy_to);
	}

	public int saveheader(Rn_module_setup_t rn_module_setup_t) {
		return rn_module_setup_t_dao.saveheader(rn_module_setup_t);
	}

}