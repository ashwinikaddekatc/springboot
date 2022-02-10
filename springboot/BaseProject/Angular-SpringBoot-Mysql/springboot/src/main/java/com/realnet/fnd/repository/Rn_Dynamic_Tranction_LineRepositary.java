package com.realnet.fnd.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.realnet.fnd.entity.Rn_Dynamic_Transaction;
import com.realnet.fnd.entity.Rn_Dynamic_Transaction_line;

@Repository
public interface Rn_Dynamic_Tranction_LineRepositary extends JpaRepository<Rn_Dynamic_Transaction_line, Integer>{
	
	@Query(value = "SELECT * FROM RN_DYNAMIC_TRANSACTION_LINE WHERE FORM_ID =:form_id", nativeQuery = true)
	List<Rn_Dynamic_Transaction_line> findByFormId(@Param("form_id") int form_id);

	@Query(value = "SELECT * FROM RN_DYNAMIC_TRANSACTION_LINE WHERE ID =:id AND FORM_ID =:form_id", nativeQuery = true)
	Rn_Dynamic_Transaction_line findByIdAndFormId(@Param("id") int id, @Param("form_id") int form_id);

}
