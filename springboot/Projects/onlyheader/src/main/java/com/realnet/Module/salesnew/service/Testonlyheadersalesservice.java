package com.realnet.Module.salesnew.service;

import com.realnet.Module.salesnew.entity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Testonlyheadersalesservice{
    public Page<Testonlyheadersales> getlist(Pageable page);
    public Testonlyheadersales createsale(Testonlyheadersales data);
    public Testonlyheadersales getid(int id);
    public Testonlyheadersales updateById(int id, Testonlyheadersales sale);
    public boolean deleteById(int id);
    
}