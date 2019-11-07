package com.valentin.sarbacane.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.RecipientDto;
import com.valentin.sarbacane.services.DocumentService;
import com.valentin.sarbacane.services.impl.DocumentServiceImpl;

@RestController
@RequestMapping("/documents")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;
	
	@PostMapping("/upload")
	public Set<RecipientDto> getRecipientListFromFile(@RequestParam("csvFile") MultipartFile csvFile) {
		
		return documentService.getRecipientListFromFile(csvFile);
	}		

}
