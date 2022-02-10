package com.realnet.rb.Controller;


import java.io.BufferedWriter;	
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.comm.entity.*;
import com.realnet.actionbuilder.service.*;
import com.realnet.bi.service.*;
import com.realnet.codeextractor.service.*;
import com.realnet.comm.service.*;
import com.realnet.flf.service.*;
import com.realnet.fnd.service.*;
import com.realnet.qb.service.*;
import com.realnet.rb.service.*;
import com.realnet.users.service.*;
import com.realnet.wfb.service.*;
import com.realnet.rb.entity.Rn_report_builder;
import com.realnet.rb.response.Rn_tableResponse;
import com.realnet.rb.service.reportbuilder2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = { "Rn_report_builder" })
public class Rn_reportbuilder2controller {
	
	@Value("${projectPath}")
	private String projectPath;
	
	@Value("${angularProjectPath}")
	private String angularprojectpath;
	
	@Autowired
	private reportbuilder2 serv;
	
	private Rn_Cff_ActionBuilder_Service  aser;
	@ApiOperation(value = "Add A Tables", response = Rn_tableResponse.class)
	@PostMapping("/add-report")
	public ResponseEntity<?> reportgetall(@RequestBody Rn_report_builder reportdata) throws IOException{
	StringBuilder report_file=new StringBuilder();
	
	String service_name = reportdata.getServicename();
	
    String report_name = reportdata.getReport_name();
    
	System.err.println(service_name);
	
	if (service_name==null) {
		throw new NullPointerException("service name is null please insert");
	}
	
	String api_name=report_name.concat("api");
	
	
	
	Object getall = (Object)serv.getall();
	
	String filestring=report_name+"reportcontroller.java";
	String controllername=report_name+"reportcontroller" ;
	
	String pathstring=projectPath+"/src/main/java/com/realnet/rb/Controller/"+filestring;
	

	StringBuilder htmlfile=new StringBuilder();
	
	StringBuilder scssfile=new StringBuilder();
	
	StringBuilder tsfile=new StringBuilder();
	
	
	
	System.err.println(pathstring);
	File servicefile=new File(pathstring);
	FileWriter fw = null;
	BufferedWriter bw = null;
	
	
	
	report_file.append("package com.realnet.rb.Controller;\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "import java.util.List;\r\n"
			+ "\r\n"
			+ "import org.springframework.beans.factory.annotation.Autowired;\r\n"
			+ "import org.springframework.http.MediaType;\r\n"
			+ "import org.springframework.http.ResponseEntity;\r\n"
			+ "import org.springframework.web.bind.annotation.GetMapping;\r\n"
			+ "\r\n"
			+ "import org.springframework.web.bind.annotation.RequestMapping;\r\n"
			+ "\r\n"
			+ "import org.springframework.web.bind.annotation.RestController;\r\n"
			+ "\r\n"
			+ "import com.realnet.comm.entity.*;\r\n"
			+ "import com.realnet.actionbuilder.service.*;\r\n"
			+ "import com.realnet.bi.service.*;\r\n"
			+ "import com.realnet.codeextractor.service.*;\r\n"
			+ "import com.realnet.comm.service.*;\r\n"
			+ "import com.realnet.flf.service.*;\r\n"
			+ "import com.realnet.fnd.service.*;\r\n"
			+ "import com.realnet.qb.service.*;\r\n"
			+ "import com.realnet.rb.service.*;\r\n"
			+ "import com.realnet.users.service.*;\r\n"
			+ "import com.realnet.wfb.service.*;\r\n"
			+ "\r\n"
			+ "import com.realnet.rb.response.Rn_tableResponse;\r\n"
			+ "import com.realnet.rb.service.reportbuilder2;\r\n"
			+ "\r\n"
			+ "import io.swagger.annotations.Api;\r\n"
			+ "import io.swagger.annotations.ApiOperation;\r\n"
			+ "\r\n"
			+ "@RestController\r\n"
			+ "@RequestMapping(value = \"/api\", produces = MediaType.APPLICATION_JSON_VALUE)\r\n"
			+ "@Api(tags = { \"Rn_report_builder\" })\r\n"
			+ "public class "+controllername+" {\r\n"
			+ "	\r\n"
			+ "	\r\n"
			+ "	@Autowired\r\n"
			+ "	private "+service_name+"  aser;\r\n"
			+"\r\n"
			+"\r\n"
			+ "	@ApiOperation(value = \"Add A Tables\", response = Rn_tableResponse.class)\r\n"
			+ "	@GetMapping(\"/"+api_name+"\")\r\n"
			+ "	public ResponseEntity<?> reportgetall(){\r\n"
			+ "	Object getall = (Object)aser.getAll();		\r\n"
			+ "		\r\n"
			+ "		return ResponseEntity.ok(getall);\r\n"
			+ "	}\r\n"
			+ "	\r\n"
			+ "\r\n"
			+ "}\r\n"
			+ "");
	
			
	htmlfile.append("\r\n"
			+ "    <div style=\"padding: 2px 2px; font-size: 15px; border-radius: 10px;\"  >\r\n"
			+ "        <button type=\"button\" (click)=\"runreport('"+api_name+"')\" class=\"btn btn-primary\">Run Report</button>\r\n"
			+ "</div>\r\n"
			+ "<div class=\"section\">\r\n"
			+ "        <p> Query Result </p>\r\n"
			+ "  </div>\r\n"
			+ "\r\n"
			+ "  <div class=\"pad-16\">\r\n"
			+ "\r\n"
			+ "    <ngx-datatable  class=\"data\"\r\n"
			+ "        style='width:1200px;'\r\n"
			+ "        [rows]=\"rows\"\r\n"
			+ "        [columns]=\"columns\">\r\n"
			+ "    </ngx-datatable>\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "</div>");
	
	
	scssfile.append("$bg-color: #dddddd;\r\n"
			+ "\r\n"
			+ ".button1::after {\r\n"
			+ "  content: none;\r\n"
			+ "}\r\n"
			+ ".button1:hover::after {\r\n"
			+ "  content: \"ADD ROWS\";\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".section {\r\n"
			+ "  background-color: $bg-color;\r\n"
			+ "  height: 40px;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".section p {\r\n"
			+ "  //color: white;\r\n"
			+ "  padding: 10px;\r\n"
			+ "  font-size: 18px;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".clr-input {\r\n"
			+ "  color: #212529;\r\n"
			+ "  border: 1px solid #ced4da;\r\n"
			+ "  border-radius: 0.25rem;\r\n"
			+ "  padding: 0.75rem 0.75rem;\r\n"
			+ "  margin-top: 3px;\r\n"
			+ "  width: 100%;\r\n"
			+ "  margin-bottom: 10px;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".center {\r\n"
			+ "  text-align: center;\r\n"
			+ "}\r\n"
			+ "\r\n"
			+ ".pad-16 .data{\r\n"
			+ "  overflow: scroll;\r\n"
			+ "}");
	
	tsfile.append("import { Component, OnInit } from '@angular/core';\r\n"
			+ "import { ActivatedRoute, Router } from '@angular/router';\r\n"
			+ "import { FormBuilder, FormGroup, FormControl } from '@angular/forms';\r\n"
			+ "import { ValidationError } from 'src/app/models/ValidationError';\r\n"
			+ "import { QueryRunnerService } from 'src/app/services/api/query-runner.service';\r\n"
			+ "import { ReportBuilderService } from 'src/app/services/api/report-builder.service';\r\n"
			+ "\r\n"
			+ "@Component({\r\n"
			+ "  selector: '"+report_name+"',\r\n"
			+ "  templateUrl: './"+report_name+".component.html',\r\n"
			+ "  styleUrls: ['./"+report_name+".component.scss']\r\n"
			+ "})\r\n"
			+ "export class "+report_name+" implements OnInit {\r\n"
			+ "\r\n"
			+ "  public entryForm: FormGroup;\r\n"
			+ "  submitted = false;\r\n"
			+ "  basic: boolean = false;\r\n"
			+ "  fieldErrors: ValidationError[] = [];\r\n"
			+ "  report_id: number;\r\n"
			+ "  columns: any[];\r\n"
			+ "  rows:any[];\r\n"
			+ "  \r\n"
			+ "  constructor(private _fb: FormBuilder,\r\n"
			+ "    private router: Router,\r\n"
			+ "    private route: ActivatedRoute,\r\n"
			+ "    private queryRunnerService: QueryRunnerService,\r\n"
			+ "    private reportBuilderService: ReportBuilderService) { }\r\n"
			+ "    \r\n"
			+ "  ngOnInit(): void {\r\n"
			+ "    this.route.queryParams.subscribe(params => {\r\n"
			+ "      this.report_id = +params['report_id'];\r\n"
			+ "    });\r\n"
			+ "    console.log(\"Report id:\",this.report_id);\r\n"
			+ " }\r\n"
			+ "\r\n"
			+ "        runreport(apiname)\r\n"
			+ "        {\r\n"
			+ "          console.log(apiname);\r\n"
			+ "\r\n"
			+ "          this.reportBuilderService.getreportdata(apiname).subscribe((data)=>{\r\n"
			+ "            console.log(\"child report data\",data);\r\n"
			+ "            this.rows=data\r\n"
			+ "            var j;\r\n"
			+ "            var cart = [];\r\n"
			+ "          for(var i = 0; i < data.length; i++) \r\n"
			+ "          { \r\n"
			+ "            var columnsIn = data[i]; \r\n"
			+ "            if(i==1)\r\n"
			+ "            {\r\n"
			+ "                for(var key in columnsIn)\r\n"
			+ "                { \r\n"
			+ "                j={prop:key , name: key};\r\n"
			+ "                cart.push(j)\r\n"
			+ "                  \r\n"
			+ "                } \r\n"
			+ "            }\r\n"
			+ "          }\r\n"
			+ "          this.columns = cart;\r\n"
			+ "\r\n"
			+ "          })\r\n"
			+ "\r\n"
			+ "        }\r\n"
			+ "\r\n"
			+ "}");
	
	// service file  create code
	try {
		
	
	if (!servicefile.exists()) {
		servicefile.createNewFile();
	}
	fw = new FileWriter(servicefile.getAbsoluteFile());
	bw = new BufferedWriter(fw);
	bw.write(report_file.toString());
	bw.close();
	
	
	//create component folder
	File file = new File(projectPath + "/webui/src/app/pages/"+report_name+"/");
	System.out.println("Ganesh File Path = " + file.getAbsolutePath());
	File dir = new File(file.getParent());
	if(!file.exists()) {
		file.mkdirs();
	}
	
	
	//create html file
	File file3 = new File(projectPath + "/webui/src/app/pages/"+report_name+"/"+report_name+".component.html");
	File dir3 = new File(file.getParent());
	if(!dir3.exists()) {
		dir3.mkdirs();
	}
	if (!file3.exists()) {
		file3.createNewFile();
	}
	
	fw = new FileWriter(file3.getAbsoluteFile());
	bw = new BufferedWriter(fw);
	bw.write(htmlfile.toString());
	bw.close();
	
	//create css file
	File file4 = new File(projectPath + "/webui/src/app/pages/"+report_name+"/"+report_name+".component.scss");
	File dir4 = new File(file.getParent());
	if(!dir4.exists()) {
		dir4.mkdirs();
	}
	if (!file4.exists()) {
		file4.createNewFile();
	}

	fw = new FileWriter(file4.getAbsoluteFile());
	bw = new BufferedWriter(fw);
	bw.write(scssfile.toString());
	bw.close();
	
	// ts file create code
	//create ts file
	File file2 = new File(projectPath + "/webui/src/app/pages/"+report_name+"/"+report_name+".component.ts");
	File dir2 = new File(file.getParent());
	if(!dir2.exists()) {
		dir2.mkdirs();
	}
	if (!file2.exists()) {
		file2.createNewFile();
	}

	fw = new FileWriter(file2.getAbsoluteFile());
	bw = new BufferedWriter(fw);
	bw.write(tsfile.toString());
	bw.close();
	
	
	
	//update the rounting file
	System.out.println("In an static files loop");
	File fileTest = new File(projectPath + "/webui/src/app/app-routing.module.ts");
	Path path = Paths.get(projectPath + "/webui/src/app/app-routing.module.ts");
	StringBuilder code = new StringBuilder();
	List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    String importstr="import { "+report_name+" } from './pages/"+report_name+"/"+report_name+".component';\n//add_import";
	String routestr = "{ path: '"+report_name+"'   , component:  "+report_name+"},\n//add_routingreport";
	
	
	for (String line2 : lines)
	{
		if (line2.startsWith("//add_import")  )
		{
		    line2="import { "+report_name+" } from './pages/"+report_name+"/"+report_name+".component';\n//add_import";
		 }
		
		if (line2.startsWith("////add_routingreportentity"))
		{
			line2 = "{ path: '"+report_name+"'   , component:  "+report_name+"},\n //add_routingreportentity";
		}
		
		code.append(line2 + "\n");
	}
	//System.out.println(code);
	bw = new BufferedWriter(new FileWriter(fileTest)); // replaced string
	bw.write(code.toString());
	bw.close();
    //update rout
//    Path path1 = Paths.get(projectPath + "/webui/src/app/app-routing.module.ts");
	
	//update the module file
	File fileModule = new File(projectPath + "/webui/src/app/app.module.ts");
	Path pathModule = Paths.get(projectPath + "/webui/src/app/app.module.ts");
	StringBuilder codeModule = new StringBuilder();
	List<String> linesModule = Files.readAllLines(pathModule, StandardCharsets.UTF_8);
	for (String line3 : linesModule) 
	{
		if (line3.startsWith("//add_module_import")) 
		{
		    line3="import { "+report_name+" } from './pages/"+report_name+"/"+report_name+".component';\n//add_module_import";
		 }
		
		if (line3.startsWith("//add_module")) 
		{
			line3 = ""+report_name+",\n//add_module";
		}
		codeModule.append(line3 + "\n");
	}
	bw = new BufferedWriter(new FileWriter(fileModule)); // replaced string
	bw.write(codeModule.toString());
	bw.close();
	
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	return ResponseEntity.status(HttpStatus.OK).body("controller created");
	}
	

}
