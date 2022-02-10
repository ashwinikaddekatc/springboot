package com.realnet.fnd.controller;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.realnet.fnd.model.Rn_User_Pref;
import com.realnet.fnd.model.Rn_Users1;
import com.realnet.fnd.service.Rn_Reset_Password_Service;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


@Controller
public class Rn_Reset_Password_Controller {
	
	@Autowired
	Rn_Reset_Password_Service rn_reset_password_service;
	
	public static final String ALGORITHM = "AES";
    public static final String KEY = "1Hbfh667adfDEJ78";

	@RequestMapping("/resetpassword1")
	public ModelAndView DocumentLoad(ModelMap map,Model model, HttpServletRequest request)
	{	
		String username1 = (String) request.getSession().getAttribute("username");
	Integer user_id = (Integer) request.getSession().getAttribute("userid");

		String kwm_user = (String) request.getSession().getAttribute("kwm_user");
		if (kwm_user != null) {
				System.out.println("autofill");
				Rn_Users1 userreg = new Rn_Users1();
							
				map.addAttribute("userreg", userreg);		
		}
		map.addAttribute("username1", kwm_user);
		map.addAttribute("user_id", user_id);


		return new ModelAndView("resetpassword");
	}
	
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public ModelAndView saveServiceRequest(@ModelAttribute Rn_Users1 Koel_user,BindingResult resultKoel_user ,
												ModelMap map, HttpServletRequest request)  
	{	 
	
		int user_id = (Integer) request.getSession().getAttribute("userid");
		String username1 = (String) request.getSession().getAttribute("username");

		
			String password = request.getParameter("password");	
	// encyption   
			
			 Key key=null;
			   Cipher cipher=null;
			try {
				key = generateKey();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		      
				try {
					cipher = Cipher.getInstance(Rn_User_Registration_Controller.ALGORITHM);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					cipher.init(Cipher.ENCRYPT_MODE, key);
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        byte[] encryptedByteValue=null;
				try {
					encryptedByteValue = cipher.doFinal(password.getBytes("utf-8"));
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        String encryptedValue = new BASE64Encoder().encode(encryptedByteValue);
			
		        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhh"+encryptedValue);
			
			
			Koel_user.setPassword(encryptedValue);				
			Koel_user.setUser_name(username1);						
			Koel_user.setCreated_by(user_id);
			Koel_user.setLast_updated_by(user_id);
		
			
	        int newuserId = rn_reset_password_service.CreateUser(Koel_user,user_id);
				System.out.println(newuserId);
			
			
				return new ModelAndView("redirect:resetpassword");

		//return new ModelAndView("resetpassword");
	}
		

	 @RequestMapping(value = "/FindPasswordPresence", method = RequestMethod.GET)
	 public void findUser(@RequestParam(value = "UserName") String user_name, HttpServletRequest request, HttpServletResponse response)  {
	 try {

		 System.out.println("in controller");
		 
	 List<Rn_Users1> userlist = new ArrayList<Rn_Users1>();
	 String json = null;
	 System.out.println("user name " + user_name);	
	 
	 userlist = rn_reset_password_service.SearchUser(user_name);
	 
	 Rn_User_Pref usr_pref=new Rn_User_Pref();
	 
	 for(int i=0; i<userlist.size(); i++)
		 
		{
		 usr_pref.setUser_id(userlist.get(0).getUser_id());

		 usr_pref.setName(userlist.get(0).getUser_name());
		// usr_pref.setPassword(userlist.get(0).getPassword());
		 String pass = userlist.get(0).getPassword();
		 
		 // decryption code
		 
		
		        Key key=null;
				try {
					key = generateKey();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        Cipher cipher=null;
				try {
					cipher = Cipher.getInstance(Rn_Reset_Password_Controller.ALGORITHM);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					cipher.init(Cipher.DECRYPT_MODE, key);
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        byte [] decryptedValue64 = new BASE64Decoder().decodeBuffer(pass);
		        byte[] decryptedByteValue=null;
				try {
					decryptedByteValue = cipher.doFinal(decryptedValue64);
				} catch (IllegalBlockSizeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (BadPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        String decryptedValue = new String(decryptedByteValue,"utf-8");
		      
		        usr_pref.setPassword(decryptedValue);
		
		}
	 
	 json = new Gson().toJson(usr_pref);
	

	 response.setContentType("application/json");
	 response.getWriter().write(json);
	 } catch (IOException e) {
	 	e.printStackTrace();
	 }
	 }

	 
	 //////password //////
	 
	 private static Key generateKey() throws Exception 
	    {
	        Key key = new SecretKeySpec(Rn_User_Registration_Controller.KEY.getBytes(),Rn_User_Registration_Controller.ALGORITHM);
	        return key;
	    }
	 
}
	
	
	

	
	
	


