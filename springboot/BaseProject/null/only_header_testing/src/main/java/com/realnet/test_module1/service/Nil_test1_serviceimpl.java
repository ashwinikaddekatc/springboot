
package com.realnet.test_module1.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.realnet.test_module1.dao.Nil_test1_dao;
import com.realnet.test_module1.model.Rn_nil_test1;

@Service
public class Nil_test1_serviceimpl implements Nil_test1_service {

	@Autowired
	private Nil_test1_dao nil_test1_dao;

	@Override
	public List<Rn_nil_test1> prefield(int u_id) {
		return nil_test1_dao.prefield(u_id);
	}

	@Override
	public List<Rn_nil_test1> userlist() {
		return nil_test1_dao.userlist();
	}

	@Override
	public int save(int rowcount, String id, String textfield1, String textfield2, String textfield3,
			String textfield4) {
		return nil_test1_dao.save(rowcount, id, textfield1, textfield2, textfield3, textfield4);
	}

	public int saveheader(Rn_nil_test1 rn_nil_test1) {
		return nil_test1_dao.saveheader(rn_nil_test1);
	}
}