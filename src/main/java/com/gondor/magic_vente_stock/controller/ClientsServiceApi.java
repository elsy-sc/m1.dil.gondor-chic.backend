package com.gondor.magic_vente_stock.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gondor.magic_vente_stock.metierServices.*;
import com.gondor.magic_vente_stock.repository.ClientRepo;
import com.gondor.magic_vente_stock.repository.DetailPanierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    private ClientRepo clientRepo;

    @Autowired
    public ClientsServiceApi(ProduitManager produitManager, ClientManager clientManager, ClientRepo clientRepo){
        this.produitManager = produitManager;
        this.clientManager = clientManager;
        this.clientRepo = clientRepo;
    }

    @CrossOrigin
    @GetMapping(path="refresh_token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            try{
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //gotta persist that secret into database (used in authorization and authentication filter also)
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String login = decodedJWT.getSubject();
                Client client = clientRepo.findByPseudo(login);
                Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority("client"));
                String access_token = JWT.create()
                        .withSubject(client.getPseudo())
                        .withClaim("id", client.getId())
                        .withClaim("nom", client.getNom())
                        .withClaim("prenom", client.getPrenom())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }catch(Exception exception){
                response.setHeader("error",exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                //response.sendError(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @CrossOrigin
    @GetMapping(path="test")
    public String test() throws Exception {
        return "You're able to access client apis that require authentication and authorization!";
    }

    @CrossOrigin
    @GetMapping(path="panier")
    public List<DetailPanier> getCurrentBasket(@RequestHeader("Authorization") String token) throws Exception {
        return clientManager.getCurrentBasket(token);
    }

    @CrossOrigin
    @PostMapping(path="/panier")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addToBasket(@RequestHeader("Authorization") String token, @RequestParam("id_produit") String idProduit, @RequestParam("quantite") int quantite) throws Exception {
        Produit produit = clientManager.getProduitById(idProduit);
        if(produit.getQuantiteEnStock()>= quantite){
            DetailPanier detailPanier = clientManager.addToBasket(token, idProduit, quantite);
            return new ResponseEntity<>(detailPanier,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Quantité supérieure à la quantité en stock",HttpStatus.BAD_REQUEST);
    }

    @CrossOrigin
    @DeleteMapping(path="/panier")
    public void deleteFromBasket(@RequestHeader("Authorization") String token, @RequestParam("id_detail_panier") String idDetailPanier) throws Exception {
        clientManager.deleteFromBasket(token, idDetailPanier);
    }

}
