package com.example.onBoarding_syst.ConfigSwagger;

import java.awt.Desktop;
import java.net.URI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SwaggerAtStart implements CommandLineRunner {
	
	@Override
    public void run(String... args) throws Exception {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse(new URI("http://localhost:8081/swagger-ui/index.html"));
        }
    }
}
