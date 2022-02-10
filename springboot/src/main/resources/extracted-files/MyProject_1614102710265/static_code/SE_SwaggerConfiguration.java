"package com.realnet." + module_name + ".configuration;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.context.annotation.Bean;" + "\r\n" + 
"import org.springframework.context.annotation.Configuration;" + "\r\n" + 
"" + "\r\n" + 
"import springfox.documentation.builders.RequestHandlerSelectors;" + "\r\n" + 
"import springfox.documentation.spi.DocumentationType;" + "\r\n" + 
"import springfox.documentation.spring.web.plugins.Docket;" + "\r\n" + 
"import springfox.documentation.swagger2.annotations.EnableSwagger2;" + "\r\n" + 
"import springfox.documentation.builders.PathSelectors;" + "\r\n" + 
"" + "\r\n" + 
"@EnableSwagger2" + "\r\n" + 
"@Configuration" + "\r\n" + 
"public class SwaggerConfiguration {" + "\r\n" + 
"	@Bean" + "\r\n" + 
"	public Docket api() {" + "\r\n" + 
"		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())" + "\r\n" + 
"				.paths(PathSelectors.any()).build();" + "\r\n" + 
"                " + "\r\n" + 
"	}" + "\r\n" + 
"}" 
