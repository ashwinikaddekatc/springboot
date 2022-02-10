
package com.realnet.fnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Dashboard_Dao_T;
import com.realnet.fnd.model.Rn_Dashboard_T;

@Service
public class Rn_Dashboard_ServiceImpl_T implements Rn_Dashboard_Service_T {

	@Autowired
	private Rn_Dashboard_Dao_T rn_dashboard_dao;

	@Override
	public List<Rn_Dashboard_T> prefield(int u_id) {
		return rn_dashboard_dao.prefield(u_id);
	}

	@Override
	public List<Rn_Dashboard_T> userlist() {
		return rn_dashboard_dao.userlist();
	}

	@Override
	public int save(int rowcount, String[] id, String[] dashboard_name, String[] description, String[] chart_type,
			String[] sql_query) {
		return rn_dashboard_dao.save(rowcount, id, dashboard_name, description, chart_type, sql_query);
	}

	public int saveheader(Rn_Dashboard_T rn_dashboard_t) {
		return rn_dashboard_dao.saveheader(rn_dashboard_t);
	}
}