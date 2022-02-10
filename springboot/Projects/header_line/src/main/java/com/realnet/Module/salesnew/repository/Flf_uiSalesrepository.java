package com.realnet.Module.salesnew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.Module.salesnew.entity.*;

@Repository
public interface Flf_uiSalesrepository extends JpaRepository<Flf_uisales, Integer>{
	// for pagination
		Page<Flf_uisales> findAll(Pageable p);
}