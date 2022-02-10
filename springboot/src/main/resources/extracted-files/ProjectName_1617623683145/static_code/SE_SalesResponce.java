"package com.realnet.comm." + module_name + ".response;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.comm.entity.Customer;" + "\r\n" + 
"import com.realnet.comm.entity.Sales;" + "\r\n" + 
"import com.realnet.fnd.response.PageResponse;" + "\r\n" + 
"" + "\r\n" + 
"import io.swagger.annotations.ApiModelProperty;" + "\r\n" + 
"import lombok.Data;" + "\r\n" + 
"import lombok.EqualsAndHashCode;" + "\r\n" + 
"" + "\r\n" + 
"@Data" + "\r\n" + 
"@EqualsAndHashCode(callSuper=false)" + "\r\n" + 
"public class " + salesresponse + "{"
"	@ApiModelProperty(required = true, value = \"\")" + "\r\n" + 
"	  private List<Sales> items;" + "\r\n" + 
"" + "\r\n" + 
"}" 
