package com.realnet.Module.salesnew.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.realnet.Module.salesnew.entity.Abtuisales;
import com.realnet.Module.salesnew.repository.AbtuiSalesrepository;
import com.realnet.Module.salesnew.responce.AbtuiSalesResponse;
import com.realnet.Module.salesnew.service.Abtuisalesservice;
@Repository
public interface AbtuiSalesrepository extends JpaRepository<Abtuisales, Integer>{

	
}