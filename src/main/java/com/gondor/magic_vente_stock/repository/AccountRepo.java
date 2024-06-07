package com.gondor.magic_vente_stock.repository;


import com.gondor.magic_vente_stock.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends JpaRepository<Account,Integer> {
    public Account findByUsername(String login);
}