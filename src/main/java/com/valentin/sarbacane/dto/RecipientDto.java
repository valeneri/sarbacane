package com.valentin.sarbacane.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recipients")
//@CompoundIndexes({ @CompoundIndex(name = "email_phone", def = "{'email': 1, 'phone': 1}") })
public class RecipientDto {
	
	@Id
	private String id;
	private String email;
	private String phone;
	
	/**
	 * @param email
	 * @param phone
	 */
	public RecipientDto(String email, String phone) {
		super();
		this.email = email;
		this.phone = phone;
	}
	
	/**
	 * 
	 */
	public RecipientDto() {
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
