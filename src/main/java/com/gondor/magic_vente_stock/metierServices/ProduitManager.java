package com.gondor.magic_vente_stock.metierServices;

import com.gondor.magic_vente_stock.repository.AccountRepo;
import com.gondor.magic_vente_stock.repository.ProduitRepo;
import com.gondor.magic_vente_stock.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ProduitManager {
    private final ProduitRepo produitRepo;

    private final PasswordEncoder passwordEncoder;

    private final AccountRepo accountRepo;

    private final RoleRepo roleRepo;

    public ProduitManager(ProduitRepo produitRepo, PasswordEncoder passwordEncoder, AccountRepo accountRepo, RoleRepo roleRepo){
        this.produitRepo = produitRepo;
        this.passwordEncoder = passwordEncoder;
        this.accountRepo = accountRepo;
        this.roleRepo = roleRepo;
    }

    public Page<Produit> getAllProduct(int page, int perPage){
        log.debug("test");
        Pageable pageable = PageRequest.of(page,perPage);
        return this.produitRepo.findAll(pageable);
    }

    public List<Produit> rechercherProduitDuJour(){
        return produitRepo.getAllProductOfTheDay();
    }

    //just for testing purpose
    public Account save(Account a){
        Role r = roleRepo.findByNom("client");
        a.setRole(r);
        a.setPassword(passwordEncoder.encode(a.getPassword()));
        return accountRepo.save(a);
    }

}
