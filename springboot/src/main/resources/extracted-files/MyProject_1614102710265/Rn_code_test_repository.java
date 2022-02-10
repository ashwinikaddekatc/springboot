package com.realnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.model.Rn_code_test_t;

@Repository
public interface	Rn_code_test_repository	extends JpaRepository<Rn_code_test_t, Integer>{

}