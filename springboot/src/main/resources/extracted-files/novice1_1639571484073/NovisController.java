package com.realnet.ncso.controller1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realnet.ncso.entity1.PmodMix;
import com.realnet.ncso.entity1.ShipMix;
import com.realnet.ncso.service.impl1.ShipServiceImpl;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value="/ncso1" )
@Api(tags = {"/ncso"})
public class NovisController {
	private ShipServiceImpl shipServiceImpl;
	@Autowired
	public NovisController(ShipServiceImpl shipServiceImpl) {
		super();
		this.shipServiceImpl = shipServiceImpl;
	}
	@GetMapping("/getOneNovis/{name}/{inVoyNbr}")
	public ResponseEntity<?> getOneById(@PathVariable("name") String name,@PathVariable("inVoyNbr")String inVoyNbr){
		Optional<Object> s = shipServiceImpl.getOneNovis(name, inVoyNbr);
		try {
			if(s.get()!=null) {
				Object[] q =(Object[]) s.get();
				ShipMix k = new ShipMix();
				 k.setVESSEL_CODE((String) q[0]);
				 k.setVESSEL_NAME((String) q[1]);
				 k.setIN_VOYAGE((String) q[2]);
				 k.setOUT_VOYAGE((String) q[3]);
				 k.setCALL_NO((String) q[4]);
				 k.setLINE_CODE((String) q[5]);
				 k.setLOA((BigDecimal) q[6]);
				 k.setLOA_UOM((String) q[7]);
				 k.setGT((BigDecimal) q[8]);
				 k.setATA((Date) q[9]);
				 k.setATD((Date) q[10]);
				 k.setLOCATION((String) q[11]);
				 k.setSERVICE_ID((String) q[12]);
				return new ResponseEntity<>(k,HttpStatus.OK);
			}
			}catch(Exception e) {
				return new ResponseEntity<>("No Novis Found",HttpStatus.OK);
			}
			return new ResponseEntity<>("No Novis Found",HttpStatus.OK);
	}
	@GetMapping("/getAllNovis")
	public ResponseEntity<?> getAll(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "5", required = false) Integer size){
		Pageable p =PageRequest.of(page,size);
		Object s = shipServiceImpl.getAll(p);
		ArrayList<ShipMix> sm = new ArrayList<ShipMix>();
		List<Object> a = (List<Object>) s;
		Iterator i = a.iterator();
		while(i.hasNext()) {
			Object[] q = (Object[]) i.next();
			 ShipMix k = new ShipMix();
			 k.setVESSEL_CODE((String) q[0]);
			 k.setVESSEL_NAME((String) q[1]);
			 k.setIN_VOYAGE((String) q[2]);
			 k.setOUT_VOYAGE((String) q[3]);
			 k.setCALL_NO((String) q[4]);
			 k.setLINE_CODE((String) q[5]);
			 k.setLOA((BigDecimal) q[6]);
			 k.setLOA_UOM((String) q[7]);
			 k.setGT((BigDecimal) q[8]);
			 k.setATA((Date) q[9]);
			 k.setATD((Date) q[10]);
			 k.setLOCATION((String) q[11]);
			 k.setSERVICE_ID((String) q[12]);
			 sm.add(k);
		}
		return new ResponseEntity<>(sm,HttpStatus.OK);
	}
	@GetMapping("/getAllPmod")
	public ResponseEntity<?> getAlPmodsl(
			@RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value = "size", defaultValue = "5", required = false) Integer size){
		Pageable p =PageRequest.of(page,size);
		Object s = shipServiceImpl.getAllPmod(p);
		ArrayList<PmodMix> pm = new ArrayList<PmodMix>();
		List<Object> a = (List<Object>) s;
		Iterator i = a.iterator();
		while(i.hasNext()) {
			Object[] q = (Object[]) i.next();
			PmodMix k = new PmodMix();
			k.setVesselCode((String) q[0]);
			k.setVesselName((String) q[1]);
			k.setVcn((String) q[2]);
			k.setCallSign((String) q[3]);
			k.setLineCode((String) q[4]);
			k.setLoa((BigDecimal) q[5]);
			k.setGt((BigDecimal) q[6]);
			k.setAta((Date) q[7]);
			k.setAtd((Date) q[8]);
			pm.add(k);
		}
		return new ResponseEntity<>(pm,HttpStatus.OK);
	}
	@GetMapping("/getOnePmod/{vcn}")
	public ResponseEntity<?> getOnePmod(@PathVariable("vcn") String vcn){
		Optional<Object> s = shipServiceImpl.getOnePmod(vcn);
		try {
		if(s.get()!=null) {
			Object[] q =(Object[]) s.get();
			System.out.println(q[0]);
			PmodMix k = new PmodMix();
			k.setVesselCode((String) q[0]);
			k.setVesselName((String) q[1]);
			k.setVcn((String) q[2]);
			k.setCallSign((String) q[3]);
			k.setLineCode((String) q[4]);
			k.setLoa((BigDecimal) q[5]);
			k.setGt((BigDecimal) q[6]);
			k.setAta((Date) q[7]);
			k.setAtd((Date) q[8]);
			return new ResponseEntity<>(k,HttpStatus.OK);
		}
		}catch(Exception e) {
			return new ResponseEntity<>("No Pmod Found",HttpStatus.OK);
		}
		return new ResponseEntity<>("No Pmod Found",HttpStatus.OK);
	}
}
