package com.realnet.comm.GaneshMod.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.realnet.Module.salesnew.entity.Ganesh_uisales;
import com.realnet.Module.salesnew.repository.Ganesh_uiSalesrepository;
import com.realnet.Module.salesnew.responce.Ganesh_uiSalesResponce;
import com.realnet.Module.salesnew.service.Ganesh_uisalesservice;
public interface Ganesh_uisalesservice{
    public Page<Ganesh_uisales> getlist(Pageable page);
    public Ganesh_uisales createsale(Ganesh_uisales data);
    public Ganesh_uisales getid(int id);
    public Ganesh_uisales updateById(int id, Ganesh_uisales sale);
    public boolean deleteById(int id);
    
}