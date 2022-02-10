package com.realnet.comm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.comm.entity.SalesPerson;

@Repository
public interface SalesPersonRepo extends JpaRepository<SalesPerson, Integer> {

}
