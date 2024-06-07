package com.gondor.magic_vente_stock.controller;

import com.gondor.magic_vente_stock.model.Produit;
import com.gondor.magic_vente_stock.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/client/")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @CrossOrigin
    @GetMapping(path="/products")
    public Page<Produit> getAllProduct(@RequestParam("page") int page, @RequestParam("perPage") int perPage) throws Exception{
        //manage error
        return clientService.getAllProduct(page,perPage);
    }

    @CrossOrigin
    @GetMapping(path="/productsOfTheDay")
    public List<Produit> getAllProductOfTheDay() throws Exception{
        return clientService.getAllProductOfTheDay();
    }

}
