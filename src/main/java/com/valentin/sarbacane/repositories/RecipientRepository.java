package com.valentin.sarbacane.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.valentin.sarbacane.dto.RecipientDto;

@Repository
public interface RecipientRepository extends MongoRepository<RecipientDto, String> { 
}
