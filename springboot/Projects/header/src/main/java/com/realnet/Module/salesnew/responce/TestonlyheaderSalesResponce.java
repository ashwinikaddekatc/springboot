package com.realnet.Module.salesnew.responce;

import java.util.List;


import com.realnet.Module.salesnew.entity.Testonlyheadersales;
import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TestonlyheaderSalesResponce extends PageResponse{	 @ApiModelProperty(required = true, value = "")
	  private List<Testonlyheadersales> sales;
}