package com.gondor.magic_vente_stock.metierServices;

import com.gondor.magic_vente_stock.repository.AccountRepo;
import com.gondor.magic_vente_stock.repository.RoleRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private RoleRepo roleRepo;

    private AccountRepo accountRepo;


    @Autowired
    public AccountService(PasswordEncoder passwordEncoder, RoleRepo roleRepo, AccountRepo accountRepo) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.accountRepo = accountRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account user = accountRepo.findByUsername(login);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database : {}",login);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getNom()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword() ,authorities);
    }
}
