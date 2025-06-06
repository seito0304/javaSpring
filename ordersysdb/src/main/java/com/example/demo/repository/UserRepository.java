package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO M_USER(eid, ename, password, mailaddress, station, " +
			"zip, address, tel, sendchk, possibleamt) " +
			"VALUES (?1, ?2, crypt(?3, gen_salt('bf')), ?4, ?5, ?6, ?7, ?8, ?9, ?10)", nativeQuery = true)
	void insertUser(String eid, String ename, String password, String mailaddress,
			String station, String zip, String address, String tel,
			Integer sendchk, Integer possibleamt);

	@Modifying
	@Transactional
	@Query(value = "UPDATE M_USER SET password = crypt(?2, gen_salt('bf')) WHERE eid = ?1", nativeQuery = true)
	int updatePassword(String eid, String newPassword);
}