package com.realnet.comm.response;

import java.util.List;

import com.realnet.comm.entity.Department;
import com.realnet.comm.entity.Fields;
import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class fieldsResponce extends PageResponse{
	@ApiModelProperty(required = true, value = "")
	private List<Fields> items;

}
