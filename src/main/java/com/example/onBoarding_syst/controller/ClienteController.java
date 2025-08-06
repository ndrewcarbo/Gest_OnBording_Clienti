package com.example.onBoarding_syst.controller;

import com.example.onBoarding_syst.model.Cliente;
import com.example.onBoarding_syst.repositories.ClienteRepos;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clienti")
public class ClienteController {

    @Autowired
    private ClienteRepos clienteRepository;

    @GetMapping
    public List<Cliente> getAllClienti() {
        return clienteRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> createCliente(@Valid @RequestBody Cliente cliente) {
    	Cliente saved = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable String id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Cliente updateCliente(@PathVariable String id, @RequestBody Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);
        if (cliente == null) return null;

        cliente.setNome(clienteDetails.getNome());
        cliente.setCognome(clienteDetails.getCognome());
        cliente.setEmail(clienteDetails.getEmail());
        cliente.setStatoOnboarding(clienteDetails.getStatoOnboarding());

        return clienteRepository.save(cliente);
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable String id) {
        clienteRepository.deleteById(id);
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
