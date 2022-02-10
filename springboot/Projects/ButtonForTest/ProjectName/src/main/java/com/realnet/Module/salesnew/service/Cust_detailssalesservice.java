package com.realnet.comm.Module_1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.realnet.Module.salesnew.entity.Cust_detailssales;
import com.realnet.Module.salesnew.repository.Cust_detailsSalesrepository;
import com.realnet.Module.salesnew.responce.Cust_detailsSalesResponce;
import com.realnet.Module.salesnew.service.Cust_detailssalesservice;
public interface Cust_detailssalesservice{
    public Page<Cust_detailssales> getlist(Pageable page);
    public Cust_detailssales createsale(Cust_detailssales data);
    public Cust_detailssales getid(int id);
    public Cust_detailssales updateById(int id, Cust_detailssales sale);
    public boolean deleteById(int id);
    
}