package com.realnet.Module.salesnew.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.Module.salesnew.entity.Flf_builderauthor;
import com.realnet.Module.salesnew.entity.Flf_builderbook;
import com.realnet.Module.salesnew.service.Flf_builderauthorservice;
import com.realnet.utils.WireFrameConstant.FormType;
import com.realnet.wfb.entity.Rn_Fb_Header;
import com.realnet.wfb.entity.Rn_Fb_Line;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "flf_builder" })
public class flf_buildercontroller {
	
	@Autowired
	private Flf_builderauthorservice flf_service;
	
	@Value("${projectPath}")
	private String projectPath;
	
	@GetMapping("/flfservice_builder/{id}")
	public ResponseEntity<?> buildflfservice(@PathVariable(value = "id") Integer id)throws IOException, FileNotFoundException
	{
		try {
		System.out.println("in a flf builder ");
			Flf_builderauthor flf_builder=flf_service.getById(id);
				List<Flf_builderbook> book = flf_builder.getBook();
			String techstack = flf_builder.getTechstack();
			String object_type = flf_builder.getObject_type();
			String sub_object_type = flf_builder.getSub_object_type();
			
			//file name string 
			String filestring=techstack + object_type + sub_object_type+"impl.java" ;
			String filename=techstack + object_type + sub_object_type+"impl" ;
			System.out.println(filestring);
			String pathstring=projectPath+"/src/main/java/com/realnet/Module/salesnew/service/"+filestring;
			
			System.out.println("file path for flf ="+pathstring);
			File servicefile=new File(pathstring);
			
			FileWriter fw = null;
			BufferedWriter bw = null;
			
			StringBuilder servicecode= new StringBuilder();
			
			if(sub_object_type.equals("only_header"))
			{
				servicecode.append("package com.realnet.Module.salesnew.service;\r\n"
						+ "\r\n"
						+ "import java.util.List;\r\n"
						+ "\r\n"
						+ "import org.springframework.beans.factory.annotation.Autowired;\r\n"
						+ "import org.springframework.stereotype.Service;\r\n"
						+ "\r\n"
						+ "import com.realnet.wfb.entity.Rn_Fb_Header;\r\n"
						+ "import com.realnet.wfb.entity.Rn_Fb_Line;\r\n"
						+ "import com.realnet.wfb.service.Rn_WireFrame_Service;\r\n"
						+ "@Service\r\n"
						+ "public class "+filename+" {\r\n"
						+ "\r\n"
						+ "	@Autowired\r\n"
						+ "	private Rn_WireFrame_Service wireFrameService;\r\n"
						
						+"				public String dynamicfields(int id, String filetype, String operationtype, String formtype) {\r\n"
						+ "				Rn_Fb_Header rn_fb_header = wireFrameService.getById(id);\r\n"
						+ "\r\n"
						+ "				List<Rn_Fb_Line> rn_fb_lines = rn_fb_header.getRn_fb_lines();\r\n"
						+ "				StringBuilder FieldsCode = new StringBuilder();\r\n"
						+ "\r\n"
						+ "				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines)\r\n"
						+ "					{\r\n"
						+ "						String typeField=rn_Fb_linefield.getType_field();\r\n"
						+ "        			if (rn_Fb_linefield.getType_field() == null) {\r\n"
						+ "			              		continue;\r\n"
						+ "		           		}\r\n");
				
					for(Flf_builderbook b:book)
					{
						
						//update fields start
						if(b.getField_type().equals("textfield"))
						{
							servicecode.append("\r\n        if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"textfield\") && operationtype.equals(\"update\"))\r\n"
									+ "			       {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			     	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		       	}\r\n");
						}
						
						if(b.getField_type().equals("autocomplete"))
						{
							servicecode.append("\r\n          if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"autocomplete\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("textarea"))
						{
							servicecode.append("\r\n          if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"textarea\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("url"))
						{
							servicecode.append("\r\n          if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"url\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("email"))
						{
							servicecode.append("\r\n          if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"email\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("dropdown"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"dropdown\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("checkbox"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"checkbox\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}

						if(b.getField_type().equals("togglebutton"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"togglebutton\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("datetime"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"datetime\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("contact_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"contact_field\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("currency_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"currency_field\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("masked"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"masked\") && operationtype.equals(\"update\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						//update fields endd
						
						//add fields
						
						if(b.getField_type().equals("textfield"))
						{
							servicecode.append("\r\n        if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"textfield\") && operationtype.equals(\"add\"))\r\n"
									+ "			       {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			     	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		       	}\r\n");
						}
						
						if(b.getField_type().equals("autocomplete"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"autocomplete\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("textarea"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"textarea\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("url"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"url\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("email"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"email\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("dropdown"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"dropdown\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("checkbox"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"checkbox\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}

						if(b.getField_type().equals("togglebutton"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"togglebutton\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("datetime"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"datetime\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("contact_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"contact_field\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("currency_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"currency_field\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("masked"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"masked\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						//add fields end
						
						//read only fields
						
						if(b.getField_type().equals("textfield"))
						{
							servicecode.append("\r\n        if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"textfield\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			       {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			     	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		       	}\r\n");
						}
						
						if(b.getField_type().equals("autocomplete"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"autocomplete\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("textarea"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"textarea\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("url"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"url\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("email"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"email\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("dropdown"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"dropdown\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("checkbox"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"checkbox\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}

						if(b.getField_type().equals("togglebutton"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"togglebutton\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("datetime"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"datetime\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("contact_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"contact_field\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("currency_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"currency_field\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("masked"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"html\") &&  formtype.equals(\"header\") && typeField.equals(\"masked\") && operationtype.equals(\"readonly\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						//readonly fields end
						
						//ts fields add
						
						if(b.getField_type().equals("textfield"))
						{
							servicecode.append("\r\n        if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"textfield\") && operationtype.equals(\"add\"))\r\n"
									+ "			       {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			     	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		       	}\r\n");
						}
						
						if(b.getField_type().equals("autocomplete"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"autocomplete\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("textarea"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"textarea\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("url"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"url\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("email"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"email\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("dropdown"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"dropdown\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("checkbox"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"checkbox\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}

						if(b.getField_type().equals("togglebutton"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"togglebutton\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("datetime"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"datetime\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("contact_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"contact_field\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("currency_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"currency_field\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("masked"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"masked\") && operationtype.equals(\"add\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						//ts add fields end
						
						//ts all fields start
						
						if(b.getField_type().equals("textfield"))
						{
							servicecode.append("\r\n        if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"textfield\") && operationtype.equals(\"all\"))\r\n"
									+ "			       {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			     	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		       	}\r\n");
						}
						
						if(b.getField_type().equals("autocomplete"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"autocomplete\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("textarea"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"textarea\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("url"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"url\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("email"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"email\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("dropdown"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"dropdown\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("checkbox"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"checkbox\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}

						if(b.getField_type().equals("togglebutton"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"togglebutton\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("datetime"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"datetime\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("contact_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"contact_field\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("currency_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"currency_field\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("masked"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"ts\") &&  formtype.equals(\"header\") && typeField.equals(\"masked\") && operationtype.equals(\"all\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						//ts all fields end
						
						//java model  fields start
						if(b.getField_type().equals("textfield"))
						{
							servicecode.append("\r\n        if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"textfield\") && operationtype.equals(\"model\"))\r\n"
									+ "			       {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			     	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		       	}\r\n");
						}
						
						if(b.getField_type().equals("autocomplete"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"autocomplete\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("textarea"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"textarea\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("url"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"url\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("email"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"email\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("dropdown"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"dropdown\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("checkbox"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"checkbox\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}

						if(b.getField_type().equals("togglebutton"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"togglebutton\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("datetime"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"datetime\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("contact_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"contact_field\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("currency_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"currency_field\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("masked"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"masked\") && operationtype.equals(\"model\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						//java model end
						
						//java model getter setter
						
						if(b.getField_type().equals("textfield"))
						{
							servicecode.append("\r\n        if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"textfield\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			       {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			     	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		       	}\r\n");
						}
						
						if(b.getField_type().equals("autocomplete"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"autocomplete\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("textarea"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"textarea\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("url"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"url\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("email"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"email\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("dropdown"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"dropdown\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("checkbox"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"checkbox\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}

						if(b.getField_type().equals("togglebutton"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"togglebutton\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("datetime"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"datetime\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("contact_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"contact_field\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("currency_field"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"currency_field\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						
						if(b.getField_type().equals("masked"))
						{
							servicecode.append("\r\n           if (filetype.equals(\"java\") &&  formtype.equals(\"header\") && typeField.equals(\"masked\") && operationtype.equals(\"modelgettersetter\"))\r\n"
									+ "			           {\r\n"
									+ "System.out.println(typeField);\r\n"
									+ "			            	FieldsCode.append("+b.getCode()+");\r\n"
									+ "		               	}\r\n");
						}
						//java model getter setter 
						
						
						
					}
					servicecode.append("              }\r\n"
							+ "                return FieldsCode.toString();\r\n"
							+ "           }\r\n"
							+ "}\r\n");
						
			}

		if (!servicefile.exists()) {
			servicefile.createNewFile();
			
		}
		
		
		fw = new FileWriter(servicefile.getAbsoluteFile());
		bw = new BufferedWriter(fw);
		bw.write(servicecode.toString());
		bw.close();
		
		return ResponseEntity.ok().body("File created: " + servicefile.getName());
		
		}catch (Exception e) {
			System.out.println("error in flf builder "+e);
			return ResponseEntity.ok().body("File already exists");
		}
		
		
		
	}
 	

}
