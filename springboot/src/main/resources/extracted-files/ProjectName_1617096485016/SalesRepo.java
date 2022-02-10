package com.realnet.comm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.comm.entity.Sales;

import io.swagger.annotations.ApiModelProperty;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Integer>{
	
	
	
	
	//public List<Sales> findAll();
    public Page<Sales> findAll(Pageable p);

    
    
    //    public Sales save(Sales sales);
//   // public Sales findbyId(int id);
//	//private List<Object> items;
//    Sales  findById(int id);
}
