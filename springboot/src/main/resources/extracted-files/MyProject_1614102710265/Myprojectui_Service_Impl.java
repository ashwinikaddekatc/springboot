
package com.realnet.test_module1.MyProjectModule.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.realnet.test_module1.dao.Myprojectui_dao;
import com.realnet.test_module1.model.Myprojectui_t;
@Service
public class	Myprojectui_serviceimpl	implements	Myprojectui_service {

@Autowired
private 	Myprojectui_dao	myprojectui_dao;

@Override 
public List<Myprojectui_t> prefield(int u_id) {
return	myprojectui_dao.prefield(u_id);
}

@Override
public List<Myprojectui_t> userlist() {
return	myprojectui_dao.userlist();
}

public int saveheader(Myprojectui_t	myprojectui_t){
	return 	myprojectui_dao.saveheader(myprojectui_t);
}
}