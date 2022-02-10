
package com.realnet.test_module1.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.realnet.test_module1.model.Rn_nil_final;
public interface	Nil_final_service
{
public List<Rn_nil_final> prefield(int u_id);
public List<Rn_nil_final> userlist();
public int saveheader(Rn_nil_final	rn_nil_final);
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4,	String	textfield5,	String	textfield6,	String	textfield7,	String	textfield8,	String	textfield9	); 
}