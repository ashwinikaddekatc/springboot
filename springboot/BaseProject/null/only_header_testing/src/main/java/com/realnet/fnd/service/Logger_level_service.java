
package com.realnet.fnd.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.realnet.fnd.model.Logger_level;
public interface	Logger_level_service
{
public List<Logger_level> prefield(int u_id);
public List<Logger_level> userlist();
public int saveheader(Logger_level	logger_level);
public int save(int rowcount,String	id,String	textfield1,	String	textfield2,	String	textfield3,	String	textfield4	); 
}