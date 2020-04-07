/**
 * 
 */
package com.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.demo.domain.PhoneBook;
import com.demo.dto.PhoneBookDTO;
import com.demo.dto.ResultDTO;
import com.demo.repository.PhoneBookRepository;
import com.demo.service.PhoneBookService;

/**
 * @author balasunku
 *
 */
@Service
@Transactional
public class PhoneBookServiceImpl implements PhoneBookService {
	
	@Autowired
	PhoneBookRepository phoneBookRepository; 

	@Override
	public ResultDTO create(PhoneBookDTO dto) {
		ResultDTO result = new ResultDTO();
		
		if (!StringUtils.hasText(dto.getSurName())) {
			result.setStatus("Fail");
			result.setMessage("Surname is mandatory to create phone book entry");
			return result;
		}
		
		if (!StringUtils.hasText(dto.getFirstName())) {
			result.setStatus("Fail");
			result.setMessage("Firstname is mandatory to create phone book entry");
			return result;
		}
		
		if (!StringUtils.hasText(dto.getPhoneNumber())) {
			result.setStatus("Fail");
			result.setMessage("Phone number is mandatory to create phone book entry");
			return result;
		}
		
		PhoneBook phonebook = phoneBookRepository.findByPhoneNumber(dto.getPhoneNumber());
		
		if (phonebook != null) {
			result.setStatus("Fail");
			result.setMessage("Phone number already exists");
			return result;
		}
		
		phonebook = new PhoneBook();
		phonebook.setSurName(dto.getSurName());
		phonebook.setFirstName(dto.getFirstName());
		phonebook.setPhoneNumber(dto.getPhoneNumber());
		phonebook.setAddress(dto.getAddress());
		
		phonebook = phoneBookRepository.save(phonebook);
		
		result.setStatus("Success");
		result.setMessage("Successfully created the phone book record");
		
		return result;
	}
	
	@Override
	public List<PhoneBook> getAll() {
		return phoneBookRepository.findAll();
	}
	
	@Override
	public ResultDTO delete(Long id) {
		ResultDTO result = new ResultDTO();
		
		Optional<PhoneBook> phoneBook = phoneBookRepository.findById(id);
		if (phoneBook.isPresent()) {
			phoneBookRepository.deleteById(id);
			result.setStatus("Success");
			result.setMessage("Successfully deleted the phone book record");
		}
		else {
			result.setStatus("Fail");
			result.setMessage("No phone book record found for given id");	
		}
		return result;
	}
	
	@Override
	public ResultDTO update(PhoneBookDTO dto) {
		ResultDTO result = new ResultDTO();
		
		if (dto.getId() == null) {
			result.setStatus("Fail");
			result.setMessage("Id can not be null");
			return result;
		}
		
		Optional<PhoneBook> phoneBookOpt = phoneBookRepository.findById(dto.getId());
		
		if (phoneBookOpt.isPresent()) {
			PhoneBook phoneBook = phoneBookOpt.get();
			if (!phoneBook.getPhoneNumber().equals(dto.getPhoneNumber()) 
					&& phoneBookRepository.findByPhoneNumber(dto.getPhoneNumber()) != null) {
				result.setStatus("Fail");
				result.setMessage("Phone number already exists");
			}
			else {
				phoneBook.setSurName(dto.getSurName());
				phoneBook.setFirstName(dto.getFirstName());
				phoneBook.setPhoneNumber(dto.getPhoneNumber());
				phoneBook.setAddress(dto.getAddress());
				
				phoneBookRepository.save(phoneBook);
				
				result.setStatus("Success");
				result.setMessage("Successfully updated the phone book record");
			}
		}
		else {
			result.setStatus("Fail");
			result.setMessage("No phone book record found for given id");
		}
 		
		return result;
	}
	
	@Override
	public ResultDTO getBySurname(String surname) {
		ResultDTO result = new ResultDTO();
		
		List<PhoneBook> phoneBookLst = phoneBookRepository.findBySurName(surname);
		if (phoneBookLst != null && phoneBookLst.size() > 0) {
			result.setStatus("Success");
			result.setPhoneBookLst(phoneBookLst);
		}
		else {
			result.setStatus("Fail");
			result.setMessage("No phone book record found for given surname");
		}
	
		return result;
	}

}
