package com.realnet.comm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realnet.comm.entity.author;

@Repository
public interface authorrepo extends JpaRepository<author, Integer> {

}
