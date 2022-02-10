
package com.realnet.fnd.controller;
 import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.realnet.configuration.HibernateConfiguration;
import com.realnet.fnd.dao.Logger_level_dao;
import com.realnet.fnd.model.Logger_level;
import com.realnet.fnd.service.Logger_level_service;
@Controller
public class	Logger_level_controller
{
@Autowired
private HibernateTemplate  hibernateTemplate;
@Autowired
private	Logger_level_service	logger_level_service;
@Autowired
HibernateConfiguration hibernateConfiguration;
@Autowired
private	Logger_level_dao	logger_level_dao;

@Autowired
private JdbcTemplate jdbcTemplate;

private static final Logger logger = Logger.getLogger(Logger_level_controller.class);


//----------------------entry form sbmit------------------------------------------
@Transactional
@RequestMapping(value = "/logger_level_submit", method = RequestMethod.POST)
public ModelAndView saveServiceRequest(@ModelAttribute	Logger_level	logger_level1,BindingResult resultKoel_user ,
ModelMap map, HttpServletRequest request)  
{int user_id=(Integer)request.getSession().getAttribute("userid");

/*String logger_level_var=(String)request.getSession().getAttribute("logger_level");
HttpSession session = request.getSession();
session.setAttribute("logger_level_var", logger_level_var);*/

	 String logger_level=request.getParameter("logger_level");
	 String filename=request.getParameter("filename");
	 String status=request.getParameter("status");
	 String textfield4=request.getParameter("textfield4");
	 String location=request.getParameter("location");
	 String comp_type=request.getParameter("comp_type");
	 String object_type=request.getParameter("object_type");
String 	attribute1=request.getParameter("attribute1");
String 	attribute2=request.getParameter("attribute2");
String 	attribute3=request.getParameter("attribute3");
String 	attribute4=request.getParameter("attribute4");
String 	attribute5=request.getParameter("attribute5");
String 	attribute6=request.getParameter("attribute6");
String 	attribute7=request.getParameter("attribute7");
String 	attribute8=request.getParameter("attribute8");
String 	attribute9=request.getParameter("attribute9");
String 	attribute10=request.getParameter("attribute10");
String 	attribute11=request.getParameter("attribute11");
String 	attribute12=request.getParameter("attribute12");
String 	attribute13=request.getParameter("attribute13");
String 	attribute14=request.getParameter("attribute14");
String 	attribute15=request.getParameter("attribute15");
String 	flex1=request.getParameter("flex1");
String 	flex2=request.getParameter("flex2");
String 	flex3=request.getParameter("flex3");
String 	flex4=request.getParameter("flex4");
String 	flex5=request.getParameter("flex5");

	logger_level1.setLogger_level(logger_level);
	logger_level1.setFile_name(filename);
	logger_level1.setStatus(status);
	logger_level1.setObject_type(object_type);
	logger_level1.setLocation(location);
	logger_level1.setComp_type(comp_type);
	logger_level1.setTextfield4(textfield4);
logger_level1.setAttribute1(attribute1);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute2(attribute2);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute3(attribute3);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute4(attribute4);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute5(attribute5);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute6(attribute6);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute7(attribute7);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute8(attribute8);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute9(attribute9);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute10(attribute10);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute11(attribute11);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute12(attribute12);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute13(attribute13);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute14(attribute14);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setAttribute15(attribute15);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setFlex1(flex1);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setFlex2(flex2);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setFlex3(flex3);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setFlex4(flex4);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);
logger_level1.setFlex5(flex5);
logger_level1.setCreated_by(user_id);
logger_level1.setLast_updated_by(user_id);

hibernateTemplate.saveOrUpdate(logger_level1);

String logger_level2 = logger_level1.getLogger_level();
System.out.println("Loggger_level :: " + logger_level2);

HttpSession session = request.getSession();
session.setAttribute("logger_level", logger_level2);

PatternLayout layout = new PatternLayout();
String conversionPattern = "[%p] %d %c %M - %m%n";
layout.setConversionPattern(conversionPattern);

// creates daily rolling file appender
DailyRollingFileAppender rollingAppender = new DailyRollingFileAppender();
rollingAppender.setName("FileLogger");
rollingAppender.setFile("app.log");
rollingAppender.setDatePattern("'.'yyyy-MM-dd");
rollingAppender.setLayout(layout);
rollingAppender.activateOptions();

// configures the root logger
Logger rootLogger = Logger.getRootLogger();
rootLogger.setLevel(Level.INFO);
rootLogger.addAppender(rollingAppender);

if(logger_level.equals("INFO")){
	logger.info("logger_level info message");

}
else if(logger_level.equals("WARN")){
	logger.warn("logger_level WARN message");

}
else if(logger_level.equals("DEBUG")){
	logger.debug("logger_level DEBUG message");

}
else if(logger_level.equals("ERROR")){
	logger.error("logger_level ERROR message");

}
return new ModelAndView("redirect:logger_level_entryform");


}


@RequestMapping("/logger_level_entryform")
public ModelAndView input_form3(HttpServletRequest request, ModelMap map) 
{
return new ModelAndView("Logger_level_view");
}


// zip patch list
			@RequestMapping("/importLoggerList")
			public ModelAndView importPatchList(HttpServletRequest request, ModelAndView model) throws Exception,IOException 
			{
				    File directory = new File("F:\\sonali_8_1_20\\REAL_NET_GB2\\src\\main\\java\\log\\");
			        //get all the files from a directory
			        File[] fList = directory.listFiles();
			        
			        ArrayList filPaths = new ArrayList();
			        for (File file : fList){
			            System.out.println(file.getName());
			            filPaths.add(file.getName());
			         }
				
			        model.addObject("fList", filPaths);
				
				
				model.setViewName("LoggerFileList");
				return model;
			}
			
			
			//import patch
			@RequestMapping(value = "/importLogger",method = RequestMethod.GET)
			public ModelAndView importPatch(HttpServletRequest request,  HttpServletResponse response, ModelAndView model) throws Exception,IOException 
			{
				
				String filename=request.getParameter("filename");
				System.out.println("file name ::"+filename);
				
				String folderPath="F:/sonali_8_1_20/REAL_NET_GB2/src/main/java/log/";
				
				 if (filename.indexOf(".doc")>-1) response.setContentType("application/msword");
			      if (filename.indexOf(".docx")>-1) response.setContentType("application/msword");
			      if (filename.indexOf(".xls")>-1) response.setContentType("application/vnd.ms-excel");
			      if (filename.indexOf(".csv")>-1) response.setContentType("application/vnd.ms-excel");
			      if (filename.indexOf(".ppt")>-1) response.setContentType("application/ppt");
			      if (filename.indexOf(".pdf")>-1) response.setContentType("application/pdf");
			      if (filename.indexOf(".zip")>-1) response.setContentType("application/zip");
			      response.setHeader("Content-Disposition", "attachment; filename=" +filename);
			      response.setHeader("Content-Transfer-Encoding", "binary");
			      try {
			    	  BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			    	  FileInputStream fis = new FileInputStream(folderPath+filename);
			    	  int len;
			    	  byte[] buf = new byte[1024];
			    	  while((len = fis.read(buf)) > 0) {
			    		  bos.write(buf,0,len);
			    	  }
			    	  bos.close();
			    	  response.flushBuffer();
			      }
			      catch(IOException e) {
			    	  e.printStackTrace();
			    	  
			      }
			
				return new ModelAndView("redirect:importLoggerList");
			}
			
			




/*
//-----------------------for prefield part-----------------------------------

@RequestMapping(value = "/logger_level_update", method = RequestMethod.GET)
public ModelAndView loadReport1(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException 
{
int u_id = Integer.parseInt(id);
Logger_level	logger_level1 = new	Logger_level();
map.addAttribute("logger_level_updt",logger_level1);
List<Logger_level> report =logger_level_service.prefield(u_id);
map.addAttribute("logger_level_update", report);
return new ModelAndView("Logger_level_update");
}

*/






/*
//-----------------------------------for grid view only--------------------------------------------------

@RequestMapping(value="/logger_level_grid_view")
public ModelAndView	logger_levelDetails(ModelAndView model) throws IOException
{
List<logger_level1>	logger_level1=logger_level_dao.userlist();
model.addObject("logger_level1", logger_level1);
System.out.println("sujit");
model.setViewName("Logger_level_grid");
return model;
}

//-----------------------for prefield part-----------------------------------

@RequestMapping(value = "/logger_level_update", method = RequestMethod.GET)
public ModelAndView loadReport1(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException 
{
int u_id = Integer.parseInt(id);
logger_level1	logger_level1 = new	logger_level1();
map.addAttribute("logger_level_updt",logger_level1);
List<logger_level1> report =logger_level_service.prefield(u_id);
map.addAttribute("logger_level_update", report);
return new ModelAndView("Logger_level_update");
}



//--------------------for readonly------------------------------------------------

@RequestMapping(value = "/logger_level_readonly", method = RequestMethod.GET)
public ModelAndView loadReport2(@RequestParam(value = "id") String	id, ModelAndView modelview,
HttpServletRequest request, ModelMap map) throws IOException 
{
int u_id = Integer.parseInt(id);
logger_level1	logger_level1 = new	logger_level1();
map.addAttribute("logger_level_updt",logger_level1);
List<logger_level1> report =logger_level_service.prefield(u_id);
map.addAttribute("logger_level_update", report);
return new ModelAndView("Logger_level_readonly");
}



//--------------------------submit update part---------------------------------------------------

@RequestMapping(value = "/logger_level_update_submit", method = RequestMethod.POST)
public ModelAndView saveReportRegister(@ModelAttribute	logger_level1	logger_level1,
BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException {
int report = 0;
String	id=request.getParameter("id");
	 String textfield1=request.getParameter("textfield1");
	 String textfield2=request.getParameter("textfield2");
	 String textfield3=request.getParameter("textfield3");
	 String textfield4=request.getParameter("textfield4");
int rowcount=id.length();
report = logger_level_service.save(rowcount,id,textfield1,	textfield2,	textfield3,	textfield4	);
String check = request.getParameter("repupdt");
map.addAttribute("check", check);
map.addAttribute("report", report);
	logger_level1	 rep_reg = new 	logger_level1();
map.addAttribute("rep_reg", rep_reg);
List<logger_level1> report_list = logger_level_service.userlist();
map.addAttribute("report_list", report_list);
return new ModelAndView("redirect:logger_level_grid_view");
}*/
}