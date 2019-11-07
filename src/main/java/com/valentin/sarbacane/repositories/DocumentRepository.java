package com.valentin.sarbacane.repositories;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Repository;

import com.valentin.sarbacane.dto.RecipientDto;

@Repository
public class DocumentRepository {

	
	public InputStream getRecipientListFromFile(String documentName) {
		
		InputStream in = null;
		
		in = getClass().getResourceAsStream("/data/" + documentName);
		
		return in;
	}
}
