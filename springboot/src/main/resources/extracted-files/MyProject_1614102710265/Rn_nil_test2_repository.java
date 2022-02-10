package com.realnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.model.Rn_nil_test2_header_t;

@Repository
public interface	Rn_nil_test2_repository	extends JpaRepository<Rn_nil_test2_header_t, Integer>{

}