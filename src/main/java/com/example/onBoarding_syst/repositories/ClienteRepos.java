package com.example.onBoarding_syst.repositories;

import com.example.onBoarding_syst.model.Cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClienteRepos extends MongoRepository<Cliente, String> {

	Optional<Cliente> findByEmail(String email);
	List<Cliente> findByStatoOnboarding(String statoOnboarding);
	
}
