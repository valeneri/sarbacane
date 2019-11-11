package com.valentin.sarbacane.services;


import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.CounterDto;

public interface RecipientService {
	
	/**
	 * saveRecipientFromFile method declaration
	 * 
	 * @param csvFile
	 * 
	 * @return CounterDto
	 */
	public CounterDto saveRecipientFromFile(MultipartFile csvFile);

}
