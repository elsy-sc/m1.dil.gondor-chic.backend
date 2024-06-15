package com.gondor.magic_vente_stock.configuration;

import com.gondor.magic_vente_stock.metierServices.Client;
import com.gondor.magic_vente_stock.metierServices.ClientManager;
import com.gondor.magic_vente_stock.metierServices.Panier;
import com.gondor.magic_vente_stock.metierServices.ProduitManager;
import com.gondor.magic_vente_stock.repository.ClientRepo;
import com.gondor.magic_vente_stock.repository.PanierRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Config {

    @Bean
    CommandLineRunner commandLineRunner(ProduitManager produitManager, ClientManager clientManager, ClientRepo clientRepo, PanierRepo panierRepo){
        return args -> {

            // au moment de l'inscription : creer un panier vide pour le client

            /*Client client = new Client();
            client.setNom("Gondori");
            client.setPrenom("Legolas");
            client.setPseudo("legolas");
            client.setMotDePasse("legolas123");
            clientManager.save(client);*/

            /*Client c = clientRepo.getById("client_1");

            Panier panier = new Panier(c);
            panierRepo.save(panier);
            */
        };
    }
}
