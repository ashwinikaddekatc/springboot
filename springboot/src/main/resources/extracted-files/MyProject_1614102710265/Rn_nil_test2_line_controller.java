package com.realnet.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.exception.ResourceNotFoundException;
import com.realnet.model.Rn_nil_test2_line_t;
import com.realnet.repository.Rn_nil_test2_line_repository;
import com.realnet.repository.Rn_nil_test2_repository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class	Rn_nil_test2_line_controller {

    @Autowired
    private	Rn_nil_test2_line_repository rn_nil_test2_line_repository;

    @Autowired
    private	Rn_nil_test2_repository rn_nil_test2_repository;

	 // ==========GET ALL LINES BY HEADER ID=========
    @GetMapping("/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t")
    public List <Rn_nil_test2_line_t> getAllRn_nil_test2_line_tByRn_nil_test2_header_t(@PathVariable(value = "rn_nil_test2_header_t_id") Integer	rn_nil_test2_header_t_id) {
        return	rn_nil_test2_line_repository.findByRn_nil_test2_header_tId(rn_nil_test2_header_t_id);
    }

	 // ==========GET LINE BY HEADER ID & LINE ID=========
	@GetMapping("/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t/{rn_nil_test2_line_t_id}")
	public	Rn_nil_test2_line_t getRn_nil_test2_line_tByRn_nil_test2_header_t_idAndRn_nil_test2_line_t_id(
			@PathVariable(value = "rn_nil_test2_header_t_id") Integer rn_nil_test2_header_t_id,
			@PathVariable(value = "rn_nil_test2_line_t_id") Integer rn_nil_test2_line_t_id) {
		if (!rn_nil_test2_repository.existsById(rn_nil_test2_header_t_id)) {
			throw new ResourceNotFoundException("rn_nil_test2_header_t_id not found");
		}
		return rn_nil_test2_line_repository.findByHeaderIdAndLineId(rn_nil_test2_header_t_id, rn_nil_test2_line_t_id)
				.orElseThrow(() -> new ResourceNotFoundException("rn_nil_test2_line_t_id not found"));
	}

	 // ==========CREATE LINES=========
    @PostMapping("/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t")
    public	Rn_nil_test2_line_t createRn_nil_test2_line_t(@PathVariable(value = "rn_nil_test2_header_t_id") Integer	rn_nil_test2_header_t_id,
        @Valid @RequestBody	Rn_nil_test2_line_t rn_nil_test2_line_t) throws ResourceNotFoundException {
        return	rn_nil_test2_repository.findById(rn_nil_test2_header_t_id).map(rn_nil_test2_header_t -> {
            rn_nil_test2_line_t.setRn_nil_test2_header_t(rn_nil_test2_header_t);
            return	rn_nil_test2_line_repository.save(rn_nil_test2_line_t);
        }).orElseThrow(() -> new ResourceNotFoundException("rn_nil_test2_header_t not found"));
    }

	 // ==========UPDATE LINES=========
    @PutMapping("/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t/{rn_nil_test2_line_t_id}")
    public	Rn_nil_test2_line_t	updateRn_nil_test2_line_t(@PathVariable(value = "rn_nil_test2_header_t_id") Integer	rn_nil_test2_header_t_id,
        @PathVariable(value = "rn_nil_test2_line_t_id") Integer	rn_nil_test2_line_t_id, @Valid @RequestBody	Rn_nil_test2_line_t rn_nil_test2_line_tRequest)
    throws ResourceNotFoundException {
        if (!rn_nil_test2_repository.existsById(rn_nil_test2_header_t_id)) {
            throw new ResourceNotFoundException("rn_nil_test2_header_t_id not found");
        }

        return	rn_nil_test2_line_repository.findById(rn_nil_test2_line_t_id).map(rn_nil_test2_line_t -> {
		//===============LOOP TO UPDATE GET SET DEVELOPEMENT================
		rn_nil_test2_line_t.setL_textfield5(rn_nil_test2_line_tRequest.getL_textfield5());
rn_nil_test2_line_t.setL_textfield6(rn_nil_test2_line_tRequest.getL_textfield6());
rn_nil_test2_line_t.setL_textfield7(rn_nil_test2_line_tRequest.getL_textfield7());
rn_nil_test2_line_t.setL_textfield8(rn_nil_test2_line_tRequest.getL_textfield8());
rn_nil_test2_line_t.setAttribute1(rn_nil_test2_line_tRequest.getAttribute1());
rn_nil_test2_line_t.setAttribute2(rn_nil_test2_line_tRequest.getAttribute2());
rn_nil_test2_line_t.setAttribute3(rn_nil_test2_line_tRequest.getAttribute3());
rn_nil_test2_line_t.setAttribute4(rn_nil_test2_line_tRequest.getAttribute4());
rn_nil_test2_line_t.setAttribute5(rn_nil_test2_line_tRequest.getAttribute5());
rn_nil_test2_line_t.setAttribute6(rn_nil_test2_line_tRequest.getAttribute6());
rn_nil_test2_line_t.setAttribute7(rn_nil_test2_line_tRequest.getAttribute7());
rn_nil_test2_line_t.setAttribute8(rn_nil_test2_line_tRequest.getAttribute8());
rn_nil_test2_line_t.setAttribute9(rn_nil_test2_line_tRequest.getAttribute9());
rn_nil_test2_line_t.setAttribute10(rn_nil_test2_line_tRequest.getAttribute10());
rn_nil_test2_line_t.setAttribute11(rn_nil_test2_line_tRequest.getAttribute11());
rn_nil_test2_line_t.setAttribute12(rn_nil_test2_line_tRequest.getAttribute12());
rn_nil_test2_line_t.setAttribute13(rn_nil_test2_line_tRequest.getAttribute13());
rn_nil_test2_line_t.setAttribute14(rn_nil_test2_line_tRequest.getAttribute14());
rn_nil_test2_line_t.setAttribute15(rn_nil_test2_line_tRequest.getAttribute15());
rn_nil_test2_line_t.setFlex1(rn_nil_test2_line_tRequest.getFlex1());
rn_nil_test2_line_t.setFlex2(rn_nil_test2_line_tRequest.getFlex2());
rn_nil_test2_line_t.setFlex3(rn_nil_test2_line_tRequest.getFlex3());
rn_nil_test2_line_t.setFlex4(rn_nil_test2_line_tRequest.getFlex4());
rn_nil_test2_line_t.setFlex5(rn_nil_test2_line_tRequest.getFlex5());

            return	rn_nil_test2_line_repository.save(rn_nil_test2_line_t);
        }).orElseThrow(() -> new ResourceNotFoundException("rn_nil_test2_line_t_id not found"));
    }

	 // ==========DELETE LINES=========
    @DeleteMapping("/rn_nil_test2_header_t/{rn_nil_test2_header_t_id}/rn_nil_test2_line_t/{rn_nil_test2_line_t_id}")
    public ResponseEntity<?> deleteRn_nil_test2_line_t(@PathVariable(value = "rn_nil_test2_header_t_id") Integer	rn_nil_test2_header_t_id,
        @PathVariable(value = "rn_nil_test2_line_t_id") Integer	rn_nil_test2_line_t_id) throws ResourceNotFoundException {
        return	rn_nil_test2_line_repository.findByHeaderIdAndLineId(rn_nil_test2_line_t_id, rn_nil_test2_header_t_id).map(rn_nil_test2_line_t -> {
            rn_nil_test2_line_repository.delete(rn_nil_test2_line_t);
            return ResponseEntity.ok().body("Record Deleted Successfully with Line Id :: " + rn_nil_test2_line_t_id);
        }).orElseThrow(() -> new ResourceNotFoundException(
            "Rn_nil_test2_line_t not found with id = " + rn_nil_test2_line_t_id + " and rn_nil_test2_header_t_id = " + rn_nil_test2_header_t_id));
    }
}