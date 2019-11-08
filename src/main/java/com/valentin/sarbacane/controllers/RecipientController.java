package com.valentin.sarbacane.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.CounterDto;
import com.valentin.sarbacane.services.RecipientService;

@RestController
@RequestMapping("/documents")
public class RecipientController {
	
	@Autowired
	private RecipientService recipientService;
	
	@PostMapping("/upload")
	public CounterDto saveRecipientFromFile(@RequestParam("csvFile") MultipartFile csvFile) {
		
		return recipientService.saveRecipientFromFile(csvFile);
	}		

}
