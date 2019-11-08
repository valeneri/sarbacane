package com.valentin.sarbacane.services.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.valentin.sarbacane.dto.CounterDto;
import com.valentin.sarbacane.dto.RecipientDto;
import com.valentin.sarbacane.repositories.RecipientRepository;
import com.valentin.sarbacane.services.RecipientService;

@Service
public class RecipientServiceImpl implements RecipientService {

	private static final String EMAIL_REGEXP = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	@Autowired
	private RecipientRepository recipientRepository;
	/**
	 * 
	 * 
	 */
	public CounterDto saveRecipientFromFile(MultipartFile csvFile) {

		List<RecipientDto> recipientList = new ArrayList<RecipientDto>();
		Set<RecipientDto> recipientSet = new HashSet<RecipientDto>();
		CounterDto counterDto = new CounterDto();
		
		Integer valid = 0;
		Integer invalid = 0;
		Integer total = 0;
		
		try {
			BufferedReader fileReader = new BufferedReader(new InputStreamReader(csvFile.getInputStream()));
			String line;

			Integer phonePosition = null;
			Integer emailPosition = null;

			while ((line = fileReader.readLine()) != null) {
				String[] data = csvFormatter(line).split(";");

				if ("email".equals(data[0])) {
					emailPosition = 0;
					phonePosition = 1;
				} else if ("phone".equals(data[0])) {
					phonePosition = 0;
					emailPosition = 1;
				} else {
					RecipientDto recipient = new RecipientDto();
					
					if (checkValidPhone(data[phonePosition]) && checkValidMail(data[emailPosition])) {
						recipient.setEmail(data[emailPosition]);
						recipient.setPhone(data[phonePosition]);
						recipientSet.add(recipient);
					}
				}
				total++;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
		recipientList.addAll(recipientSet);
		List<RecipientDto> recipientListReturned = recipientRepository.saveAll(recipientList);

		valid = recipientListReturned.size();
		invalid = total - recipientListReturned.size();
		
		counterDto.setValid(valid);
		counterDto.setInvalid(invalid);
		
		return counterDto;
	}
	
	public String csvFormatter(String data) {
		data.replace(",", ";");
		data.replace("\"", "");
		
		return data;
	}
	
	public boolean checkValidPhone(String phone) {
		
		if (phone.length() != 10) {
			return false;
		} else if (!phone.matches("\\d+")) {
			return false;
		} else if (!"06".equals(phone.substring(0, 2)) && !"07".equals(phone.substring(0, 2))) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean checkValidMail(String email) {
		
		if (!email.matches(EMAIL_REGEXP)) {
			return false;
		} else {
			return true;
		}
	}
}
