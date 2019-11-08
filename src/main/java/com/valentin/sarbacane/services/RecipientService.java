package com.valentin.sarbacane.services;


import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.CounterDto;

public interface RecipientService {
	
	public CounterDto saveRecipientFromFile(MultipartFile csvFile);

}
