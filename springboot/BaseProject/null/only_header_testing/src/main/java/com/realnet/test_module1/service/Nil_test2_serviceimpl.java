
package com.realnet.test_module1.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.realnet.test_module1.dao.Nil_test2_dao;
import com.realnet.test_module1.model.Rn_nil_test2;
@Service
public class	Nil_test2_serviceimpl	implements	Nil_test2_service
 {

@Autowired
private 	Nil_test2_dao	nil_test2_dao;

@Override 
public List<Rn_nil_test2> prefield(int u_id)
{
return	nil_test2_dao.prefield(u_id);
 }
@Override
public List<Rn_nil_test2> userlist() 
{
return	nil_test2_dao.userlist();
}
@Override
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4	) 
{
return	nil_test2_dao.save(rowcount,id,textfield1,	textfield2,	textfield3,	textfield4	);
}
public int saveheader(Rn_nil_test2	rn_nil_test2){return 	nil_test2_dao.saveheader(rn_nil_test2);}
}