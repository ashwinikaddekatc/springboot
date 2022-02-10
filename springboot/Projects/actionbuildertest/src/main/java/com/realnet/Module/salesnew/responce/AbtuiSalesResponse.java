package com.realnet.Module.salesnew.responce;

import java.util.List;

import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.realnet.Module.salesnew.entity.Abtuisales;

@Data
@EqualsAndHashCode(callSuper=false)
public class AbtuiSalesResponse extends PageResponse{	 @ApiModelProperty(required = true, value = "")
	  private List<Abtuisales> sales;
}