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

    public ProduitManager(ProduitRepo produitRepo, PasswordEncoder passwordEncoder){
        this.produitRepo = produitRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Produit> getAllProduct(){
        /*log.debug("test");
        Pageable pageable = PageRequest.of(page,perPage);*/
        return this.produitRepo.findAll();
    }

    public List<Produit> rechercherProduitDuJour(){
        return produitRepo.getAllProductOfTheDay();
    }

    //just for testing purpose

    public Produit save(Produit produit){
        return produitRepo.save(produit);
    }

}
