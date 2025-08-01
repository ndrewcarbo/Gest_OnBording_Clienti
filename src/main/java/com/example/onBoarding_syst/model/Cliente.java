package com.example.onBoarding_syst.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "clienti")
public class Cliente {

    @Id
    private String id;
    
    @NotBlank(message = "Il nome non può essere vuoto")
    private String nome;
    
    @NotBlank(message = "Il cognome non può essere vuoto")
    private String cognome;
    
    @Email(message = "Email non valida")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;
    
    @NotBlank(message = "Stato onboarding richiesto")
    private String statoOnboarding; 
    // ESEMPIO -> "IN_CORSO", "COMPLETATO", "BLOCCATO"


    public Cliente() {}

    public Cliente(String nome, String cognome, String email, String statoOnboarding) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.statoOnboarding = statoOnboarding;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatoOnboarding() {
        return statoOnboarding;
    }

    public void setStatoOnboarding(String statoOnboarding) {
        this.statoOnboarding = statoOnboarding;
    }
    
}

