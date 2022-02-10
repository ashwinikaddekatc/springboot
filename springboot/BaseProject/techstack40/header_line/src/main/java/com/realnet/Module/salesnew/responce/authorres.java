package com.realnet.comm.response;

import java.util.List;

import com.realnet.comm.entity.Teacher;
import com.realnet.comm.entity.author;
import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class authorres extends PageResponse {
	@ApiModelProperty(required = true, value = "")
	  private List<author> author;

}
