package com.gondor.magic_vente_stock.controller;

import com.gondor.magic_vente_stock.metierServices.Produit;
import com.gondor.magic_vente_stock.metierServices.ProduitManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/client/")
public class ProduitApi {

    private ProduitManager produitManager;

    @Autowired
    public ProduitApi(ProduitManager produitManager){
        this.produitManager = produitManager;
    }

    @CrossOrigin
    @GetMapping(path="/produits")
    public Page<Produit> getAllProduct(@RequestParam("page") int page, @RequestParam("perPage") int perPage) throws Exception{
        //manage error
        return produitManager.getAllProduct(page,perPage);
    }

    @CrossOrigin
    @GetMapping(path="/rechercherProduitDuJour")
    public List<Produit> rechercherProduitDuJour() throws Exception{
        return produitManager.rechercherProduitDuJour();
    }

}
