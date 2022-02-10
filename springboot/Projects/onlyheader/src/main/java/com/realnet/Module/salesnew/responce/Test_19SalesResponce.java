package com.realnet.comm.header.response;

import java.util.List;

import com.realnet.comm.entity.sales;
import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Test_19SalesResponce{	 @ApiModelProperty(required = true, value = "")
	  private List<sales> sales;
}