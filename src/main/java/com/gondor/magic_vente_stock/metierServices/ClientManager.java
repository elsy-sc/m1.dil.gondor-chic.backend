package com.gondor.magic_vente_stock.metierServices;

import com.gondor.magic_vente_stock.repository.AccountRepo;
import com.gondor.magic_vente_stock.repository.ProduitRepo;
import com.gondor.magic_vente_stock.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ClientManager {

    private final PasswordEncoder passwordEncoder;

    private final AccountRepo accountRepo;

    private final RoleRepo roleRepo;

    public ClientManager(PasswordEncoder passwordEncoder, AccountRepo accountRepo, RoleRepo roleRepo){
        this.passwordEncoder = passwordEncoder;
        this.accountRepo = accountRepo;
        this.roleRepo = roleRepo;
    }

    //just for testing purpose
    public Account save(Account a){
        Role r = roleRepo.findByNom("client");
        a.setRole(r);
        a.setMotDePasse(passwordEncoder.encode(a.getMotDePasse()));
        return accountRepo.save(a);
    }

}
