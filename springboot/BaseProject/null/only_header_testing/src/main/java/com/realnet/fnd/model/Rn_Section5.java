package com.realnet.fnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RN_SECTION5_T")


public class Rn_Section5
{
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id
	@Column(name = "ID")
	private int id;
	
	

	@Column(name = "DASH_ID")
	private int dash_id;
	
	@Column(name = "CHART_SIZE5")
	private String chart_size5;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDash_id() {
		return dash_id;
	}

	public void setDash_id(int dash_id) {
		this.dash_id = dash_id;
	}

	public String getChart_size5() {
		return chart_size5;
	}

	public void setChart_size5(String chart_size5) {
		this.chart_size5 = chart_size5;
	}

	public String getChart13() {
		return chart13;
	}

	public void setChart13(String chart13) {
		this.chart13 = chart13;
	}

	public String getChart14() {
		return chart14;
	}

	public void setChart14(String chart14) {
		this.chart14 = chart14;
	}

	public String getChart15() {
		return chart15;
	}

	public void setChart15(String chart15) {
		this.chart15 = chart15;
	}

	@Column(name = "CHART13")
	private String chart13;
	
	@Column(name = "CHART14")
	private String chart14;
	
	@Column(name = "CHART15")
	private String chart15;
	
	
	
	
	

	
}

