package com.realnet.comm.header.service;

import com.realnet.comm.entity.sales;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Test_19salesservice{
    public Page<sales> getlist(Pageable page);
    public sales createsale(sales data);
    public sales getid(int id);
    public sales updateById(int id, sales sale);
    public boolean deleteById(int id);
    
}