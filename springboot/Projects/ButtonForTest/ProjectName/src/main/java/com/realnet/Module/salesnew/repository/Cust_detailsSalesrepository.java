package com.realnet.comm.Module_1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.realnet.Module.salesnew.entity.Cust_detailssales;
import com.realnet.Module.salesnew.repository.Cust_detailsSalesrepository;
import com.realnet.Module.salesnew.responce.Cust_detailsSalesResponce;
import com.realnet.Module.salesnew.service.Cust_detailssalesservice;


@Repository
public interface Cust_detailsSalesrepository extends JpaRepository<Cust_detailssales, Integer>{

	
}