package com.realnet.Module.salesnew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.wfb.entity.Rn_Fb_Header;
import com.realnet.wfb.entity.Rn_Fb_Line;
import com.realnet.wfb.service.Rn_WireFrame_Service;
@Service
public class techstack50formonly_headerimpl {

	@Autowired
	private Rn_WireFrame_Service wireFrameService;
				public String dynamicfields(int id, String filetype, String operationtype, String formtype) {
				Rn_Fb_Header rn_fb_header = wireFrameService.getById(id);

				List<Rn_Fb_Line> rn_fb_lines = rn_fb_header.getRn_fb_lines();
				StringBuilder FieldsCode = new StringBuilder();

				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines)
					{
						String typeField=rn_Fb_linefield.getType_field();
        			if (rn_Fb_linefield.getType_field() == null) {
			              		continue;
		           		}

        if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("textfield") && operationtype.equals("update"))
			       {
System.out.println(typeField);
			     	FieldsCode.append("ddddddddddddddddddddddd");
		       	}

        if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("textfield") && operationtype.equals("add"))
			       {
System.out.println(typeField);
			     	FieldsCode.append("ddddddddddddddddddddddd");
		       	}

        if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("textfield") && operationtype.equals("readonly"))
			       {
System.out.println(typeField);
			     	FieldsCode.append("ddddddddddddddddddddddd");
		       	}

        if (filetype.equals("ts") &&  formtype.equals("header") && typeField.equals("textfield") && operationtype.equals("add"))
			       {
System.out.println(typeField);
			     	FieldsCode.append("ddddddddddddddddddddddd");
		       	}

        if (filetype.equals("ts") &&  formtype.equals("header") && typeField.equals("textfield") && operationtype.equals("all"))
			       {
System.out.println(typeField);
			     	FieldsCode.append("ddddddddddddddddddddddd");
		       	}

        if (filetype.equals("java") &&  formtype.equals("header") && typeField.equals("textfield") && operationtype.equals("model"))
			       {
System.out.println(typeField);
			     	FieldsCode.append("ddddddddddddddddddddddd");
		       	}

        if (filetype.equals("java") &&  formtype.equals("header") && typeField.equals("textfield") && operationtype.equals("modelgettersetter"))
			       {
System.out.println(typeField);
			     	FieldsCode.append("ddddddddddddddddddddddd");
		       	}

         else if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("url") && operationtype.equals("update"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("dddddddddddddddddddddd");
		               	}

         else if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("url") && operationtype.equals("add"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("dddddddddddddddddddddd");
		               	}

         else if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("url") && operationtype.equals("readonly"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("dddddddddddddddddddddd");
		               	}

         else if (filetype.equals("ts") &&  formtype.equals("header") && typeField.equals("url") && operationtype.equals("add"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("dddddddddddddddddddddd");
		               	}

         else if (filetype.equals("ts") &&  formtype.equals("header") && typeField.equals("url") && operationtype.equals("all"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("dddddddddddddddddddddd");
		               	}

         else if (filetype.equals("java") &&  formtype.equals("header") && typeField.equals("url") && operationtype.equals("model"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("dddddddddddddddddddddd");
		               	}

         else if (filetype.equals("java") &&  formtype.equals("header") && typeField.equals("url") && operationtype.equals("modelgettersetter"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("dddddddddddddddddddddd");
		               	}

         else if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("dropdown") && operationtype.equals("update"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("ffffffffffffffffffffffffffffffffffffffff");
		               	}

         else if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("dropdown") && operationtype.equals("add"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("ffffffffffffffffffffffffffffffffffffffff");
		               	}

         else if (filetype.equals("html") &&  formtype.equals("header") && typeField.equals("dropdown") && operationtype.equals("readonly"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("ffffffffffffffffffffffffffffffffffffffff");
		               	}

         else if (filetype.equals("ts") &&  formtype.equals("header") && typeField.equals("dropdown") && operationtype.equals("add"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("ffffffffffffffffffffffffffffffffffffffff");
		               	}

         else if (filetype.equals("ts") &&  formtype.equals("header") && typeField.equals("dropdown") && operationtype.equals("all"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("ffffffffffffffffffffffffffffffffffffffff");
		               	}

         else if (filetype.equals("java") &&  formtype.equals("header") && typeField.equals("dropdown") && operationtype.equals("model"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("ffffffffffffffffffffffffffffffffffffffff");
		               	}

         else if (filetype.equals("java") &&  formtype.equals("header") && typeField.equals("dropdown") && operationtype.equals("modelgettersetter"))
			           {
System.out.println(typeField);
			            	FieldsCode.append("ffffffffffffffffffffffffffffffffffffffff");
		               	}
              }
                return FieldsCode.toString();
           }
}
