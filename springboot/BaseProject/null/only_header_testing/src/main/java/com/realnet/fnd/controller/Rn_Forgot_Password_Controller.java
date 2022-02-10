package com.realnet.fnd.controller;

import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.realnet.fnd.dao.Rn_Forgot_Password_Dao;
import com.realnet.fnd.dao.Rn_News_Login_Dao;
import com.realnet.fnd.model.Rn_News_Login;
import com.realnet.fnd.model.Rn_OTP;
import com.realnet.fnd.model.Rn_Users;
import com.realnet.fnd.service.Rn_User_Registration_Service;;


@Controller
public class Rn_Forgot_Password_Controller {

	
	@Autowired
	private Rn_News_Login_Dao rn_news_login_dao;
	
	@Autowired
	private Rn_Forgot_Password_Dao rn_forgot_password_dao;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	@Autowired
	private Rn_User_Registration_Service rn_user_Registration_Service;

	private int otp;

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	public static final String ALGORITHM = "AES";
    public static final String KEY = "1Hbfh667adfDEJ78";
	
	@RequestMapping(value="/forgotpassword")
	public ModelAndView forgotpassword(ModelAndView model,ModelMap map) throws IOException
	{		
		return new ModelAndView("forgotpassword");
		
	}
	
	@RequestMapping(value="/forgotpassword2")
	public ModelAndView forgotpassword2(ModelAndView model,ModelMap map) throws IOException
	{		
		return new ModelAndView("forgototp");
		
	}
	
	
	@RequestMapping(value="/forgotpassword3")
	public ModelAndView forgotpassword3(ModelAndView model,ModelMap map) throws IOException
	{		
		return new ModelAndView("otpenter");
		
	}
	
	
	@RequestMapping(value="/forgotpassword4")
	public ModelAndView forgotpassword4(ModelAndView model,ModelMap map) throws IOException
	{		
		return new ModelAndView("otpenter");
		
	}
	
	@RequestMapping(value="/newpassword")
	public ModelAndView forgotpassword5(ModelAndView model,ModelMap map) throws IOException
	{		
		return new ModelAndView("newpassword");
		
	}
	
	
	

	@Transactional
	@RequestMapping(value = "/forgot_password", method = RequestMethod.POST)
	public ModelAndView updatedistributormaster(@ModelAttribute Rn_Users menuregister,Model model, BindingResult result, 
			ModelMap map, HttpServletRequest request) throws ParseException, NoSuchAlgorithmException
	{
		Rn_Users kwm_users1 = new Rn_Users();
		
		String user_name = request.getParameter("user_name");

			System.out.println("user Name"+user_name);
			List<Rn_Users> ForgotPassword_List = rn_forgot_password_dao.ForgotPassword_List(user_name);
			String email1=null;
			String password=null;
			
			if(ForgotPassword_List.get(0).getEmail_address()!=null && ForgotPassword_List.get(0).getPassword()!=null)
			{
			   email1=ForgotPassword_List.get(0).getEmail_address();
			   password = ForgotPassword_List.get(0).getPassword();
			}
			if(email1.equals("no_email") && password.equals("no_password"))
			{
				model.addAttribute("kwm_users", kwm_users1);
				return new ModelAndView("Realnetlogin");
			}
			else
			{
				
				int ab=0;
			try {
				generateAndSendEmail(password,email1,user_name);
				System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");

				ab = 5;
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ab=3;
			}
			
			//return new ModelAndView("koelwslogin");
			
			Rn_Users kwm_users = new Rn_Users();

			map.addAttribute("kwm_users", kwm_users);

			System.out.println("wasib login");
			List<Rn_News_Login> NewsLogin_List = rn_news_login_dao.DashbordApprover_List();
			model.addAttribute("NewsLogin", NewsLogin_List);
			model.addAttribute("ab",ab);

			return new ModelAndView("redirect:forgotpassword3");

			}
	
	}

	public static void generateAndSendEmail(String password1,String email,String user_name) throws AddressException, MessagingException, NoSuchAlgorithmException {

		System.out.println("email address--->"+email);
		System.out.println("password--------->"+password1);
		
		
		Random t = new Random();
    	String otp =String.valueOf(t.nextInt(999999));
    	System.out.println(" DATADB OTP  IS---->"+otp);
    	Rn_OTP rn_otp=new Rn_OTP();
    	rn_otp.setEmail_otp(otp);
      
    	 MessageDigest md = MessageDigest.getInstance("MD5");
         md.update(otp.getBytes());
        
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
     
        System.out.println("Digest(in hex format):: " + sb.toString());
        
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	
    	
		//PreparedStatement stmt2;
		
					
		
	    String host ="smtp.gmail.com";
	    String port="587";
		final String user =email;// change accordingly
		final String password =password1;// change accordingly
		
		//String to = email;
		String to =email;
		String subject="Forgot your password";
		String content="your otpb is ="+otp;
	    Properties props = new Properties();
			   props.put("mail.smtp.host",host);
			   props.put("mail.smtp.port",port);
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
			  
			   Session session = Session.getDefaultInstance(props,
			    new javax.mail.Authenticator() 
			   {
			      protected PasswordAuthentication getPasswordAuthentication() 
			      {
			    	  	return new PasswordAuthentication(user,password);
			      	}
			    });

			   //Compose the message
			    try {
					     MimeMessage message = new MimeMessage(session);
					     message.setFrom(new InternetAddress(user));
					     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
					     message.setSubject(subject);
					     message.setText(content);
			    
			    //send the message
			     Transport.send(message);

			     System.out.println("message sent successfully...");
			 
			     } catch (Exception e) {
			    	 System.out.println(e);
			
			     }	
	}

	 //////password //////
	 
	 private static Key generateKey() throws Exception 
	    {
	        Key key = new SecretKeySpec(Rn_User_Registration_Controller.KEY.getBytes(),Rn_User_Registration_Controller.ALGORITHM);
	        return key;
	    }
	 
	 
		 //======ONE MORE ATTEMPT FOR OTP==========

	

	 //3red attempt
	 
	
	
	@RequestMapping(value="/forgototpResetPassword")
	public ModelAndView help1(ModelMap map,Model model, HttpServletRequest request)
	{
		
		
	return new ModelAndView("forgototpResetPassword");
	}
	
	@Transactional
	@RequestMapping(value = "/forgototp")
	public ModelAndView saveServiceRequest(@ModelAttribute Rn_Users rn,BindingResult resultrn_user ,
												Model model,ModelMap map, HttpServletRequest request) throws Exception  
	{
	
	String user_name = request.getParameter("user_name");
	
	System.out.println("user Name"+user_name);
	List<Rn_Users> ForgotPassword_List = rn_forgot_password_dao.ForgotPassword_List(user_name);
	String email1=ForgotPassword_List.get(0).getEmail_address();
	String password1= ForgotPassword_List.get(0).getPassword();
	int user_id =ForgotPassword_List.get(0).getUser_id();
	System.out.println("email id ganesh.........."+email1);
	System.out.println("pass by ganesh.........."+password1);
	System.out.println("user_id by ganesh.........."+user_id);
	
	
	
	    //random
	    Random t = new Random();
    	String otp =String.valueOf(t.nextInt(999999));
    	String otp2 =String.valueOf(t.nextInt(999999));
    	System.out.println(" DATADB OTP  IS---->"+otp);
    	System.out.println(" DATADB OTP2  IS---->"+otp2);
    	
    	Rn_OTP rn_otp1=new Rn_OTP();
    	rn_otp1.setEmail_otp(otp);
    	rn_otp1.setPhone_otp(otp2);
    	rn_otp1.setUser_name(user_name);
    	rn_otp1.setUser_id(user_id);
    	
    	
    	
    	
    	MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(otp.getBytes());
        
        byte byteData[]=md.digest();
 
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
     
        System.out.println("Digest(in hex format):: " + sb.toString());
        
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    
     	
  		 
   // 	============================//for encryption====================================================
    	
    //	=======
		

    	
   
	 

	
	
	 
	
	 
	 int id=0;
	 if(request.getParameter("otp_id")!=" "){
     request.getParameter("otp_id");
	 
	 }
	 
	 rn_user_Registration_Service.CreateUser2(rn_otp1,id);

	//===========for mail send otp====================
	 
	 
	
	    String host = "smtp.gmail.com";
	    String port="587";
	 
	    final String user = email1;// change accordingly
		final String password = password1;// change accordingly
		String otp_id1="19";
		//String to = email;
		String to = email1;
		String subject="Insert your 6 digit OTP";
		String content="your email OTP is ="+otp+"\n your phone OTP is ="+otp2+"\n click to enter otp:http://localhost:8087/WASIB_UAT/otpgenratelink?otp_id="+otp_id1+"";
	    Properties props = new Properties();
			   props.put("mail.smtp.host",host);
			   props.put("mail.smtp.port",port);
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
			  
			   Session session = Session.getDefaultInstance(props,
			    new javax.mail.Authenticator() {
			      protected PasswordAuthentication getPasswordAuthentication() {
			    	  	return new PasswordAuthentication(user,password);
			      	}
			    });
			   //Compose the message
			    try {
					     MimeMessage message = new MimeMessage(session);
					     message.setFrom(new InternetAddress(user));
					     message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
					     message.setSubject(subject);
					     message.setText(content);
			    
			    //send the message
			     Transport.send(message);

			     System.out.println("OTP sent successfully...");
			 
			     } catch (Exception e) {
			    	 System.out.println(e);
			
			     }	
			    
			    //-==========================================================================================
			    
	 return new ModelAndView("redirect:forgotpassword3");
	 
	 }
	
	
	
	
	 @RequestMapping(value = "/otpgenratelink", method = RequestMethod.GET)
		public ModelAndView loadReport(@RequestParam(value = "otp_id") String otp_id, ModelAndView modelview,
				HttpServletRequest request, ModelMap map) throws IOException, SQLException 
	   {
			
			 int o_id = Integer.parseInt(otp_id);
			 //String user_name = request.getParameter("user_name");
		     System.out.println("ganesh user id"+otp_id);
		    
	         Rn_OTP otp = new Rn_OTP();
	         map.addAttribute("otp_updt", otp);
	         
			 List<Rn_OTP> otp_report = rn_user_Registration_Service.otp_report(o_id);
			 map.addAttribute("report_update", otp_report);
			 return new ModelAndView("otpenter");
	 }
	
	 
	 
	 
	 
	 
	 
	/*//for verify OTP
		@Transactional
		@RequestMapping("/forgototpVerify")
		public ModelAndView help(@ModelAttribute RN_OTP rn_otp,ModelMap map,Model model, HttpServletRequest request)
		{
			int email_otp = 0;
			//int otp_id = Integer.parseInt(rn_otp);
			//int otp_id = 0;
			int phone_otp = 0;
			List<RN_OTP> verifyotp_list= contactDAO.verifyotp_list(otp_id,email_otp,phone_otp);
		    model.addAttribute("verifyotp_list",verifyotp_list);
		    return new ModelAndView("forgototpVerify");
		}*/
		
		
		
		
		
		
		




 
 
 
 
 
/*@RequestMapping(value = "/verifyotp", method=RequestMethod.POST)
public ModelAndView getLoginForm(HttpServletRequest request, @ModelAttribute("rn_otp") RN_OTP rn_otp,
		BindingResult resultkwm_users, final RedirectAttributes re, ModelMap map,Model model) 
{
   int otp_id = (Integer) request.getSession().getAttribute("opt_id");
 //int phone_otp = (Integer) request.getSession().getAttribute("phone_otp");
 //int email_otp = (Integer) request.getSession().getAttribute("email_otp");

	int phone_otp=Integer.parseInt(request.getParameter("phone_otp"));
    int email_otp=Integer.parseInt(request.getParameter("email_otp"));


	List<RN_OTP> otp_list=contactDAO.getUser3(otp_id,phone_otp,email_otp);
	System.out.println("verify otp query"+otp_list);
	
	int otp_id1 = (Integer) request.getSession().getAttribute("opt_id");
	System.out.println(otp_id1);
	//model.addAttribute("otp_list", otp_list);
	if(phone_otp ==phone_otp)
	{
		return new ModelAndView("newpassword"); 	
	}
	else
	{
	   return new ModelAndView("redirect:forgotpassword");
	}
}*/
	 
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/verifyotp", method=RequestMethod.POST)
	 public ModelAndView getLoginForm(HttpServletRequest request, @ModelAttribute("rn_otp") Rn_OTP rn_otp,
	 		BindingResult resultkwm_users, final RedirectAttributes re, ModelMap map,Model model) 
	 {
		 
		 
		 //int otp_id = (Integer) request.getSession().getAttribute("opt_id");
		 //int phone_otp = (Integer) request.getSession().getAttribute("phone_otp");
		// int email_otp = (Integer) request.getSession().getAttribute("email_otp");
	    // System.out.println("phone otp by ganesh "+phone_otp);
	     //System.out.println("email otp by ganesh "+email_otp);
	     
         
		//List<RN_OTP> otp_list=contactDAO.getUser3(otp_id,phone_otp,email_otp);

		 List<Rn_OTP> report = rn_user_Registration_Service.getUid();
		 
		String phone_otp= report.get(0).getPhone_otp();
		String email_otp= report.get(0).getEmail_otp();
		report.get(0).getOtp_id();
		
		System.out.println("phone otp by ganesh "+phone_otp);
		System.out.println("email otp by ganesh "+email_otp);
		System.out.println(" otp by ganesh "+otp);
		
		String phone_otp1=request.getParameter("phone_otp");
	    String email_otp1=request.getParameter("email_otp");
	    
	 	if(phone_otp1.equals(phone_otp) && email_otp1.equals(email_otp) )
	 	{  
	 		
	 		//int u_id = Integer.parseInt(id);
			
			//RN_OTP rn = new RN_OTP();
			////map.addAttribute("rep_updt", rn);
			//List<RN_OTP> report = serviceUserRegistration.getUid();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
			//map.addAttribute("report_update", report);
	 		return new ModelAndView("newpassword"); 	
	 	}
	 	else
	 	{
	 	   return new ModelAndView("redirect:forgotpassword3");
	 	}
	 }
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
		public ModelAndView saveReportRegister(@ModelAttribute Rn_Users rn_users,
				BindingResult resultReportRegister, ModelMap map, HttpServletRequest request) throws ParseException 
	 {
			
			String user_id[]=request.getParameterValues("user_id");
			String user_name[] = request.getParameterValues("user_name");      
			String password[] = request.getParameterValues("password");
			//String cpassword[] = request.getParameterValues("cpassword");
			
			int rowcount=user_id.length;

			int  report = rn_user_Registration_Service.resetpass(rowcount,user_id,user_name,password );
			System.out.println(report);
			String check = request.getParameter("repupdt");
			map.addAttribute("check", check);
			map.addAttribute("report", report);
			
			Rn_Users rep_reg = new Rn_Users();
			map.addAttribute("rep_reg", rep_reg);
			List<Rn_Users> report_list = rn_user_Registration_Service.rn_userlist();
			map.addAttribute("report_list", report_list);
			
			return new ModelAndView("redirect:distributordetails1");
		}
		

	 
	 
	 
	 
	 
	 
	 
	 
	 @Transactional
		@RequestMapping(value = "/rspass", method = RequestMethod.POST)
		public ModelAndView saveLookup1(@ModelAttribute Rn_Users ru, BindingResult result, 
				ModelMap map, HttpServletRequest request) throws ParseException
		{
			
			   //int user_id = (Integer) request.getSession().getAttribute("userid");
			   //System.out.println("User id sujit"+user_id);
			
			    String pass = request.getParameter("password");
			    ru.setUser_id(88);
			    ru.setPassword(pass);
			    System.out.println("password  by ganesh----"+pass);
			
				hibernateTemplate.saveOrUpdate(ru);
			
			return new ModelAndView("redirect:koellogin");
			
				
		}
	
}
	

