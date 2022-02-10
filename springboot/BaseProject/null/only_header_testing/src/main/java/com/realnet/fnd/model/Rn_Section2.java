package com.realnet.fnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RN_SECTION2_T")

public class Rn_Section2 {
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Id
	@Column(name = "ID")
	private int id;

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

	public String getChart_size2() {
		return chart_size2;
	}

	public void setChart_size2(String chart_size2) {
		this.chart_size2 = chart_size2;
	}

	public String getChart4() {
		return chart4;
	}

	public void setChart4(String chart4) {
		this.chart4 = chart4;
	}

	public String getChart5() {
		return chart5;
	}

	public void setChart5(String chart5) {
		this.chart5 = chart5;
	}

	public String getChart6() {
		return chart6;
	}

	public void setChart6(String chart6) {
		this.chart6 = chart6;
	}

	@Column(name = "DASH_ID")
	private int dash_id;

	@Column(name = "CHART_SIZE2")
	private String chart_size2;

	@Column(name = "CHART4")
	private String chart4;

	@Column(name = "CHART5")
	private String chart5;

	@Column(name = "CHART6")
	private String chart6;

}
