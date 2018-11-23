package com.estudo.authserver.config.oauth;

import com.estudo.authserver.usuarios.Usuario;
import com.estudo.authserver.usuarios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Usuario> user = usuarioRepository.findOneByLogin(s);

        return user.map(ResourceOwner::new).orElseThrow(() -> new UsernameNotFoundException("Usuário não autorizado"));
    }
}
