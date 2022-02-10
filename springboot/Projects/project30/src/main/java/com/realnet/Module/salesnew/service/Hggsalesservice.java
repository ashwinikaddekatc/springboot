package com.realnet.comm.testmodule30.service;

import com.realnet.comm.entity.sales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Hggsalesservice{
    public Page<sales> getlist(Pageable page);
    public sales createsale(sales data);
    public sales getid(int id);
    public sales updateById(int id, sales sale);
    public boolean deleteById(int id);
    
}