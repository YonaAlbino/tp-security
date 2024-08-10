package com.example.tp.integrador.spring.security.service;

import com.example.tp.integrador.spring.security.dto.AuthLoguinRequestDTO;
import com.example.tp.integrador.spring.security.dto.AuthLoguinResponseDTO;
import com.example.tp.integrador.spring.security.model.Usuario;
import com.example.tp.integrador.spring.security.repository.IUsuarioRepository;
import com.example.tp.integrador.spring.security.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUsuarioRepository usuarioRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findUserEntityByusername(username).orElseThrow(
                () -> new UsernameNotFoundException("El usuario " +username+ " no fue encontrado"));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        usuario.getListaRoles()
                .stream()
                .forEach(roles -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(roles.getNombreRol()))));

        usuario.getListaRoles().stream()
                .flatMap(role -> role.getListaPermiso().stream())
                .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getNombrePermiso())));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.isEnable(),
                usuario.isAccountNotExpired(),
                usuario.isAccountNotLocked(),
                usuario.isCredentialNotExpired(),
                authorityList);
    }

    public Authentication authenticate(String username, String password){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null)
            throw new BadCredentialsException("Nombre de usuario incorrecto");

        if(!passwordEncoder.matches(password, userDetails.getPassword()))
            throw new BadCredentialsException("Contraseña incorrecta");

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    public Authentication authenticate(String username){
        UserDetails userDetails = this.loadUserByUsername(username);

        if(userDetails == null) {
            throw new BadCredentialsException("Nombre de usuario incorrecto");
        }

        return new UsernamePasswordAuthenticationToken(username, userDetails.getPassword(), userDetails.getAuthorities());
    }

    public AuthLoguinResponseDTO loguin (AuthLoguinRequestDTO authLoguinRequestDTO){
        String username = authLoguinRequestDTO.nombreUsuario();
        String password = authLoguinRequestDTO.contrasenia();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtil.createToken(authentication);
        AuthLoguinResponseDTO authLoguinResponseDTO = new AuthLoguinResponseDTO(username,"Loguin correcto", token, true);
        return authLoguinResponseDTO;
    }

}
