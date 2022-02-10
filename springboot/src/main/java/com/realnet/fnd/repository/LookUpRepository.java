package com.realnet.fnd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.realnet.fnd.entity.Rn_Lookup_Values;

@Repository
public interface LookUpRepository extends JpaRepository<Rn_Lookup_Values, Integer> {
	
	@Query(value = "SELECT LOOKUP_CODE FROM rn_lookup_values_t WHERE LOOKUP_TYPE ='Form_ext'" , nativeQuery = true)
	List<String> getLookupValues();
	
	@Query(value = "SELECT LOOKUP_CODE FROM rn_lookup_values_t WHERE LOOKUP_TYPE ='DataType'" , nativeQuery = true)
	List<String> getDataTypeValues();
}
