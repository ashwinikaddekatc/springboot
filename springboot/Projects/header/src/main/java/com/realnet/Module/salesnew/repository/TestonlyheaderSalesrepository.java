package com.realnet.Module.salesnew.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.Module.salesnew.entity.Testonlyheadersales;



@Repository
public interface TestonlyheaderSalesrepository extends JpaRepository<Testonlyheadersales, Integer>{
	
}