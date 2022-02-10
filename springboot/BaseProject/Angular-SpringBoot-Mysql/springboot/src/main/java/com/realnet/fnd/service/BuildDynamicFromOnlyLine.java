package com.realnet.fnd.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import com.realnet.exceptions.ResourceNotFoundException;
import com.realnet.fnd.entity.Rn_Forms_Component_Setup;
import com.realnet.fnd.entity.Rn_Forms_Setup;
import com.realnet.fnd.repository.Rn_Forms_Setup_Repository;
import com.realnet.utils.WireFrameConstant;

@Service
public class BuildDynamicFromOnlyLine  {
	
	@Value("${angularProjectPath}")
	private String angularProjectPath;
	
	@Autowired
	private Rn_Forms_Setup_Repository rn_forms_setup_repository;
	
	
	
	
	public List<Rn_Forms_Setup> getAll() {
		// TODO Auto-generated method stub
		return rn_forms_setup_repository.findAll();
	}

	public Page<Rn_Forms_Setup> getAll(Pageable p) {
		// TODO Auto-generated method stub
		return rn_forms_setup_repository.findAll(p);
	}

	
	public Rn_Forms_Setup getById(int id) {
		// TODO Auto-generated method stub
		Rn_Forms_Setup rn_forms_setup = rn_forms_setup_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rn_Forms_Setup not found :: " + id));
		return rn_forms_setup;
	}
	
	//for line save
	
	public Rn_Forms_Setup save(Rn_Forms_Setup rn_forms_setup) {
		// TODO Auto-generated method stub
		Rn_Forms_Setup savedRn_Forms_Setup = rn_forms_setup_repository.save(rn_forms_setup);
		return savedRn_Forms_Setup;
	}

	
	public Rn_Forms_Setup updateById(int id, Rn_Forms_Setup rn_forms_setupRequest) {
		// TODO Auto-generated method stub
		Rn_Forms_Setup old_rn_forms_setup = rn_forms_setup_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rn_Forms_Setup not found :: " + id));
		old_rn_forms_setup.setButton_caption(rn_forms_setupRequest.getButton_caption());
		old_rn_forms_setup.setForm_desc(rn_forms_setupRequest.getForm_desc());
		old_rn_forms_setup.setForm_name(rn_forms_setupRequest.getForm_name());
		old_rn_forms_setup.setPage_event(rn_forms_setupRequest.getPage_event());
		old_rn_forms_setup.setRelated_to(rn_forms_setupRequest.getRelated_to());
		// line part
		old_rn_forms_setup.setComponents(rn_forms_setupRequest.getComponents());
		// updated by
		old_rn_forms_setup.setUpdatedBy(rn_forms_setupRequest.getUpdatedBy());
		final Rn_Forms_Setup updated_rn_forms_setup = rn_forms_setup_repository.save(old_rn_forms_setup);
		return updated_rn_forms_setup;
	}

	
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		Rn_Forms_Setup rn_forms_setup = rn_forms_setup_repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Rn_Forms_Setup not found :: " + id));
		rn_forms_setup_repository.delete(rn_forms_setup);
		return true;
	}

	
	public List<Rn_Forms_Setup> getByFormId(int form_id) {
		// TODO Auto-generated method stub
		return rn_forms_setup_repository.findByFormId(form_id);	}

	
	public void buildDynamicForm(int form_id) {
		// TODO Auto-generated method stub
		// HEADER
				Rn_Forms_Setup rn_forms_setup = rn_forms_setup_repository.findById(form_id)
						.orElseThrow(() -> new ResourceNotFoundException("Rn_Forms_Setup not found :: " + form_id));
		
		// LINE
				List<Rn_Forms_Component_Setup> components = rn_forms_setup.getComponents();		
				String form_name = rn_forms_setup.getForm_name();
				String form_name_upper = form_name.toUpperCase();
				String buttonCaption = rn_forms_setup.getButton_caption();
			
				StringBuilder dynamic_entry_form_html = new StringBuilder();
				
				StringBuilder dynamic_grid_view_form = new StringBuilder();
				StringBuilder dynamic_read_only_form = new StringBuilder();
				StringBuilder dynamic_edit_form = new StringBuilder();
				
				
//				// add
//				dynamic_entry_form_html.append("<div class=\"entry-pg pad-16\">\r\n" + 
//						"  <h4><b>ENTRY FORM</b></h4>\r\n" + 
//						"\r\n" +
//						"  <br />\r\n" + 
//						"  <h3>" + form_name_upper + "</h3>\r\n" + 
//						"  <section class=\"form-block\" style=\"margin-top:32px\">\n" +
//						"	<!-- entry form-->\r\n" + 
//						"      <form [formGroup]=\"entryForm\" (ngSubmit)=\"onSubmit()\">\r\n" + 
//						"          <table>\r\n");

				
				
//				//add
				dynamic_entry_form_html.append("<div class=\"entry-pg pad-16\">\r\n" + 
						"  <h4><b>ENTRY FORM</b></h4>\r\n"+
						"  <h3>" + form_name_upper + "</h3>\r\n" 
						+ "<section class=\"form-block\" style=\"margin-top:32px\">\r\n"
						+ "        <form [formGroup]=\"entryForm\" (ngSubmit)=\"onSubmit()\">\r\n"
//						+ "            <div class=\"clr-row\">\r\n"
//						+ "                <div class=\"clr-col-lg-12\">\r\n"
						+ "                    <table class=\"table\" style=\"width:100%;\" formArrayName=\"uiData\">"

						+" <tbody>\r\n"
						+ "                            <tr *ngFor=\"let item of controls; let i=index\" [formGroupName]=\"i\">\r\n"
						+ "                                <td class=\"left\">"
						
						);
				
//				// edit
//				dynamic_edit_form.append("<div class=\"read-only-pg pad-16\">\r\n" + 
//						"  <h4>EDIT FORM</h4>\r\n" + 
//						"  <br />\r\n" + 
//						"  <h3>" + form_name_upper + "</h3>\r\n" + 
//						"\r\n" + 
//						"  <section class=\"form-block\" style=\"margin-top:32px\">\r\n" + 
//						"      <form (ngSubmit)=\"onSubmit()\">\r\n" + 
//						"          <table>\n");
//				
//				// read-only
//				dynamic_read_only_form.append("<table class=\"s-header\">\n");
//				
				
				
				//2
				int loopCount = 0;
				for(Rn_Forms_Component_Setup component: components) {
					int i = ++loopCount;
					String label = component.getLabel();
					String type = component.getType();
					boolean mandatory = Boolean.parseBoolean(component.getMandatory());
					boolean readonly = Boolean.parseBoolean(component.getReadonly());
					//boolean b1=Boolean.parseBoolean(string);  
					String drop_value = component.getDrop_values();
					
					System.out.println("Label Name::" + label);
					// FOR MODIFICATION REFER : com.realnet.wfb.service: SpringMVCFieldTypeServiceImpl.java 
					if(WireFrameConstant.DT_TEXTFIELD.equalsIgnoreCase(type)) {
						// ENTRY FORM (.html)
						dynamic_entry_form_html.append(
								
								" <td class=\"left\">\r\n"
								
								+	"  <td style=\"width:125px;\">" + label + ": </td>\r\n" 
								+ " <input type=\"text\" [attr.id]=\"'label' + i\" formControlName=\"comp"
								+ i+"\""
								+ "placeholder=\"Enter Name\" style=\"width:180px\" class=\"clr-input\">\r\n"
//								+ "                                </td>"
								);
								
					
						
						}
					
					
//					// GRID VIEW FORM (.ts)
//					dynamic_grid_view_form.append("{prop:\"comp" + i + "\"  , name: \"" + label + "\"   , width:120 },\r\n");
//					
//					// READ-ONLY FORM
//					dynamic_read_only_form.append("<tr>\r\n" + 
//							"          <td>" + label + " </td>\r\n" + 
//							"          <td> {{ dynamicForm.comp" + i + " }} </td>\r\n" + 
//							"      </tr>\r\n");

					}//loop end
				
				
				//remove lines
				dynamic_entry_form_html.append("<td style=\"width:40px;\">\r\n"
						+ "                                    <a *ngIf=\"controls.length > 1\" (click)=\"onRemoveLines(i)\">\r\n"
						+ "                                        <clr-icon shape=\"trash\" class=\"is-error\"></clr-icon>\r\n"
						+ "                                    </a>\r\n"
						+ "                                </td>"
						
						
						);
				
//				Addlines
				dynamic_entry_form_html.append(
						"\n \r\n"
						+ "                            </tr>\r\n"
						+ "                        </tbody>\r\n"
						+ "                    </table>" 
						+ "\n  	 <button type=\"button\" class=\"btn btn-primary button1 \" (click)=\"onAddLines()\">\r\n"
						+ "		        <clr-icon shape=\"plus\"></clr-icon>\r\n"
						+ "						    </button><br>\r\n"
						//+ "						 <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"!entryForm.valid\">SUBMIT</button>\r\n"
						);
				

				
				//submit
				dynamic_entry_form_html.append(" <button type=\"submit\" class=\"btn btn-primary\" [disabled]=\"!entryForm.valid\">SUBMIT\r\n"
						+ "        \r\n"
						+ "    </button>"
						+ "\r\n"
						+ "      </form>\r\n"
						+ "  </section>\r\n"
						+ "</div>\r\n"
					
						
						);
				
				

//				// UPDATE
//				dynamic_edit_form.append(" </table>\r\n" + 
//						"          <br>\r\n" + 
//						"          <button type=\"submit\" form-control class=\"btn btn-primary\">UPDATE</button>\r\n" + 
//						"      </form>\r\n" + 
//						"  </section>\r\n" + 
//						"</div>\r\n");
//				
//				// READ-ONLY
//				dynamic_read_only_form.append("  </table>\r\n");
				
				FileWriter fw = null;
				BufferedWriter bw = null;
				
				try {
				
				// ENTRY FORM
				String ngDynamicEntryFormHtmlPath = angularProjectPath
						+ "/src/app/pages/dynamic-form/add/add-dynamic-form.component.html";
				File ngDynamicEntryFormHtmlFile = new File(ngDynamicEntryFormHtmlPath);
				if (!ngDynamicEntryFormHtmlFile.exists()) {
					ngDynamicEntryFormHtmlFile.createNewFile();
				}
				fw = new FileWriter(ngDynamicEntryFormHtmlFile.getAbsoluteFile());
				bw = new BufferedWriter(fw);
				bw.write(dynamic_entry_form_html.toString());
				bw.close();
				

				
				//				// UPDATE FORM
//				String ngDynamicEditFormHtmlPath = angularProjectPath
//						+ "/src/app/pages/dynamic-form/edit/edit-dynamic-form.component.html";
//				File ngDynamicEditFormHtmlFile = new File(ngDynamicEditFormHtmlPath);
//				if (!ngDynamicEditFormHtmlFile.exists()) {
//					ngDynamicEditFormHtmlFile.createNewFile();
//				}
//				fw = new FileWriter(ngDynamicEditFormHtmlFile.getAbsoluteFile());
//				bw = new BufferedWriter(fw);
//				bw.write(dynamic_edit_form.toString());
//				bw.close();
//
//				// GRID VIEW FORM
//				final String grid_form_start = "// DYNAMIC FORM GRID START";
//				final String grid_form_end = "// DYNAMIC FORM GRID END";
//				String grid_form_replaceWith = dynamic_grid_view_form.toString();
//				String ngDynamicGridFormTsPath = angularProjectPath
//						+ "/src/app/pages/dynamic-form/all/all-dynamic-form.component.ts";
//				File ngDynamicGridFormTsFile = new File(ngDynamicGridFormTsPath);
//				String grid_fileString = FileUtils.readFileToString(ngDynamicGridFormTsFile, StandardCharsets.UTF_8);
//				String grid_finalString = stringReplace(grid_fileString, grid_form_start, grid_form_end, grid_form_replaceWith);
//				
//				bw = new BufferedWriter(new FileWriter(ngDynamicGridFormTsFile, false)); // replaced string
//				bw.write(grid_finalString);
//				bw.close();
//				
//				// READ-ONLY FORM
//				final String read_only_form_start = "<!-- read only form start -->";
//				final String read_only_form_end = "<!-- read only form end -->";
//				String read_only_form_replaceWith = dynamic_read_only_form.toString();
//				String ngDynamicReadOnlyFormHtmlPath = angularProjectPath
//						+ "/src/app/pages/dynamic-form/read-only/read-only-dynamic-form.component.html";
//				File ngDynamicReadOnlyFormHtmlFile = new File(ngDynamicReadOnlyFormHtmlPath);
//				String read_only_fileString = FileUtils.readFileToString(ngDynamicReadOnlyFormHtmlFile, StandardCharsets.UTF_8);
//				String read_only_finalString = stringReplace(read_only_fileString, read_only_form_start, read_only_form_end, read_only_form_replaceWith);
//				
//				bw = new BufferedWriter(new FileWriter(ngDynamicReadOnlyFormHtmlFile, false)); // replaced string
//				bw.write(read_only_finalString);
//				bw.close();
//				
				}catch (IOException e) {
					e.printStackTrace();
					}
				
				
		
	}

	public String stringReplace(String str, String start, String end, String replaceWith) {
		// TODO Auto-generated method stub
		return null;
	}
	

}


