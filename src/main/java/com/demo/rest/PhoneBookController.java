/**
 * 
 */
package com.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.domain.PhoneBook;
import com.demo.dto.PhoneBookDTO;
import com.demo.dto.ResultDTO;
import com.demo.service.PhoneBookService;

/**
 * @author balasunku
 *
 */

@RestController
@RequestMapping(value="/phoneBook")
public class PhoneBookController {
	
	@Autowired
	PhoneBookService phoneBookService;
	
	@RequestMapping(value="/create", 
			method = RequestMethod.POST,
            produces = "application/json")
	public ResponseEntity<ResultDTO> create(@RequestBody PhoneBookDTO dto) {
		
		ResultDTO result = phoneBookService.create(dto);
		
		return new ResponseEntity<ResultDTO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="getAll",
			method = RequestMethod.GET,
			produces = "application/json")
	public ResponseEntity<?> getAll() {
		List<PhoneBook> result = phoneBookService.getAll();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="delete/{id}",
			method = RequestMethod.DELETE,
			produces = "application/json")
	public ResponseEntity<ResultDTO> delete(@PathVariable Long id) {
		
		ResultDTO result = phoneBookService.delete(id);
		
		return new ResponseEntity<ResultDTO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="/update", 
			method = RequestMethod.PUT,
            produces = "application/json")
	public ResponseEntity<ResultDTO> update(@RequestBody PhoneBookDTO dto) {
		
		ResultDTO result = phoneBookService.update(dto);
		
		return new ResponseEntity<ResultDTO>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value="getBySurname",
			method = RequestMethod.GET,
			produces = "application/json")
	public ResponseEntity<?> getBySurname(@RequestParam(value="surname", required = true) String surname) {
		
		ResultDTO result = phoneBookService.getBySurname(surname);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
