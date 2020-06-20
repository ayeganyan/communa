package com.communa.server.auth;

import com.communa.server.entity.Resident;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CommunaPrincipal implements UserDetails {

    private final Resident resident;


    public CommunaPrincipal(Resident resident) {
        super();
        this.resident = resident;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.resident.getPassword();
    }

    @Override
    public String getUsername() {
        return this.resident.getEmail();
    }

    public Resident getResident() {
        return resident;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
