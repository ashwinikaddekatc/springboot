package com.realnet.Module.salesnew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.Module.salesnew.entity.*;

@Repository
public interface Flf_builderauthorrepo extends JpaRepository<Flf_builderauthor, Integer>{
}