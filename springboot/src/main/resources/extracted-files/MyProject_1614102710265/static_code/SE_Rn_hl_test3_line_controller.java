"package com.realnet." + module_name + ".controller;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"" + "\r\n" + 
"import javax.validation.Valid;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.beans.factory.annotation.Autowired;" + "\r\n" + 
"import org.springframework.http.ResponseEntity;" + "\r\n" + 
"import org.springframework.web.bind.annotation.CrossOrigin;" + "\r\n" + 
"import org.springframework.web.bind.annotation.DeleteMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.GetMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PathVariable;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PostMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.PutMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestBody;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RequestMapping;" + "\r\n" + 
"import org.springframework.web.bind.annotation.RestController;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.exception.ResourceNotFoundException;" + "\r\n" + 
"import com.realnet.model.Rn_hl_test3_line_t;" + "\r\n" + 
"import com.realnet.repository.Rn_hl_test3_line_repository;" + "\r\n" + 
"import com.realnet.repository.Rn_hl_test3_repository;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class Rn_hl_test3_line_controller {" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private Rn_hl_test3_line_repository rn_hl_test3_line_repository;" + "\r\n" + 
"" + "\r\n" + 
"	@Autowired" + "\r\n" + 
"	private Rn_hl_test3_repository rn_hl_test3_repository;" + "\r\n" + 
"" + "\r\n" + 
"	// ==========GET ALL LINES BY HEADER ID=========" + "\r\n" + 
"	@GetMapping(\"/rn_hl_test3_header_t/{rn_hl_test3_header_t_id}/rn_hl_test3_line_t\")" + "\r\n" + 
"	public List<Rn_hl_test3_line_t> getAllRn_hl_test3_line_tByRn_hl_test3_header_t(" + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_header_t_id\") Integer rn_hl_test3_header_t_id) {" + "\r\n" + 
"		return rn_hl_test3_line_repository.findByRn_hl_test3_header_tId(rn_hl_test3_header_t_id);" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ==========GET LINE BY HEADER ID & LINE ID=========" + "\r\n" + 
"	@GetMapping(\"/rn_hl_test3_header_t/{rn_hl_test3_header_t_id}/rn_hl_test3_line_t/{rn_hl_test3_line_t_id}\")" + "\r\n" + 
"	public Rn_hl_test3_line_t getRn_hl_test3_line_tByRn_hl_test3_header_t_idAndRn_hl_test3_line_t_id(" + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_header_t_id\") Integer rn_hl_test3_header_t_id," + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_line_t_id\") Integer rn_hl_test3_line_t_id) {" + "\r\n" + 
"		if (!rn_hl_test3_repository.existsById(rn_hl_test3_header_t_id)) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"rn_hl_test3_header_t_id not found\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return rn_hl_test3_line_repository.findByHeaderIdAndLineId(rn_hl_test3_header_t_id, rn_hl_test3_line_t_id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"rn_hl_test3_line_t_id not found\"));" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ==========CREATE LINES=========" + "\r\n" + 
"	@PostMapping(\"/rn_hl_test3_header_t/{rn_hl_test3_header_t_id}/rn_hl_test3_line_t\")" + "\r\n" + 
"	public Rn_hl_test3_line_t createRn_hl_test3_line_t(" + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_header_t_id\") Integer rn_hl_test3_header_t_id," + "\r\n" + 
"			@Valid @RequestBody Rn_hl_test3_line_t rn_hl_test3_line_t) throws ResourceNotFoundException {" + "\r\n" + 
"		return rn_hl_test3_repository.findById(rn_hl_test3_header_t_id).map(rn_hl_test3_header_t -> {" + "\r\n" + 
"			rn_hl_test3_line_t.setRn_hl_test3_header_t(rn_hl_test3_header_t);" + "\r\n" + 
"			return rn_hl_test3_line_repository.save(rn_hl_test3_line_t);" + "\r\n" + 
"		}).orElseThrow(() -> new ResourceNotFoundException(\"rn_hl_test3_header_t not found\"));" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ==========UPDATE LINES=========" + "\r\n" + 
"	@PutMapping(\"/rn_hl_test3_header_t/{rn_hl_test3_header_t_id}/rn_hl_test3_line_t/{rn_hl_test3_line_t_id}\")" + "\r\n" + 
"	public Rn_hl_test3_line_t updateRn_hl_test3_line_t(" + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_header_t_id\") Integer rn_hl_test3_header_t_id," + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_line_t_id\") Integer rn_hl_test3_line_t_id," + "\r\n" + 
"			@Valid @RequestBody Rn_hl_test3_line_t rn_hl_test3_line_tRequest) throws ResourceNotFoundException {" + "\r\n" + 
"		if (!rn_hl_test3_repository.existsById(rn_hl_test3_header_t_id)) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"rn_hl_test3_header_t_id not found\");" + "\r\n" + 
"		}" + "\r\n" + 
"" + "\r\n" + 
"		return rn_hl_test3_line_repository.findById(rn_hl_test3_line_t_id).map(rn_hl_test3_line_t -> {" + "\r\n" + 
"			// ===============LOOP TO UPDATE GET SET DEVELOPEMENT================" + "\r\n" + 
"			rn_hl_test3_line_t.setL_textfield5(rn_hl_test3_line_tRequest.getL_textfield5());" + "\r\n" + 
"			rn_hl_test3_line_t.setL_textfield6(rn_hl_test3_line_tRequest.getL_textfield6());" + "\r\n" + 
"			rn_hl_test3_line_t.setL_textfield7(rn_hl_test3_line_tRequest.getL_textfield7());" + "\r\n" + 
"			rn_hl_test3_line_t.setL_textfield8(rn_hl_test3_line_tRequest.getL_textfield8());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute1(rn_hl_test3_line_tRequest.getAttribute1());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute2(rn_hl_test3_line_tRequest.getAttribute2());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute3(rn_hl_test3_line_tRequest.getAttribute3());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute4(rn_hl_test3_line_tRequest.getAttribute4());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute5(rn_hl_test3_line_tRequest.getAttribute5());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute6(rn_hl_test3_line_tRequest.getAttribute6());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute7(rn_hl_test3_line_tRequest.getAttribute7());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute8(rn_hl_test3_line_tRequest.getAttribute8());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute9(rn_hl_test3_line_tRequest.getAttribute9());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute10(rn_hl_test3_line_tRequest.getAttribute10());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute11(rn_hl_test3_line_tRequest.getAttribute11());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute12(rn_hl_test3_line_tRequest.getAttribute12());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute13(rn_hl_test3_line_tRequest.getAttribute13());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute14(rn_hl_test3_line_tRequest.getAttribute14());" + "\r\n" + 
"			rn_hl_test3_line_t.setAttribute15(rn_hl_test3_line_tRequest.getAttribute15());" + "\r\n" + 
"			rn_hl_test3_line_t.setFlex1(rn_hl_test3_line_tRequest.getFlex1());" + "\r\n" + 
"			rn_hl_test3_line_t.setFlex2(rn_hl_test3_line_tRequest.getFlex2());" + "\r\n" + 
"			rn_hl_test3_line_t.setFlex3(rn_hl_test3_line_tRequest.getFlex3());" + "\r\n" + 
"			rn_hl_test3_line_t.setFlex4(rn_hl_test3_line_tRequest.getFlex4());" + "\r\n" + 
"			rn_hl_test3_line_t.setFlex5(rn_hl_test3_line_tRequest.getFlex5());" + "\r\n" + 
"" + "\r\n" + 
"			return rn_hl_test3_line_repository.save(rn_hl_test3_line_t);" + "\r\n" + 
"		}).orElseThrow(() -> new ResourceNotFoundException(\"rn_hl_test3_line_t_id not found\"));" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	// ==========DELETE LINES=========" + "\r\n" + 
"	@DeleteMapping(\"/rn_hl_test3_header_t/{rn_hl_test3_header_t_id}/rn_hl_test3_line_t/{rn_hl_test3_line_t_id}\")" + "\r\n" + 
"	public ResponseEntity<?> deleteRn_hl_test3_line_t(" + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_header_t_id\") Integer rn_hl_test3_header_t_id," + "\r\n" + 
"			@PathVariable(value = \"rn_hl_test3_line_t_id\") Integer rn_hl_test3_line_t_id)" + "\r\n" + 
"			throws ResourceNotFoundException {" + "\r\n" + 
"		return rn_hl_test3_line_repository.findByHeaderIdAndLineId(rn_hl_test3_line_t_id, rn_hl_test3_header_t_id)" + "\r\n" + 
"				.map(rn_hl_test3_line_t -> {" + "\r\n" + 
"					rn_hl_test3_line_repository.delete(rn_hl_test3_line_t);" + "\r\n" + 
"					return ResponseEntity.ok()" + "\r\n" + 
"							.body(\"Record Deleted Successfully with Line Id :: \" + rn_hl_test3_line_t_id);" + "\r\n" + 
"				}).orElseThrow(() -> new ResourceNotFoundException(\"Rn_hl_test3_line_t not found with id = \"" + "\r\n" + 
"						+ rn_hl_test3_line_t_id + \" and rn_hl_test3_header_t_id = \" + rn_hl_test3_header_t_id));" + "\r\n" + 
"	}" + "\r\n" + 
"}" 
