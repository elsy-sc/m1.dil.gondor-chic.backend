package com.gondor.magic_vente_stock.controller;

import com.gondor.magic_vente_stock.metierServices.Produit;
import com.gondor.magic_vente_stock.metierServices.ProduitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/client/")
public class ProduitsServiceApi {

    private ProduitManager produitManager;

    @Autowired
    public ProduitsServiceApi(ProduitManager produitManager){
        this.produitManager = produitManager;
    }

    @CrossOrigin
    @GetMapping(path="/produits")
    public List<Produit> getAllProduct() throws Exception{
        //manage error
        return produitManager.getAllProduct();
    }

    @CrossOrigin
    @GetMapping(path="/rechercherProduitDuJour")
    public List<Produit> rechercherProduitDuJour() throws Exception{
        return produitManager.rechercherProduitDuJour();
    }

    @CrossOrigin
    @PostMapping(path="produits")
    @ResponseStatus(HttpStatus.CREATED)
    public Produit insertInstitution(@RequestBody Produit produit) throws Exception{
        return produitManager.save(produit);
    }

}
