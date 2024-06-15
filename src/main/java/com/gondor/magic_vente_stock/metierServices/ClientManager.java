package com.gondor.magic_vente_stock.metierServices;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gondor.magic_vente_stock.repository.ClientRepo;
import com.gondor.magic_vente_stock.repository.DetailPanierRepo;
import com.gondor.magic_vente_stock.repository.PanierRepo;
import com.gondor.magic_vente_stock.repository.ProduitRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class ClientManager {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepo clientRepo;
    private final PanierRepo panierRepo;
    private final DetailPanierRepo detailPanierRepo;
    private final ProduitRepo produitRepo;



    public ClientManager(PasswordEncoder passwordEncoder, ClientRepo clientRepo, PanierRepo panierRepo, DetailPanierRepo detailPanierRepo, ProduitRepo produitRepo){
        this.passwordEncoder = passwordEncoder;
        this.clientRepo = clientRepo;
        this.panierRepo = panierRepo;
        this.detailPanierRepo = detailPanierRepo;
        this.produitRepo = produitRepo;
    }

    public Client save(Client a){
        a.setMotDePasse(passwordEncoder.encode(a.getMotDePasse()));
        return clientRepo.save(a);
    }

    public List<DetailPanier> getCurrentBasket(String bearerToken){
        Client client = getClientIdFromJWT(bearerToken);
        if(client != null){
            Panier panier = panierRepo.getCurrentBasket(client.getId());
            return detailPanierRepo.getListDetailCurrentBasket(panier.getId());
        }
        return null;
    }

    public DetailPanier addToBasket(String bearerToken, String idProduit, int quantite){
        Client client = getClientIdFromJWT(bearerToken);
        Optional<Produit> optionalProduit = produitRepo.findById(idProduit);
        if(optionalProduit.isPresent()){
            Produit produit = optionalProduit.get();
            Panier panier = panierRepo.getCurrentBasket(client.getId());
            DetailPanier detail = new DetailPanier(produit,quantite,panier);
            return detailPanierRepo.save(detail);
        }
        return null;
    }

    public void deleteFromBasket(String bearerToken, String idDetailProduit){
        Client client = getClientIdFromJWT(bearerToken);
        detailPanierRepo.deleteById(idDetailProduit);
    }

    //secret should be stored as env variable
    public Client getClientIdFromJWT(String bearerToken){
        String token = bearerToken.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String id = decodedJWT.getClaim("id").asString();
        Optional<Client> optionalClient = clientRepo.findById(id);
        if(optionalClient.isPresent()){
            return optionalClient.get();
        }
        return null;
    }

    public Produit getProduitById(String id) throws Exception{
        Optional<Produit> optionalProduit = produitRepo.findById(id);
        if(optionalProduit.isPresent()){
            Produit produit = optionalProduit.get();
            return produit;
        }
        throw new Exception("That product doesn't exist");
    }

}
