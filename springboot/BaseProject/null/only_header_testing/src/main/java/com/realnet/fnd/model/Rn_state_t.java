   package com.realnet.fnd.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;import org.hibernate.annotations.GenericGenerator;import java.util.Date;
@Entity
@Table(name = "RN_STATE_T")
public class	Rn_state_t
{
@Id
@GeneratedValue(strategy = GenerationType.AUTO, generator ="native")
@GenericGenerator(name = "native", strategy = "native")

@Column(name = "ID")
private int 	id;


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

@Column(name = "COUNTRY_ID")
private int 	country_id;




@Column(name = "STATE_NAME")
private String 	state_name;


public String getState_name() 
{
return	state_name;
}

public void setState_name(String	state_name)
{
this.state_name=state_name;
}

public int getCountry_id() 
{
return	country_id;
}

public void setCountry_id(int	country_id)
{
this.country_id=country_id;
}
}