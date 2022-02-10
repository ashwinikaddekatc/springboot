package com.realnet.Module.salesnew.responce;

import java.util.List;

import com.realnet.Module.salesnew.entity.*;
import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Hlnewauthorres  extends PageResponse{	@ApiModelProperty(required = true, value = "")
	  private List<Hlnewauthor> author;

}