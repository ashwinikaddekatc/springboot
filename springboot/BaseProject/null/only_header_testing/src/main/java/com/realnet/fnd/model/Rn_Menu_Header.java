package com.realnet.fnd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
// @Table(name = "Bassplus_Menu_Header")
@Table(name = "Menu_Header2")
public class Rn_Menu_Header implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int header_menu_id;

	@Column(name = "Menu_link_text")
	private String menu_link_text;

	@Column(name = "Menu_action_name")
	private String menu_action_name;

	@Column(name = "ICONS")
	private String icons;

	public String getIcons() {
		return icons;
	}

	public void setIcons(String icons) {
		this.icons = icons;
	}

	public int getHeader_menu_id() {
		return header_menu_id;
	}

	public void setHeader_menu_id(int header_menu_id) {
		this.header_menu_id = header_menu_id;
	}

	public String getMenu_link_text() {
		return menu_link_text;
	}

	public void setMenu_link_text(String menu_link_text) {
		this.menu_link_text = menu_link_text;
	}

	public String getMenu_action_name() {
		return menu_action_name;
	}

	public void setMenu_action_name(String menu_action_name) {
		this.menu_action_name = menu_action_name;
	}

}
