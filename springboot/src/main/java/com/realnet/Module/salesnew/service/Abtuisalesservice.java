package com.realnet.Module.salesnew.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.realnet.Module.salesnew.entity.Abtuisales;
import com.realnet.Module.salesnew.repository.AbtuiSalesrepository;
import com.realnet.Module.salesnew.responce.AbtuiSalesResponse;
import com.realnet.Module.salesnew.service.Abtuisalesservice;
public interface Abtuisalesservice{
    public Page<Abtuisales> getlist(Pageable page);
    public Abtuisales save(Abtuisales data);
    public Abtuisales getid(int id);
    public Abtuisales updateById(int id, Abtuisales sale);
    public boolean deleteById(int id);
    
}