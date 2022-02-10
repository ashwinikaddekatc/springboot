package com.realnet.ncso.entity1;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShipMix {
	private String VESSEL_CODE;
	private String VESSEL_NAME;//yes
	private String LINE_CODE;//yes
	private String IN_VOYAGE;//yes
	private String OUT_VOYAGE;//yes
	private String CALL_NO;//yes	
	private Date ATA;//yes
	private Date ATD;//yes
	private String LOCATION;//yes
	private String SERVICE_ID;//yes
	private BigDecimal LOA;//yes
	private String LOA_UOM;
	private BigDecimal GT;
}
