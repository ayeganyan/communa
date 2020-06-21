package com.communa.server.service;

import com.communa.server.auth.CommunaPrincipal;
import com.communa.server.entity.ResidentEntity;
import com.communa.server.repository.ResidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ResidentUserDetailsService implements UserDetailsService {

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ResidentEntity residentEntity = residentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find resident with email" + email));

        return new CommunaPrincipal(residentEntity);
    }
}
