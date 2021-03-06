package com.realnet.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.realnet.users.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByEmail(String email);

	Optional<User> findByUsernameAndPassword(String username, String password);
	// @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User p WHERE p.email = :email")

	Boolean existsByEmail(String email);

	// need modification
	@Query(value = "SELECT * FROM user WHERE ACCOUNT_ID =:accId AND STATUS =:status", nativeQuery = true)
	List<User> findByAccountIdAndStatus(@Param("accId") Long id, @Param("status") String status, Pageable pageable);
	
	@Query(value = "delete from user_roles where users_id= :user_id", nativeQuery = true)
    void deleteRelation(@Param("user_id") Long user_id);

	// List<User> findByAccountIdAndStatus(Long id, String status);
	
//	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User c WHERE c.name = :name")
//	Boolean existsByName(@Param("name") String name);

}
