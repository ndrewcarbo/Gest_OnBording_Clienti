package com.example.onBoarding_syst;

import com.example.onBoarding_syst.controller.ClienteController;
import com.example.onBoarding_syst.model.Cliente;
import com.example.onBoarding_syst.repositories.ClienteRepos;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteRepos clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void quandoClienteValido_alloraStatus201() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("Mario");
        cliente.setCognome("Rosso");
        cliente.setEmail("mario.rosso@email.com");
        //cliente.setStatoOnboarding("IN_CORSO");
        
        when(clienteRepository.save(any())).thenReturn(cliente);

        mockMvc.perform(post("/api/clienti")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Mario"));
    }

    @Test
    void quandoClienteNonValido_alloraStatus400() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNome("");  
        cliente.setEmail("emailNonValida");

        mockMvc.perform(post("/api/clienti")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
    }
}
