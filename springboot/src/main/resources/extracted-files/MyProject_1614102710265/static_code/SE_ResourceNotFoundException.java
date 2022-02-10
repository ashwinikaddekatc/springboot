"package com.realnet." + module_name + ".exception;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.http.HttpStatus;" + "\r\n" + 
"import org.springframework.web.bind.annotation.ResponseStatus;" + "\r\n" + 
"" + "\r\n" + 
"@ResponseStatus(value = HttpStatus.NOT_FOUND)" + "\r\n" + 
"public class ResourceNotFoundException extends RuntimeException {" + "\r\n" + 
"	private static final long serialVersionUID = 1L;" + "\r\n" + 
"	" + "\r\n" + 
"	public ResourceNotFoundException(String msg) {" + "\r\n" + 
"		super(msg);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"}" 
