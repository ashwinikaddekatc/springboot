package com.realnet.comm.GaneshMod.response;

import java.util.List;

import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Ganesh_uiSalesResponce extends PageResponse{	 @ApiModelProperty(required = true, value = "")
	  private List<Ganesh_uisales> sales;
}