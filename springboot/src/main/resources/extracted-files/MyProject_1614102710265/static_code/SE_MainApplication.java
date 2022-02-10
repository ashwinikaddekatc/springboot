"package com." + module_name + ".realnet;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.boot.SpringApplication;" + "\r\n" + 
"import org.springframework.boot.autoconfigure.SpringBootApplication;" + "\r\n" + 
"import org.springframework.data.jpa.repository.config.EnableJpaAuditing;" + "\r\n" + 
"" + "\r\n" + 
"" + "\r\n" + 
"@SpringBootApplication" + "\r\n" + 
"@EnableJpaAuditing	////  Enabling JPA Auditing" + "\r\n" + 
"public class MainApplication {" + "\r\n" + 
"	public static void main(String[] args) {" + "\r\n" + 
"		SpringApplication.run(MainApplication.class, args);" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
