
package com.realnet.fnd.dao;
import java.util.Date;
import java.util.List;

import com.realnet.fnd.model.Logger_level;
public interface	Logger_level_dao {	
public List<Logger_level> userlist();
public List<Logger_level> prefield(int u_id);
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4	);
public int saveheader(Logger_level	logger_level);
public List<Logger_level> rn_logger_level(String logger_level_var);
public List<Logger_level> loggerDetails();
}