//import_change_repository_start "package com.realnet.comm.response;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.sales;" + "\r\n" + 
"import com.realnet.fnd.response.PageResponse;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"import lombok.EqualsAndHashCode;" + "\r\n" + //import_change_repository_end
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@EqualsAndHashCode(callSuper=false)" + "\r\n" + 
"public class SalesResponse extends PageResponse {" + "\r\n" + 
"	 @ApiModelProperty(required = true, value = \"\")" + "\r\n" + 
"	  private List<sales> sales;" + "\r\n" + 
"}" 