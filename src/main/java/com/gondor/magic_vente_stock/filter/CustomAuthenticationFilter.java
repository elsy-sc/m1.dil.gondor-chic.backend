package com.gondor.magic_vente_stock.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gondor.magic_vente_stock.metierServices.AccountService;
import com.gondor.magic_vente_stock.metierServices.Client;
import com.gondor.magic_vente_stock.repository.ClientRepo;
import com.gondor.magic_vente_stock.security.CustomAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final ClientRepo clientRepo;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, ClientRepo clientRepo) {
        this.authenticationManager = authenticationManager;
        this.clientRepo = clientRepo;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");
        String accountType = request.getParameter("role");

        log.info("Username: {}", login);
        log.info("Password: {}", password);
        log.info("AccountType: {}", accountType);

        if (accountType == null || (!accountType.equalsIgnoreCase("client") && !accountType.equalsIgnoreCase("employee"))) {
            throw new AuthenticationException("Account type must be either client or employee") {};
        }

        // Create the initial authentication token with username and password
        CustomAuthenticationToken authenticationToken = new CustomAuthenticationToken(login, password, accountType);

        // Authenticate the token
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        return authentication;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User)authentication.getPrincipal();
        String role = request.getParameter("role");
        String nom = "";
        String prenom = "";
        String id = null;
        if(role.equalsIgnoreCase("client")){
            Client client = clientRepo.findByPseudo(user.getUsername());
            id = client.getId();
            nom = client.getNom();
            prenom = client.getPrenom();
        }

        //else if role is other condition here
        // -----------------

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //gotta change it to a secret in the database
        String access_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("id", id)
                .withClaim("nom",nom)
                .withClaim("prenom", prenom)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


        String refresh_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("id", id)
                .withClaim("nom",nom)
                .withClaim("prenom", prenom)
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);

        //response.setHeader("access_token",access_token);
        //response.setHeader("refresh_token",refresh_token);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);

    }
}
