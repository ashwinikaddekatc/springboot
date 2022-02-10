
package com.realnet.MyProjectModule.dao;
import java.util.Date;
import java.util.List;
import com.realnet.MyProjectModule.model.Myprojectui_t;
public interface	Myprojectui_dao {	
public List<Myprojectui_t> userlist();
public List<Myprojectui_t> prefield(int u_id);
public int saveheader(Myprojectui_t	myprojectui_t);
}