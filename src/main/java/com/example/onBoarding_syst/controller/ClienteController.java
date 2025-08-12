package com.example.onBoarding_syst.controller;

import com.example.onBoarding_syst.model.Cliente;

import com.example.onBoarding_syst.repositories.ClienteRepos;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    @Autowired
    private ClienteRepos clienteRepository;
    
    private static Logger log = LoggerFactory.getLogger(ClienteController.class);

    @GetMapping
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
    	if (cliente.getId() == null) {
            cliente.setId(UUID.randomUUID().toString()); 
        }
    	
    	log.info("Creazione cliente: {} {}", cliente.getNome(), cliente.getCognome());
    	
    	Cliente clienteSalvato = clienteRepository.save(cliente);
    	
    	log.debug("Cliente salvato: {}", clienteSalvato);
        return ResponseEntity.ok(clienteSalvato);
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable String id, @RequestBody Cliente clienteDetails) {
        Optional<Cliente> clienteMod = clienteRepository.findById(id);
        if (clienteMod.isEmpty()) {
            return ResponseEntity.notFound().build(); 
        }

        Cliente cliente = clienteMod.get();
        
        cliente.setNome(clienteDetails.getNome());
        cliente.setCognome(clienteDetails.getCognome());
        cliente.setEmail(clienteDetails.getEmail());
        cliente.setStatoOnboarding(clienteDetails.getStatoOnboarding());

        Cliente modificato = clienteRepository.save(cliente);
        return ResponseEntity.ok(modificato); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable String id) {
    	System.out.println("Eliminazione cliente con id: " + id); //per dubug
    	
        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> getClienteByEmail(@PathVariable String email) {
        return clienteRepository.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/stato/{stato}")
    public ResponseEntity<List<Cliente>> getClientiByStato(@PathVariable String stato) {
        List<Cliente> clienti = clienteRepository.findByStatoOnboarding(stato.toUpperCase());
        if (clienti.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clienti);
    }


}
