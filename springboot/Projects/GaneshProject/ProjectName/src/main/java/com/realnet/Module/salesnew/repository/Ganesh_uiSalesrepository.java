package com.realnet.comm.GaneshMod.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.realnet.Module.salesnew.entity.Ganesh_uisales;
import com.realnet.Module.salesnew.repository.Ganesh_uiSalesrepository;
import com.realnet.Module.salesnew.responce.Ganesh_uiSalesResponce;
import com.realnet.Module.salesnew.service.Ganesh_uisalesservice;


@Repository
public interface Ganesh_uiSalesrepository extends JpaRepository<Ganesh_uisales, Integer>{

	
}