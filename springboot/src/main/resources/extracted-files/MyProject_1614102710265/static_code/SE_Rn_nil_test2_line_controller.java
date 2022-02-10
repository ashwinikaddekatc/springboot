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
"import com.realnet.model.Rn_nil_test2_line_t;" + "\r\n" + 
"import com.realnet.repository.Rn_nil_test2_line_repository;" + "\r\n" + 
"import com.realnet.repository.Rn_nil_test2_repository;" + "\r\n" + 
"" + "\r\n" + 
"@RestController" + "\r\n" + 
"@CrossOrigin(origins = \"http://localhost:4200\")" + "\r\n" + 
"@RequestMapping(\"/api\")" + "\r\n" + 
"public class	Rn_nil_test2_line_controller {" + "\r\n" + 
"" + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    private	Rn_nil_test2_line_repository rn_nil_test2_line_repository;" + "\r\n" + 
"" + "\r\n" + 
"    @Autowired" + "\r\n" + 
"    private	Rn_nil_test2_repository rn_nil_test2_repository;" + "\r\n" + 
"" + "\r\n" + 
"	 // ==========GET ALL LINES BY HEADER ID=========" + "\r\n" + 
"    @GetMapping(\"/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t\")" + "\r\n" + 
"    public List <Rn_nil_test2_line_t> getAllRn_nil_test2_line_tByRn_nil_test2_header_t(@PathVariable(value = \"rn_nil_test2_header_t_id\") Integer	rn_nil_test2_header_t_id) {" + "\r\n" + 
"        return	rn_nil_test2_line_repository.findByRn_nil_test2_header_tId(rn_nil_test2_header_t_id);" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"	 // ==========GET LINE BY HEADER ID & LINE ID=========" + "\r\n" + 
"	@GetMapping(\"/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t/{rn_nil_test2_line_t_id}\")" + "\r\n" + 
"	public	Rn_nil_test2_line_t getRn_nil_test2_line_tByRn_nil_test2_header_t_idAndRn_nil_test2_line_t_id(" + "\r\n" + 
"			@PathVariable(value = \"rn_nil_test2_header_t_id\") Integer rn_nil_test2_header_t_id," + "\r\n" + 
"			@PathVariable(value = \"rn_nil_test2_line_t_id\") Integer rn_nil_test2_line_t_id) {" + "\r\n" + 
"		if (!rn_nil_test2_repository.existsById(rn_nil_test2_header_t_id)) {" + "\r\n" + 
"			throw new ResourceNotFoundException(\"rn_nil_test2_header_t_id not found\");" + "\r\n" + 
"		}" + "\r\n" + 
"		return rn_nil_test2_line_repository.findByHeaderIdAndLineId(rn_nil_test2_header_t_id, rn_nil_test2_line_t_id)" + "\r\n" + 
"				.orElseThrow(() -> new ResourceNotFoundException(\"rn_nil_test2_line_t_id not found\"));" + "\r\n" + 
"	}" + "\r\n" + 
"" + "\r\n" + 
"	 // ==========CREATE LINES=========" + "\r\n" + 
"    @PostMapping(\"/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t\")" + "\r\n" + 
"    public	Rn_nil_test2_line_t createRn_nil_test2_line_t(@PathVariable(value = \"rn_nil_test2_header_t_id\") Integer	rn_nil_test2_header_t_id," + "\r\n" + 
"        @Valid @RequestBody	Rn_nil_test2_line_t rn_nil_test2_line_t) throws ResourceNotFoundException {" + "\r\n" + 
"        return	rn_nil_test2_repository.findById(rn_nil_test2_header_t_id).map(rn_nil_test2_header_t -> {" + "\r\n" + 
"            rn_nil_test2_line_t.setRn_nil_test2_header_t(rn_nil_test2_header_t);" + "\r\n" + 
"            return	rn_nil_test2_line_repository.save(rn_nil_test2_line_t);" + "\r\n" + 
"        }).orElseThrow(() -> new ResourceNotFoundException(\"rn_nil_test2_header_t not found\"));" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"	 // ==========UPDATE LINES=========" + "\r\n" + 
"    @PutMapping(\"/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t/{rn_nil_test2_line_t_id}\")" + "\r\n" + 
"    public	Rn_nil_test2_line_t	updateRn_nil_test2_line_t(@PathVariable(value = \"rn_nil_test2_header_t_id\") Integer	rn_nil_test2_header_t_id," + "\r\n" + 
"        @PathVariable(value = \"rn_nil_test2_line_t_id\") Integer	rn_nil_test2_line_t_id, @Valid @RequestBody	Rn_nil_test2_line_t rn_nil_test2_line_tRequest)" + "\r\n" + 
"    throws ResourceNotFoundException {" + "\r\n" + 
"        if (!rn_nil_test2_repository.existsById(rn_nil_test2_header_t_id)) {" + "\r\n" + 
"            throw new ResourceNotFoundException(\"rn_nil_test2_header_t_id not found\");" + "\r\n" + 
"        }" + "\r\n" + 
"" + "\r\n" + 
"        return	rn_nil_test2_line_repository.findById(rn_nil_test2_line_t_id).map(rn_nil_test2_line_t -> {" + "\r\n" + 
"		//===============LOOP TO UPDATE GET SET DEVELOPEMENT================" + "\r\n" + 
"		rn_nil_test2_line_t.setL_textfield5(rn_nil_test2_line_tRequest.getL_textfield5());" + "\r\n" + 
"rn_nil_test2_line_t.setL_textfield6(rn_nil_test2_line_tRequest.getL_textfield6());" + "\r\n" + 
"rn_nil_test2_line_t.setL_textfield7(rn_nil_test2_line_tRequest.getL_textfield7());" + "\r\n" + 
"rn_nil_test2_line_t.setL_textfield8(rn_nil_test2_line_tRequest.getL_textfield8());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute1(rn_nil_test2_line_tRequest.getAttribute1());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute2(rn_nil_test2_line_tRequest.getAttribute2());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute3(rn_nil_test2_line_tRequest.getAttribute3());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute4(rn_nil_test2_line_tRequest.getAttribute4());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute5(rn_nil_test2_line_tRequest.getAttribute5());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute6(rn_nil_test2_line_tRequest.getAttribute6());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute7(rn_nil_test2_line_tRequest.getAttribute7());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute8(rn_nil_test2_line_tRequest.getAttribute8());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute9(rn_nil_test2_line_tRequest.getAttribute9());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute10(rn_nil_test2_line_tRequest.getAttribute10());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute11(rn_nil_test2_line_tRequest.getAttribute11());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute12(rn_nil_test2_line_tRequest.getAttribute12());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute13(rn_nil_test2_line_tRequest.getAttribute13());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute14(rn_nil_test2_line_tRequest.getAttribute14());" + "\r\n" + 
"rn_nil_test2_line_t.setAttribute15(rn_nil_test2_line_tRequest.getAttribute15());" + "\r\n" + 
"rn_nil_test2_line_t.setFlex1(rn_nil_test2_line_tRequest.getFlex1());" + "\r\n" + 
"rn_nil_test2_line_t.setFlex2(rn_nil_test2_line_tRequest.getFlex2());" + "\r\n" + 
"rn_nil_test2_line_t.setFlex3(rn_nil_test2_line_tRequest.getFlex3());" + "\r\n" + 
"rn_nil_test2_line_t.setFlex4(rn_nil_test2_line_tRequest.getFlex4());" + "\r\n" + 
"rn_nil_test2_line_t.setFlex5(rn_nil_test2_line_tRequest.getFlex5());" + "\r\n" + 
"" + "\r\n" + 
"            return	rn_nil_test2_line_repository.save(rn_nil_test2_line_t);" + "\r\n" + 
"        }).orElseThrow(() -> new ResourceNotFoundException(\"rn_nil_test2_line_t_id not found\"));" + "\r\n" + 
"    }" + "\r\n" + 
"" + "\r\n" + 
"	 // ==========DELETE LINES=========" + "\r\n" + 
"    @DeleteMapping(\"/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t/{rn_nil_test2_line_t_id}\")" + "\r\n" + 
"    public ResponseEntity<?> deleteRn_nil_test2_line_t(@PathVariable(value = \"rn_nil_test2_header_t_id\") Integer	rn_nil_test2_header_t_id," + "\r\n" + 
"        @PathVariable(value = \"rn_nil_test2_line_t_id\") Integer	rn_nil_test2_line_t_id) throws ResourceNotFoundException {" + "\r\n" + 
"        return	rn_nil_test2_line_repository.findByHeaderIdAndLineId(rn_nil_test2_line_t_id, rn_nil_test2_header_t_id).map(rn_nil_test2_line_t -> {" + "\r\n" + 
"            rn_nil_test2_line_repository.delete(rn_nil_test2_line_t);" + "\r\n" + 
"            return ResponseEntity.ok().body(\"Record Deleted Successfully with Line Id :: \" + rn_nil_test2_line_t_id);" + "\r\n" + 
"        }).orElseThrow(() -> new ResourceNotFoundException(" + "\r\n" + 
"            \"Rn_nil_test2_line_t not found with id = \" + rn_nil_test2_line_t_id + \" and rn_nil_test2_header_t_id = \" + rn_nil_test2_header_t_id));" + "\r\n" + 
"    }" + "\r\n" + 
"}" 
