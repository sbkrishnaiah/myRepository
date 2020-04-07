package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.PhoneBook;

public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {
	
	public PhoneBook findByPhoneNumber(String phoneNumber);
	
	public List<PhoneBook> findBySurName(String surname);

}
