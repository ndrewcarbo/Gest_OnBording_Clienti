package com.example.onBoarding_syst;

import com.example.onBoarding_syst.model.Cliente;
import com.example.onBoarding_syst.repositories.ClienteRepos;

//import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
public class ClienteReposTest {

    @Autowired
    private ClienteRepos clienteRepos;

    @Test
    void quandoSalvoCliente_poiLoTrovoNelDatabase() {
        Cliente cliente = new Cliente("Marco", "Rossi", "marco.rossi@email.com", "COMPLETATO");

        clienteRepos.save(cliente);

        List<Cliente> clienti = clienteRepos.findAll();

        assertThat(clienti).isNotEmpty();
        assertThat(clienti.get(0).getNome()).isEqualTo("Marco");
    }
    
    /*
    @Test
    void quandoEmailMancante_alloraLancioEccezione() {
        Cliente cliente = new Cliente("Luca", "Bianchi", "", "IN_CORSO");

        org.junit.jupiter.api.Assertions.assertThrows(
            ConstraintViolationException.class,
            () -> clienteRepos.save(cliente)
        );
    }
    
    */
    
}
