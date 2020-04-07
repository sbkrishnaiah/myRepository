package com.demo.service;

import java.util.List;

import com.demo.domain.PhoneBook;
import com.demo.dto.PhoneBookDTO;
import com.demo.dto.ResultDTO;

public interface PhoneBookService {

	public ResultDTO create(PhoneBookDTO dto);
	
	public List<PhoneBook> getAll();
	
	public ResultDTO delete(Long id);
	
	public ResultDTO update(PhoneBookDTO dto);
	
	public ResultDTO getBySurname(String surname);
}
