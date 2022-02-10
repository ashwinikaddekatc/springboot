
package com.realnet.fnd.service;
import java.util.List;

import com.realnet.fnd.model.Rn_Instance_Type;
public interface	Rn_Instance_Type_Service
{
public List<Rn_Instance_Type> prefield(int u_id);
public List<Rn_Instance_Type> userlist();
public int save(int rowcount,String[]	id,String[]	instance_type	) ;
public int saveheader(Rn_Instance_Type	rn_instance_type_t);
}