
package com.realnet.test_module1.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.realnet.test_module1.model.Rn_nil_test2;
public interface	Nil_test2_service
{
public List<Rn_nil_test2> prefield(int u_id);
public List<Rn_nil_test2> userlist();
public int saveheader(Rn_nil_test2	rn_nil_test2);
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4	); 
}