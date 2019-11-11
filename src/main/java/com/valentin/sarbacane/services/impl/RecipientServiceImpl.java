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
import com.valentin.sarbacane.exceptions.InvalidColumnsException;
import com.valentin.sarbacane.repositories.RecipientRepository;
import com.valentin.sarbacane.services.RecipientService;

@Service
public class RecipientServiceImpl implements RecipientService {

	// regexp to check email validity, based on RFC 5322 official
	private static final String EMAIL_REGEXP = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	@Autowired
	private RecipientRepository recipientRepository;


	/**
	 * saveRecipientFromFile method will parse .csv file and check mail.phone validty then call the method from repository
	 * 
	 * @param csvFile 
	 * 			file from controller
	 * @return CounterDto
	 * 			object with number of valid and invalid entries
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

			boolean shouldCount = false;
			int lineNumber = 0;
			// read firstline first
			line = fileReader.readLine();
			if (line != null) {
				String[] data = csvFormatter(line).split(";");
				if ("email".equals(data[0]) && "phone".equals(data[1])) {
					emailPosition = 0;
					phonePosition = 1;
					shouldCount = true;
				} else if ("phone".equals(data[0]) && "email".equals(data[1])) {
					phonePosition = 0;
					emailPosition = 1;
					shouldCount = true;
				} else {
					throw new InvalidColumnsException();
				}
			} else {
				throw new InvalidColumnsException();
			}

			// file parsing
			while ((line = fileReader.readLine()) != null && shouldCount) {
				String[] data = csvFormatter(line).split(";");

				if (line.trim().isEmpty()) {
					continue;
				}
				
				if (lineNumber == 0) {
					lineNumber = 1;
					continue;
				} else {
					RecipientDto recipient = new RecipientDto();
					// check mail/phone format validty
					if (checkValidPhone(data[phonePosition]) && checkValidMail(data[emailPosition])) {
						recipient.setEmail(data[emailPosition]);
						recipient.setPhone(data[phonePosition]);
						recipientSet.add(recipient);
					}
				}
				total++;
			}
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
	/**
	 * csvFormatter method will format each line in a unique format
	 * 
	 * @param data
	 * 			line from the file
	 * 
	 * @return data
	 * 			line from the file (formatted)
	 */
	public String csvFormatter(String data) {
		data.replace(",", ";");
		data.replace("\"", "");

		return data;
	}

	/**
	 * checkValidPhone method will check format (length, digits and beginning with "06" or "07"
	 * 
	 * @param phone
	 * 				phone entry
	 * 
	 * @return boolean
	 */
	public boolean checkValidPhone(String phone) {

		if (phone.isEmpty()) {
			return false;
		} else if (phone.length() != 10) {
			return false;
		} else if (!phone.matches("\\d+")) {
			return false;
		} else if (!"06".equals(phone.substring(0, 2)) && !"07".equals(phone.substring(0, 2))) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * checkValidMail method will check if email entry is a real email format, based on regexp
	 * 
	 * @param email
	 * 			email entry
	 * @return boolean
	 */
	public boolean checkValidMail(String email) {

		if (email.isEmpty()) {
			return false;
		} else if (!email.matches(EMAIL_REGEXP)) {
			return false;
		} else {
			return true;
		}
	}
}
