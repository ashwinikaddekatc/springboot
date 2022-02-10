package com.sales.jpa.dto;

import com.realnet.comm.entity.Sales;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class salesRequest {
	
	private Sales sales;

}
