package com.realnet.ncso.service.impl1;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.realnet.ncso.repository1.ShipsRepository;

@Service
public class ShipServiceImpl {
	private ShipsRepository shipsRepository;
	
	@Autowired
	public ShipServiceImpl(ShipsRepository shipsRepository) {
		this.shipsRepository = shipsRepository;
	}
	
	
	public List<Object> getAll(Pageable page){
//		Page<ShipMix> p = shipsRepository.getAll(page);
		Page<Object> o = shipsRepository.getAll(page);
		return o.getContent();
	}
	public Optional<Object> getOneNovis(String name,String inVoyNbr){
		return shipsRepository.getOneNovis(name, inVoyNbr);
	}
	
	public List<Object> getAllPmod(Pageable page){
		Page<Object>  o = shipsRepository.getAllPmod(page);
		return o.getContent();
	}
	public Optional<Object> getOnePmod(String vcn){
		return shipsRepository.getOnePmod(vcn);
	}
	
}
