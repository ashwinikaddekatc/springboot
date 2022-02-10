
package com.realnet.test_module1.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.realnet.test_module1.dao.Nil_final_dao;
import com.realnet.test_module1.model.Rn_nil_final;
@Service
public class	Nil_final_serviceimpl	implements	Nil_final_service {

@Autowired
private 	Nil_final_dao	nil_final_dao;

@Override 
public List<Rn_nil_final> prefield(int u_id) {
return	nil_final_dao.prefield(u_id);
}

@Override
public List<Rn_nil_final> userlist() {
return	nil_final_dao.userlist();
}

@Override
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4,	String	textfield5,	String	textfield6,	String	textfield7,	String	textfield8,	String	textfield9) {
return	nil_final_dao.save(rowcount,id,textfield1,	textfield2,	textfield3,	textfield4,	textfield5,	textfield6,	textfield7,	textfield8,	textfield9);
}

public int saveheader(Rn_nil_final	rn_nil_final){
	return 	nil_final_dao.saveheader(rn_nil_final);
}
}