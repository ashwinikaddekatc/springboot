package com.realnet.fnd.dao;

import java.util.List;

import com.realnet.fnd.model.Rn_OTP;
import com.realnet.fnd.model.Rn_Users;

public interface Rn_Forgot_Password_Dao {


	public List<Rn_Users> ForgotPassword_List(String user_name);
	public List<Rn_OTP> otp_report(int otp_id);
	//public List<RN_OTP> verifyotp_list(int otp_id,int email_otp,int phone_otp);
	public List<Rn_OTP> getUser3(int otp_id,int phone_otp,int email_otp); 
	public List<Rn_OTP> getUid();
	public int resetpass(int rowcount, String[] user_id, String[] user_name,String[] password);
}
