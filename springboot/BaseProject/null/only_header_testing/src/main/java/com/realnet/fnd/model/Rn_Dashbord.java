package com.realnet.fnd.model;

import java.sql.Date;

public class Rn_Dashbord {
//    approver dash bord model-------------------------------------------------
	private Integer claim_number;
	private String country_code;
	private Integer pending_claims ;
	private Integer approved_claims ;
	private Integer total_claims ;
	private Integer rejected_claims;
	
	private String wf_notification_msg;
	
	
	public String getWf_notification_msg() {
		return wf_notification_msg;
	}
	public void setWf_notification_msg(String wf_notification_msg) {
		this.wf_notification_msg = wf_notification_msg;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public Integer getClaim_number() {
		return claim_number;
	}
	public void setClaim_number(Integer claim_number) {
		this.claim_number = claim_number;
	}
	public Integer getPending_claims() {
		return pending_claims;
	}
	public void setPending_claims(Integer pending_claims) {
		this.pending_claims = pending_claims;
	}
	public Integer getApproved_claims() {
		return approved_claims;
	}
	public void setApproved_claims(Integer approved_claims) {
		this.approved_claims = approved_claims;
	}
	public Integer getTotal_claims() {
		return total_claims;
	}
	public void setTotal_claims(Integer total_claims) {
		this.total_claims = total_claims;
	}
	public Integer getRejected_claims() {
		return rejected_claims;
	}
	public void setRejected_claims(Integer rejected_claims) {
		this.rejected_claims = rejected_claims;
	}
	//-----------------------dealer dashboard---------------------
	private Integer pending_srdealer;
	private Integer submitted_srdealer;
	private Integer converted_srdealer;
	private Integer total_srdealer;
	private Integer approved_claimsdealer;
	private Integer rejected_claimsdealer;
	private Integer inprocess_claimsdealer;
	private Integer total_claimsdealer;
	public Integer getPending_srdealer() {
		return pending_srdealer;
	}
	public void setPending_srdealer(Integer pending_srdealer) {
		this.pending_srdealer = pending_srdealer;
	}
	public Integer getSubmitted_srdealer() {
		return submitted_srdealer;
	}
	public void setSubmitted_srdealer(Integer submitted_srdealer) {
		this.submitted_srdealer = submitted_srdealer;
	}
	public Integer getConverted_srdealer() {
		return converted_srdealer;
	}
	public void setConverted_srdealer(Integer converted_srdealer) {
		this.converted_srdealer = converted_srdealer;
	}
	public Integer getTotal_srdealer() {
		return total_srdealer;
	}
	public void setTotal_srdealer(Integer total_srdealer) {
		this.total_srdealer = total_srdealer;
	}
	public Integer getApproved_claimsdealer() {
		return approved_claimsdealer;
	}
	public void setApproved_claimsdealer(Integer approved_claimsdealer) {
		this.approved_claimsdealer = approved_claimsdealer;
	}
	public Integer getRejected_claimsdealer() {
		return rejected_claimsdealer;
	}
	public void setRejected_claimsdealer(Integer rejected_claimsdealer) {
		this.rejected_claimsdealer = rejected_claimsdealer;
	}
	public Integer getInprocess_claimsdealer() {
		return inprocess_claimsdealer;
	}
	public void setInprocess_claimsdealer(Integer inprocess_claimsdealer) {
		this.inprocess_claimsdealer = inprocess_claimsdealer;
	}
	public Integer getTotal_claimsdealer() {
		return total_claimsdealer;
	}
	public void setTotal_claimsdealer(Integer total_claimsdealer) {
		this.total_claimsdealer = total_claimsdealer;
	}
	//-----------------------Sales person ---------------------------------
	
	private Integer pending_srsales;
	private Integer submitted_srsales;
	private Integer converted_srsales;
	private Integer total_srsales;
	private Integer approved_claimssales;
	private Integer rejected_claimssales;
	private Integer inprocess_claimssales;
	private Integer total_claimssales;
	public Integer getPending_srsales() {
		return pending_srsales;
	}
	public void setPending_srsales(Integer pending_srsales) {
		this.pending_srsales = pending_srsales;
	}
	public Integer getSubmitted_srsales() {
		return submitted_srsales;
	}
	public void setSubmitted_srsales(Integer submitted_srsales) {
		this.submitted_srsales = submitted_srsales;
	}
	public Integer getConverted_srsales() {
		return converted_srsales;
	}
	public void setConverted_srsales(Integer converted_srsales) {
		this.converted_srsales = converted_srsales;
	}
	public Integer getTotal_srsales() {
		return total_srsales;
	}
	public void setTotal_srsales(Integer total_srsales) {
		this.total_srsales = total_srsales;
	}
	public Integer getApproved_claimssales() {
		return approved_claimssales;
	}
	public void setApproved_claimssales(Integer approved_claimssales) {
		this.approved_claimssales = approved_claimssales;
	}
	public Integer getRejected_claimssales() {
		return rejected_claimssales;
	}
	public void setRejected_claimssales(Integer rejected_claimssales) {
		this.rejected_claimssales = rejected_claimssales;
	}
	public Integer getInprocess_claimssales() {
		return inprocess_claimssales;
	}
	public void setInprocess_claimssales(Integer inprocess_claimssales) {
		this.inprocess_claimssales = inprocess_claimssales;
	}
	public Integer getTotal_claimssales() {
		return total_claimssales;
	}
	public void setTotal_claimssales(Integer total_claimssales) {
		this.total_claimssales = total_claimssales;
	}
	
	
	//----------------------------------------------------------------------
}
