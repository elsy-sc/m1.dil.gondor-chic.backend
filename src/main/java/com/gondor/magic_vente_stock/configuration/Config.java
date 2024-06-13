package com.gondor.magic_vente_stock.configuration;

import com.gondor.magic_vente_stock.metierServices.ClientManager;
import com.gondor.magic_vente_stock.metierServices.ProduitManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(ProduitManager produitManager, ClientManager clientManager){
        return args -> {
            /*Role r = new Role("client");
            roleRepo.save(r);*/

            /*Client client = new Client();
            client.setNom("Gondori");
            client.setPrenom("Legolas");
            client.setPseudo("legolas");
            client.setMotDePasse("legolas123");
            clientManager.save(client);*/
        };
    }
}
