
package com.realnet.fnd.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Rn_Instance_Type_Dao;
import com.realnet.fnd.model.Rn_Instance_Type;
@Service
public class	Rn_Instance_Type_ServiceImpl	implements	Rn_Instance_Type_Service
 {

@Autowired
private 	Rn_Instance_Type_Dao	rn_instance_type_dao;

@Override 
public List<Rn_Instance_Type> prefield(int u_id)
{
return	rn_instance_type_dao.prefield(u_id);
 }
@Override
public List<Rn_Instance_Type> userlist() 
{
return	rn_instance_type_dao.userlist();
}
@Override
public int save(int rowcount,String[]	id,String[]	instance_type	) 
{
return	rn_instance_type_dao.save(rowcount,id,instance_type	);
}public int saveheader(Rn_Instance_Type	rn_instance_type_t){return 	rn_instance_type_dao.saveheader(rn_instance_type_t);}
}