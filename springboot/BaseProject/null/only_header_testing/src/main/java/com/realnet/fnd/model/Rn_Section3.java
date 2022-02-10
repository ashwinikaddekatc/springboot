package com.realnet.fnd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RN_SECTION3_T")

public class Rn_Section3 {
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

	public String getChart_size3() {
		return chart_size3;
	}

	public void setChart_size3(String chart_size3) {
		this.chart_size3 = chart_size3;
	}

	public String getChart7() {
		return chart7;
	}

	public void setChart7(String chart7) {
		this.chart7 = chart7;
	}

	public String getChart8() {
		return chart8;
	}

	public void setChart8(String chart8) {
		this.chart8 = chart8;
	}

	public String getChart9() {
		return chart9;
	}

	public void setChart9(String chart9) {
		this.chart9 = chart9;
	}

	@Column(name = "DASH_ID")
	private int dash_id;

	@Column(name = "CHART_SIZE3")
	private String chart_size3;

	@Column(name = "CHART7")
	private String chart7;

	@Column(name = "CHART8")
	private String chart8;

	@Column(name = "CHART9")
	private String chart9;

}
