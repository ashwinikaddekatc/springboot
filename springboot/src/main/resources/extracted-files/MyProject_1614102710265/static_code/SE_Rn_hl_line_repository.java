"package com.realnet." + module_name + ".repository;" + "\r\n" + 
"" + "\r\n" + 
"import java.util.List;" + "\r\n" + 
"import java.util.Optional;" + "\r\n" + 
"" + "\r\n" + 
"import org.springframework.data.jpa.repository.JpaRepository;" + "\r\n" + 
"import org.springframework.data.jpa.repository.Modifying;" + "\r\n" + 
"import org.springframework.data.jpa.repository.Query;" + "\r\n" + 
"import org.springframework.data.repository.query.Param;" + "\r\n" + 
"import org.springframework.stereotype.Repository;" + "\r\n" + 
"" + "\r\n" + 
"import com.realnet.model.Rn_hl_line_t;" + "\r\n" + 
"" + "\r\n" + 
"@Repository" + "\r\n" + 
"public interface Rn_hl_line_repository extends JpaRepository<Rn_hl_line_t, Integer> {" + "\r\n" + 
"" + "\r\n" + 
"	@Query(value = \"SELECT * FROM RN_HL_LINE_T WHERE rn_hl_header_t_id = ?1\" , nativeQuery = true)" + "\r\n" + 
"	List<Rn_hl_line_t> findByRn_hl_header_t_Id(Integer rn_hl_header_t_id);" + "\r\n" + 
"" + "\r\n" + 
"	@Query(value = \"SELECT * FROM RN_HL_LINE_T WHERE rn_hl_header_t_id = :h_id AND l_id= :l_id\" , nativeQuery = true)" + "\r\n" + 
"	Optional<Rn_hl_line_t> findByHeaderIdAndLineId(@Param(\"h_id\")Integer headerId, @Param(\"l_id\") Integer lineId);" + "\r\n" + 
"" + "\r\n" + 
"}" 
