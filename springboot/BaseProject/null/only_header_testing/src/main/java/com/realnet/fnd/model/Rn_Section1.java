package com.realnet.fnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RN_SECTION1_T")

public class Rn_Section1 {
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

	public String getChart1() {
		return chart1;
	}

	public void setChart1(String chart1) {
		this.chart1 = chart1;
	}

	public String getChart2() {
		return chart2;
	}

	public void setChart2(String chart2) {
		this.chart2 = chart2;
	}

	public String getChart3() {
		return chart3;
	}

	public void setChart3(String chart3) {
		this.chart3 = chart3;
	}

	@Column(name = "DASH_ID")
	private int dash_id;

	@Column(name = "CHART_SIZE1")
	private String chart_size1;

	public String getChart_size1() {
		return chart_size1;
	}

	public void setChart_size1(String chart_size1) {
		this.chart_size1 = chart_size1;
	}

	@Column(name = "CHART1")
	private String chart1;

	@Column(name = "CHART2")
	private String chart2;

	@Column(name = "CHART3")
	private String chart3;

}
