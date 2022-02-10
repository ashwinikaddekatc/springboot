package com.realnet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.realnet.model.Rn_hl_test3_line_t;


@Repository
public interface	Rn_hl_test3_line_repository	extends JpaRepository<Rn_hl_test3_line_t, Integer>{

	@Query(value = "SELECT * FROM RN_HL_TEST3_LINE_T WHERE rn_hl_test3_header_t_id = ?1" , nativeQuery = true)
	List<Rn_hl_test3_line_t> findByRn_hl_test3_header_tId(Integer	rn_hl_test3_header_t_id);
 	@Query(value = "SELECT * FROM RN_HL_TEST3_LINE_T WHERE rn_hl_test3_header_t_id = :h_id AND l_id = :l_id" , nativeQuery = true)
	Optional<Rn_hl_test3_line_t> findByHeaderIdAndLineId(@Param("h_id")Integer headerId, @Param("l_id") Integer lineId);
}