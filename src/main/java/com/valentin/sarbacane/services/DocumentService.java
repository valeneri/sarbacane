package com.valentin.sarbacane.services;

import java.io.InputStream;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.RecipientDto;

public interface DocumentService {
	
	public Set<RecipientDto> getRecipientListFromFile(MultipartFile csvFile);

}
