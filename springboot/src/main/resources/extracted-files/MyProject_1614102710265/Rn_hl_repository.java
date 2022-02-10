package com.realnet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.model.Rn_hl_header_t;

@Repository
public interface Rn_hl_repository extends JpaRepository<Rn_hl_header_t, Integer> {

}