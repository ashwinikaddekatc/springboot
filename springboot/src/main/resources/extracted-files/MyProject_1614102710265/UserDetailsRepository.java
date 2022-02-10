package com.realnet.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.realnet.model.Rn_Users;


public interface UserDetailsRepository extends JpaRepository<Rn_Users, Integer> {

}
