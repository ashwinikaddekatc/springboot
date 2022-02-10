
package com.realnet.fnd.dao;
import java.util.List;

import com.realnet.fnd.model.Rn_Instance_Type;
public interface	Rn_Instance_Type_Dao {	
public List<Rn_Instance_Type> userlist();
public List<Rn_Instance_Type> prefield(int u_id);
public int save(int rowcount,String[]	id,String[]	instance_type	);
public int saveheader(Rn_Instance_Type	rn_instance_type_t);
}