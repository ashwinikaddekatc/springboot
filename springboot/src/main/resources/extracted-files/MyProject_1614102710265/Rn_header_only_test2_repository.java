package com.realnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.model.Rn_header_only_test2_t;

@Repository
public interface	Rn_header_only_test2_repository	extends JpaRepository<Rn_header_only_test2_t, Integer>{

}