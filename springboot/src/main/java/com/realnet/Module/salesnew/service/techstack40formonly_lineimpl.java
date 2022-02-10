package com.realnet.Module.salesnew.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realnet.wfb.entity.Rn_Fb_Header;
import com.realnet.wfb.entity.Rn_Fb_Line;
import com.realnet.wfb.service.Rn_WireFrame_Service;
@Service
public class techstack40formonly_lineimpl {

	@Autowired
	private Rn_WireFrame_Service wireFrameService;
	public String dynamicfields(int id, String filetype, String operationtype, String formtype) {
		Rn_Fb_Header rn_fb_header = wireFrameService.getById(id);

		List<Rn_Fb_Line> rn_fb_lines = rn_fb_header.getRn_fb_lines();
		StringBuilder FieldsCode = new StringBuilder();
		String fieldscodestr = null;

	if (filetype.equals("html") && operationtype.equals("addheader")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {
						FieldsCode.append(" <th class=\"left\" style=\"width:125px;\">" + rn_Fb_linefield.getFieldName()
								+ "</th>" + "\r\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("html") && operationtype.equals("addinput")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\"  type=\"text\"   formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"enter \" />" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "                      <textarea  rows=\"3\" cols=\"40\" 	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\""
								+ "formControlName=\"" + rn_Fb_linefield.getFieldName() + "\"  placeholder=\"Enter "
								+ rn_Fb_linefield.getFieldName() + "\" ></textarea>" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\"  type=\"url\"   formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"https://www.facebook.com\" />" + "\r\n" + "      </td>"
								+ "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\"  type=\"email\"  formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "            <select      class=\"clr-dropdown\" formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" >\r\n"
								+ "                <option  value=\"null\">Choose ...</option>\r\n"
								+ "				   <option *ngFor=\"let val of dropdownval\" value={{val}}>{{val}}</option>"
								+ "              </select>" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\"  type=\"checkbox\" clrCheckbox  formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\"  type=\"checkbox\" clrToggle formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\" type=\"date\" formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\" type=\"text\" list=\"datalistauto\" formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" +

								" <datalist  id=\"datalistauto\">\r\n"
								+ "     <option *ngFor=\"let item of autocomlist\" value={{item}}></option>\r\n"
								+ "     </datalist>" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "                     <span style='font-size:25px;'>&#8377;</span>"
								+ "        <input colspan=\"2\" type=\"number\" formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\" type=\"tel\" mask=\"(000) 000-0000\"  formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" + "      </td>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {
						FieldsCode.append("     <td class=\"left\">" + "\r\n" + "" + "\r\n"
								+ "        <input colspan=\"2\" type=\"password\" formControlName=\""
								+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\"" + "\r\n"
								+ "    	  placeholder=\"Enter name\" />" + "\r\n" + "      </td>" + "\r\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("html") && operationtype.equals("update")) {

				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "                  \r\n"
										+ "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"text\""
										+ "name=\"" + rn_Fb_linefield.getFieldName() + "\" [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\" placeholder=\"Enter "
										+ rn_Fb_linefield.getFieldName() + "\" >" + "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "                  \r\n"
										+ "                      <textarea   rows=\"3\" cols=\"40\"	 style=\"width:fit-content;\" 	 type=\"text\""
										+ "name=\"" + rn_Fb_linefield.getFieldName() + "\" [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\" placeholder=\"Enter "
										+ rn_Fb_linefield.getFieldName() + "\" >" + "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "                  \r\n"
										+ "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"url\""
										+ "name=\"" + rn_Fb_linefield.getFieldName() + "\" [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\" placeholder=\"Enter "
										+ rn_Fb_linefield.getFieldName() + "\" >" + "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "                  \r\n"
										+ "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\"  type=\"email\""
										+ "name=\"" + rn_Fb_linefield.getFieldName() + "\" [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\" placeholder=\"Enter "
										+ rn_Fb_linefield.getFieldName() + "\" >" + "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "            <select   [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\"   class=\"clr-dropdown\"  >\r\n"
										+ "                <option value=\"null\">Choose ...</option>\r\n"
										+ "                <option value=\"medicine\">medicine</option>\r\n"
										+ "                <option value=\"herbals\">herbals</option>\r\n"
										+ "                <option value=\"beauty\">beauty</option>\r\n"
										+ "              </select>" + "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                    <label > " + rn_Fb_linefield.getFieldName()
										+ ":</label>\r\n"
										+ "                  <input type=\"checkbox\" clrCheckbox  [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\" \r\n"
										+ "                   value=\"true \"  />\r\n" + "            </div>");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                    <label > " + rn_Fb_linefield.getFieldName()
										+ ":</label>\r\n"
										+ "                  <input type=\"checkbox\" clrToggle  [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\" \r\n"
										+ "                   value=\"true \"  />\r\n" + "            </div>");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                <label for=\"doj\">" + rn_Fb_linefield.getFieldName()
										+ ":</label>\r\n"
										+ "                <input id=\"doj\"  type=\"date\" class=\"clr-input\"   [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\" >\r\n" + "            </div>");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {
						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "                  \r\n"
										+ "                      <input class=\"clr-input\"  	 style=\"width:fit-content;\" 	colspan=\"2\" type=\"text\" autocomplete=\"on\"  "
										+ "	[(ngModel)]=\"student." + rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\"  placeholder=\"Enter "
										+ rn_Fb_linefield.getFieldName() + "\" >" + "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {
						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "                  \r\n"
										+ "                     <span style='font-size:25px;'>&#8377;</span> <input type=\"number\" class=\"clr-input\" \r\n"
										+ "        		 placeholder=\"Enter  currency\" 	[(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\" style=\"width: fit-content;\"/>"
										+ "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {
						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n" + "                  \r\n"
										+ " <input type=\"tel\" mask=\"(000) 000-0000\" style=\"width: fit-content;\" class=\"clr-input\" [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\">" + "</div>\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {
						FieldsCode
								.append("<div class=\"clr-col-md-4 clr-col-sm-12\" style=\"margin-bottom: 20px;\">\r\n"
										+ "                  <label>" + rn_Fb_linefield.getFieldName()
										+ ": </label>\r\n"
										+ " <input type=\"password\" placeholder=\"Password\" style=\"width: fit-content;\" [(ngModel)]=\"student."
										+ rn_Fb_linefield.getFieldName() + "\"  name=\""
										+ rn_Fb_linefield.getFieldName() + "\" class=\"clr-input\">" + "</div>\r\n");
					}

				}

				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("html") && operationtype.equals("readonly")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {

						FieldsCode.append("        <tr>" + "\r\n" + "            <td style=\"width:125px\">"
								+ rn_Fb_linefield.getFieldName() + "</td>" + "\r\n" + "            <td> {{department."
								+ rn_Fb_linefield.getFieldName() + "}}</td>" + "\r\n" + "        </tr>" + "\r\n");
					}
				}

				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("java") && operationtype.equals("model")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode.append("    private Date  " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {

						FieldsCode.append("    private String " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {

						FieldsCode.append("    private long  " + rn_Fb_linefield.getFieldName() + ";" + "\r\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("java") && operationtype.equals("modelgettersetter")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode.append("	public Date get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(Date "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {

						FieldsCode.append("	public String get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(String "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {

						FieldsCode.append("	public long get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "() {" + "\r\n" + "		return "
								+ rn_Fb_linefield.getFieldName() + ";" + "\r\n" + "	}" + "\r\n" + "" + "\r\n"
								+ "	public void set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "(long "
								+ rn_Fb_linefield.getFieldName() + ") {" + "\r\n" + "		this."
								+ rn_Fb_linefield.getFieldName() + " = " + rn_Fb_linefield.getFieldName() + ";" + "\r\n"
								+ "	}" + "\r\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("java") && operationtype.equals("update")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {

						FieldsCode.append("dep.set" + rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "" + "(dept.get"
								+ rn_Fb_linefield.getFieldName().substring(0, 1).toUpperCase()
								+ rn_Fb_linefield.getFieldName().substring(1) + "());" + "\r\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("ts") && operationtype.equals("add")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {

						FieldsCode.append("    " + rn_Fb_linefield.getFieldName() + ":[null] ," + "\r\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("ts") && operationtype.equals("all")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {

						FieldsCode.append("      { prop: \"" + rn_Fb_linefield.getFieldName() + "\", name: \""
								+ rn_Fb_linefield.getFieldName() + "\", width: 150 }," + "\r\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();

			} else if (filetype.equals("ts") && operationtype.equals("model")) {
				for (Rn_Fb_Line rn_Fb_linefield : rn_fb_lines) {
					if (rn_Fb_linefield.getType_field() == null) {
						continue;
					}

					if (rn_Fb_linefield.getType_field().equals("textfield")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("textarea")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("url")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("email")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("dropdown")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("checkbox")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("togglebutton")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("datetime")) {

						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("autocomplete")) {
						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("currency_field")) {
						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("contact_field")) {
						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

					if (rn_Fb_linefield.getType_field().equals("masked")) {
						FieldsCode.append("" + rn_Fb_linefield.getFieldName() + ";\n");
					}

				}
				return fieldscodestr = FieldsCode.toString();
			}


		return fieldscodestr;
	}
}