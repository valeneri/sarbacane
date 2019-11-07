package com.valentin.sarbacane.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.RecipientDto;
import com.valentin.sarbacane.repositories.DocumentRepository;
import com.valentin.sarbacane.services.DocumentService;

@Service
public class DocumentServiceImpl implements DocumentService {
	
	@Autowired
	private DocumentRepository documentRepository;
	
	public Set<RecipientDto> getRecipientListFromFile(MultipartFile csvFile) {
		
		Set<RecipientDto> recipientSet = new HashSet<RecipientDto>();
		
		try {
//			InputStream in = new InputStream(documentName + ".csv");
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
//			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			String line;
			
			Integer phonePosition = null;
			Integer emailPosition = null;
			
			
			while ((line = fileReader.readLine()) != null) {
			    String[] data = line.split(";");
			    
			    if ("email".equals(data[0])) {
			    	emailPosition = 0;
			    	phonePosition = 1;
			    } else if ("phone".equals(data[0])) {
			    	phonePosition = 0;
			    	emailPosition = 1;
			    } else {
			    	RecipientDto recipient = new RecipientDto();
			    	recipient.setEmail(data[emailPosition]);
			    	recipient.setPhone(data[phonePosition]);
			    	recipientSet.add(recipient);
			    }
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return recipientSet;
	}

}
