package com.realnet.Module.salesnew.service;

import com.realnet.Module.salesnew.entity.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface Flf_uisalesservice{
    public Page<Flf_uisales> getlist(Pageable page);
    public Flf_uisales createsale(Flf_uisales data);
    public Flf_uisales getid(int id);
    public Flf_uisales updateById(int id, Flf_uisales sale);
    public boolean deleteById(int id);
    
}