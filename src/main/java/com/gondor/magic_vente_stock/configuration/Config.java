package com.gondor.magic_vente_stock.configuration;

import com.gondor.magic_vente_stock.metierServices.ProduitManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(ProduitManager produitManager){
        return args -> {
            /*
            Account client = new Account();
            client.setNom("Gang");
            client.setPrenom("Bulla");
            client.setUsername("Bulla");
            client.setEmail("andersonmahosi@gmail.com");
            client.setPassword("Bulla");
            clientService.save(client);*/
        };
    }
}
