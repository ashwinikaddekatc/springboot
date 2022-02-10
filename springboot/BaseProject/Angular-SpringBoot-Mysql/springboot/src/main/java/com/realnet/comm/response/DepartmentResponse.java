package com.realnet.comm.response;

import java.util.List;

import com.realnet.comm.entity.Department;
import com.realnet.comm.entity.Teacher;
import com.realnet.fnd.response.PageResponse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper=false)
public class DepartmentResponse extends PageResponse {
	@ApiModelProperty(required = true, value = "")
	private List<Department> items;

}
