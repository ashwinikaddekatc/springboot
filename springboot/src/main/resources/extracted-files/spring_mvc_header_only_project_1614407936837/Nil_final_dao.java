
package com.realnet.test_module1.dao;
import java.util.Date;
import java.util.List;
import com.realnet.test_module1.model.Rn_nil_final;
public interface	Nil_final_dao {	
public List<Rn_nil_final> userlist();
public List<Rn_nil_final> prefield(int u_id);
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4,	String	textfield5,	String	textfield6,	String	textfield7,	String	textfield8,	String	textfield9);
public int saveheader(Rn_nil_final	rn_nil_final);
}