package com.example.onBoarding_syst.repositories;

import com.example.onBoarding_syst.model.Cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;


@Validated
@Repository
public interface ClienteRepos extends MongoRepository<Cliente, String> {

	Optional<Cliente> findByEmail(String email);
	List<Cliente> findByStatoOnboarding(String statoOnboarding);
	
}
