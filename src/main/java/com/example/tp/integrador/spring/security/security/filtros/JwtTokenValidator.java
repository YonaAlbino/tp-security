package com.example.tp.integrador.spring.security.security.filtros;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.tp.integrador.spring.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtil jwtUtil;

    public JwtTokenValidator(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(jwtToken != null){
            jwtToken = jwtToken.substring(7);
            DecodedJWT decodedJWT = jwtUtil.validateToken(jwtToken);

            String userName = jwtUtil.extractUsername(decodedJWT);
            String authoritues = jwtUtil.getSpecifClaim(decodedJWT, "authorities").asString();

            Collection<? extends GrantedAuthority> authoritiList = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritues);

            SecurityContext context = SecurityContextHolder.getContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(userName, null, authoritiList);
            context.setAuthentication(authentication);
            SecurityContextHolder.setContext(context);
        }
        filterChain.doFilter(request,response);
    }
}
