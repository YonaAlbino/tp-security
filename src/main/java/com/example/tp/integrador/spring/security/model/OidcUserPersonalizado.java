package com.example.tp.integrador.spring.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class OidcUserPersonalizado implements OidcUser {

    private OidcUser OdiUserOriginal;
    private String tokenInterno;

    public OidcUserPersonalizado(OidcUser odiUserOriginal, String tokenInterno){
        this.OdiUserOriginal = odiUserOriginal;
        this.tokenInterno = tokenInterno;
    }

    @Override
    public Map<String, Object> getClaims() {
        return OdiUserOriginal.getClaims();
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return OdiUserOriginal.getUserInfo();
    }

    @Override
    public OidcIdToken getIdToken() {
        return OdiUserOriginal.getIdToken();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return OdiUserOriginal.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return OdiUserOriginal.getAuthorities();
    }

    @Override
    public String getName() {
        return OdiUserOriginal.getName();
    }

    public String getTokenInterno(){
        return tokenInterno;
    }
}
