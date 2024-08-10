package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.model.OidcUserPersonalizado;
import com.example.tp.integrador.spring.security.model.Usuario;
import com.example.tp.integrador.spring.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private IUsuarioService usuarioUservice;

    @Autowired
    @Lazy
    private UserDetailsServiceImp userDetails;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String jwtToken;
        String email = oidcUser.getEmail();
        Authentication authentication = null;

        Optional<Usuario> usuario = usuarioUservice.findUserEntityByusername(email);

        if(usuario.isEmpty()){
            Usuario nuevoUsuario = usuarioUservice.saveUserOauth(email);
            authentication = userDetails.authenticate(nuevoUsuario.getUsername());
        }else{
            authentication = userDetails.authenticate(usuario.get().getUsername());
        }
        jwtToken = jwtUtil.createToken(authentication);
        //System.out.println(oidcUser.getEmail());
        //System.out.println(oidcUser.getGivenName());
        //System.out.println(oidcUser.getFamilyName());
        //System.out.println(oidcUser.getPicture());
        return new OidcUserPersonalizado(oidcUser, jwtToken);
    }

}
