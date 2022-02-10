
package com.realnet.fnd.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.realnet.fnd.dao.Logger_level_dao;
import com.realnet.fnd.model.Logger_level;
@Service
public class	Logger_level_serviceimpl	implements	Logger_level_service
 {

@Autowired
private 	Logger_level_dao	logger_level_dao;

@Override 
public List<Logger_level> prefield(int u_id)
{
return	logger_level_dao.prefield(u_id);
 }
@Override
public List<Logger_level> userlist() 
{
return	logger_level_dao.userlist();
}
@Override
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4	) 
{
return	logger_level_dao.save(rowcount,id,textfield1,	textfield2,	textfield3,	textfield4	);
}
public int saveheader(Logger_level	logger_level){return 	logger_level_dao.saveheader(logger_level);}
}