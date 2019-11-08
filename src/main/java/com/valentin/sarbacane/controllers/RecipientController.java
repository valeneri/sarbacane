package com.valentin.sarbacane.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.CounterDto;
import com.valentin.sarbacane.exceptions.InvalidFileException;
import com.valentin.sarbacane.exceptions.EmptyFileException;
import com.valentin.sarbacane.exceptions.SarbacaneException;
import com.valentin.sarbacane.services.RecipientService;

@RestController
@RequestMapping("/documents")
public class RecipientController {
	
	@Autowired
	private RecipientService recipientService;
	
	/**
	 * saveRecipientFromFile method will pass the file to the service, if its the right format, else throws exception
	 * 
	 * @param csvFile
	 * 				file from the client
	 * 
	 * @return CounterDto
	 * 				valid/invalid entries
	 * 
	 * @throws SarbacaneException
	 * 				if file is not .csv or if no file
	 */
	@PostMapping("/upload")
	public CounterDto saveRecipientFromFile(@RequestParam("csvFile") MultipartFile csvFile) throws SarbacaneException {
		
		if (csvFile.isEmpty()) {
			throw new EmptyFileException();
		}
		else if(!csvFile.getOriginalFilename().endsWith(".csv")) {
			throw new InvalidFileException();
		} else {
			return recipientService.saveRecipientFromFile(csvFile);
		}
	}		

}
