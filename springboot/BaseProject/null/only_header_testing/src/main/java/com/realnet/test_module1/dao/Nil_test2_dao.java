
package com.realnet.test_module1.dao;
import java.util.Date;
import java.util.List;
import com.realnet.test_module1.model.Rn_nil_test2;
public interface	Nil_test2_dao {	
public List<Rn_nil_test2> userlist();
public List<Rn_nil_test2> prefield(int u_id);
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4	);
public int saveheader(Rn_nil_test2	rn_nil_test2);
}