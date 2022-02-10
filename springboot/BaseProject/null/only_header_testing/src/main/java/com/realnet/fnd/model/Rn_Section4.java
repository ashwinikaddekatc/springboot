package com.realnet.fnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RN_SECTION4_T")


public class Rn_Section4
{
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	@Id
	@Column(name = "ID")
	private int id;
	
	

	@Column(name = "DASH_ID")
	private int dash_id;
	
	@Column(name = "CHART_SIZE4")
	private String chart_size4;
	
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

	public String getChart_size4() {
		return chart_size4;
	}

	public void setChart_size4(String chart_size4) {
		this.chart_size4 = chart_size4;
	}

	public String getChart10() {
		return chart10;
	}

	public void setChart10(String chart10) {
		this.chart10 = chart10;
	}

	public String getChart11() {
		return chart11;
	}

	public void setChart11(String chart11) {
		this.chart11 = chart11;
	}

	public String getChart12() {
		return chart12;
	}

	public void setChart12(String chart12) {
		this.chart12 = chart12;
	}

	@Column(name = "CHART10")
	private String chart10;
	
	@Column(name = "CHART11")
	private String chart11;
	
	@Column(name = "CHART12")
	private String chart12;
	
	
	
	
	

	
}

