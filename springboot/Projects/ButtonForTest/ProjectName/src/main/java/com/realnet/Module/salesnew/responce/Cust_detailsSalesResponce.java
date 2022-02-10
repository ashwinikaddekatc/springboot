package com.realnet.comm.Module_1.response;

import java.util.List;

import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Cust_detailsSalesResponce extends PageResponse{	 @ApiModelProperty(required = true, value = "")
	  private List<Cust_detailssales> sales;
}