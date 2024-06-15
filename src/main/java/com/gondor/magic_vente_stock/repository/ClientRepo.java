package com.gondor.magic_vente_stock.repository;


import com.gondor.magic_vente_stock.metierServices.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,String> {
    public Client findByPseudo(String login);
}
