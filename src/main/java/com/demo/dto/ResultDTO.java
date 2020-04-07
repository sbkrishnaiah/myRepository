/**
 * 
 */
package com.demo.dto;

import java.util.List;

import com.demo.domain.PhoneBook;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author balasunku
 *
 */
@JsonInclude(Include.NON_NULL)
public class ResultDTO {
	
	private String status;
	private String Message;
	private List<PhoneBook> phoneBookLst; 
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public List<PhoneBook> getPhoneBookLst() {
		return phoneBookLst;
	}
	public void setPhoneBookLst(List<PhoneBook> phoneBookLst) {
		this.phoneBookLst = phoneBookLst;
	}


}
