package com.gondor.magic_vente_stock.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String accountType;

    public CustomAuthenticationToken(Object principal, Object credentials, String accountType) {
        super(principal, credentials);
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
}
