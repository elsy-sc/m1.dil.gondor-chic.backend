package com.gondor.magic_vente_stock.metierServices;

import com.gondor.magic_vente_stock.repository.ClientRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class AccountService {
    private ClientRepo clientRepo;


    @Autowired
    public AccountService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public UserDetails loadUserByUsernameAndType(String login, String accountType) throws UsernameNotFoundException {
        if ("client".equalsIgnoreCase(accountType)) {
            Client client = clientRepo.findByPseudo(login);
            if (client != null) {
                return buildUserDetails(client);
            }
        }/*else if ("employee".equalsIgnoreCase(accountType)) {
            Employee employee = employeeRepo.findByPseudo(login);
            if (employee != null) {
                return buildUserDetails(employee);
            }
        }*/
        throw new UsernameNotFoundException("User not found in the database");
    }

    private UserDetails buildUserDetails(Object user) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof Client) {
            authorities.add(new SimpleGrantedAuthority("client"));
            return new org.springframework.security.core.userdetails.User(((Client) user).getPseudo(), ((Client) user).getMotDePasse(), authorities);
        } /*else if (user instanceof Employee) {
            authorities.add(new SimpleGrantedAuthority(((Employee) user).getRole().getNom()));
            return new org.springframework.security.core.userdetails.User(((Employee) user).getPseudo(), ((Employee) user).getMotDePasse(), authorities);
        }*/
        throw new UsernameNotFoundException("User not found in the database");
    }

}
