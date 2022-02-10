
package com.realnet.test_module1.MyProjectModule.service;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.realnet.test_module1.model.Myprojectui_t;
public interface	Myprojectui_service
{
public List<Myprojectui_t> prefield(int u_id);
public List<Myprojectui_t> userlist();
public int saveheader(Myprojectui_t	myprojectui_t);
}