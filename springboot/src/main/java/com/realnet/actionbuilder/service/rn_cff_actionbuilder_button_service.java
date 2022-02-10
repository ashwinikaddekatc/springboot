package com.realnet.actionbuilder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Header;
import com.realnet.actionbuilder.entity.Rn_cff_ActionBuilder_Lines;
import com.realnet.wfb.entity.Rn_Fb_Header;
import com.realnet.wfb.entity.Rn_Fb_Line;
import com.realnet.wfb.service.Rn_WireFrame_Service;

@Service
public class rn_cff_actionbuilder_button_service {
	
	@Autowired
	private Rn_Cff_ActionBuilder_Service actionBuilderService;
	
	@Autowired
	private Rn_WireFrame_Service wireFrameService;
	
	public void actionbuilderbutton(int id,String controllerName,String technology_stack,
			String file_location,String sbohservice1,String sbohentity1) {
		Rn_Fb_Header rn_fb_header = wireFrameService.getById(id);
		
		System.out.println("in a service");
		List<Rn_Fb_Line> rn_fb_lines = rn_fb_header.getRn_fb_lines();
		
//		System.out.println("FILE LOCATION SUBSTRING = " + file_location);
		for(Rn_Fb_Line line:rn_fb_lines)
		{
			if( !line.getFieldName().equals("submit") && line.getType_field().equals("button") )
			{
				
				System.out.println(line.getFieldName());
//				System.out.println(controllerName);
//		controllerName = controllerName.concat(".java");
		System.err.println(controllerName);
		Rn_cff_ActionBuilder_Header header = new Rn_cff_ActionBuilder_Header();
		     header.setRn_fb_header(rn_fb_header);// SAVE rn_fb_header_id
			header.setTechnologyStack(technology_stack);
			header.setControllerName(controllerName); // NO NEED
			header.setMethodName(line.getMethodName());
			header.setFileLocation(file_location);
			header.setActionName(line.getMethodName());
			header.setType(line.getAction());
			header.setService_name(sbohservice1);// action name and method name is same
	   Rn_cff_ActionBuilder_Header actionbuilder=actionBuilderService.save(header);
	   
	   
//	   String[] inVar = {"id","label1","label2"};
	   String[] inVar = line.getRequest_param().split(",");
	   System.out.println("in var"+inVar);
	   
	   String[] inVarDataType = {"int","varchar","varchar"};
	   
	   for(int i=0;i<inVar.length;i++) {
		   Rn_cff_ActionBuilder_Lines actionParam=new Rn_cff_ActionBuilder_Lines();
		   actionParam.setRn_cff_actionBuilderHeader(actionbuilder);
		   actionParam.setVariableName(inVar[i]);
		   actionParam.setActionType1("in_var");
		   actionParam.setDataType(inVarDataType[i]);
		   actionParam.setSeq(i+1);
		   actionBuilderService.save(actionParam); 
	   }
	   
	   
	   Rn_cff_ActionBuilder_Lines actionLines1=new Rn_cff_ActionBuilder_Lines();
	   actionLines1.setRn_cff_actionBuilderHeader(actionbuilder);
	   actionLines1.setVariableName(sbohentity1);
	   actionLines1.setActionType1("model");
	   actionLines1.setSeq(4);
	   actionBuilderService.save(actionLines1);
	   int count=5;
	   for(Rn_Fb_Line lineList:rn_fb_lines) {
		   String fieldName=lineList.getFieldName();
		   String dataType=lineList.getDataType();
		   String typeField=lineList.getType_field();
		   
		   System.out.println("Type Field::"+typeField);
		   if(!typeField.equals("section") && !typeField.equals("id") && !typeField.equals("button")) {
			   Rn_cff_ActionBuilder_Lines actionLines=new Rn_cff_ActionBuilder_Lines();
			   actionLines.setRn_cff_actionBuilderHeader(actionbuilder);
			   actionLines.setActionType1("variable");
			   actionLines.setVariableName(fieldName);
			   actionLines.setDataType(dataType);
			   actionLines.setAssignment("null");
			   actionLines.setSeq(count);
			   actionBuilderService.save(actionLines);  
			   count++;
		   }
		   
		    
	   }
	  
	   Rn_cff_ActionBuilder_Lines actionLinesservice=new Rn_cff_ActionBuilder_Lines();
	   actionLinesservice.setRn_cff_actionBuilderHeader(actionbuilder);
	   actionLinesservice.setVariableName(sbohservice1);
	   actionLinesservice.setActionType1("service");
	   actionLinesservice.setSeq(count);
	   actionBuilderService.save(actionLinesservice);
	   
//	   String[] outVar = {"label1","label2"};
	   String[] outVar = line.getRequest_param().split(",");
	   String[] outDataType = {"varchar","varchar"};
	   
	   for(int i=0;i<outVar.length;i++) 
	   {
		   Rn_cff_ActionBuilder_Lines actionParamOut=new Rn_cff_ActionBuilder_Lines();
		   actionParamOut.setRn_cff_actionBuilderHeader(actionbuilder);
		   actionParamOut.setVariableName(outVar[i]);
		   actionParamOut.setDataType(outDataType[i]);
		   actionParamOut.setActionType1("out_var");
		   actionParamOut.setSeq(count+1+i);
		   actionBuilderService.save(actionParamOut);
	   		}
	   
	   
		}
	}
	}
}
