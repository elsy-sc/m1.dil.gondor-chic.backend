package com.gondor.magic_vente_stock.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gondor.magic_vente_stock.metierServices.Account;
import com.gondor.magic_vente_stock.metierServices.ClientManager;
import com.gondor.magic_vente_stock.metierServices.Produit;
import com.gondor.magic_vente_stock.metierServices.ProduitManager;
import com.gondor.magic_vente_stock.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path="api/client/")
public class ClientsServiceApi {

    private ProduitManager produitManager;
    private ClientManager clientManager;

    private AccountRepo accountRepo;

    @Autowired
    public ClientsServiceApi(ProduitManager produitManager, ClientManager clientManager, AccountRepo accountRepo){
        this.produitManager = produitManager;
        this.clientManager = clientManager;
        this.accountRepo = accountRepo;
    }

    @CrossOrigin
    @GetMapping(path="test")
    public String test() throws Exception {
        return "You're able to access client apis that require authentication and authorization!";
    }

}
