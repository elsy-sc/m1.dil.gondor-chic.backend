package com.gondor.magic_vente_stock.metierServices;

import com.gondor.magic_vente_stock.repository.ClientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class ClientManager {

    private final PasswordEncoder passwordEncoder;

    private final ClientRepo clientRepo;

    public ClientManager(PasswordEncoder passwordEncoder, ClientRepo clientRepo){
        this.passwordEncoder = passwordEncoder;
        this.clientRepo = clientRepo;
    }

    //just for testing purpose
    public Client save(Client a){
        /*Role r = roleRepo.findByNom("client");
        a.setRole(r);*/
        a.setMotDePasse(passwordEncoder.encode(a.getMotDePasse()));
        return clientRepo.save(a);
    }

}
