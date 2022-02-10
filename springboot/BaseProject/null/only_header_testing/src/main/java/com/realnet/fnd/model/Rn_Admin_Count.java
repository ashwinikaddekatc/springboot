package com.realnet.fnd.model;

public class Rn_Admin_Count {

	private String distributor_name;
	private int open_claim;
	private int open_sr;
	private int closed_claim;
	private int installed_prf;
	private int rejected_claim;
	
	
	
	public int getRejected_claim() {
		return rejected_claim;
	}
	public void setRejected_claim(int rejected_claim) {
		this.rejected_claim = rejected_claim;
	}
	public String getDistributor_name() {
		return distributor_name;
	}
	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}
	public int getOpen_claim() {
		return open_claim;
	}
	public void setOpen_claim(int open_claim) {
		this.open_claim = open_claim;
	}
	public int getOpen_sr() {
		return open_sr;
	}
	public void setOpen_sr(int open_sr) {
		this.open_sr = open_sr;
	}
	public int getClosed_claim() {
		return closed_claim;
	}
	public void setClosed_claim(int closed_claim) {
		this.closed_claim = closed_claim;
	}
	public int getInstalled_prf() {
		return installed_prf;
	}
	public void setInstalled_prf(int installed_prf) {
		this.installed_prf = installed_prf;
	}

}
