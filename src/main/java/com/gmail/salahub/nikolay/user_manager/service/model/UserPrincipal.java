package com.gmail.salahub.nikolay.user_manager.service.model;

import com.gmail.salahub.nikolay.user_manager.repository.model.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static com.gmail.salahub.nikolay.user_manager.controller.constant.ControllerConstant.ACTIVE_STRING_VALUE;

public class UserPrincipal implements UserDetails {

    private UserDTO userDTO;
    private Set<GrantedAuthority> grantedAuthorities;

    public UserPrincipal(UserDTO userDTO) {
        this.userDTO = userDTO;
        this.grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(userDTO.getRole().toString()));
    }

    public Long getIdFromUserPrincipal() {
        return userDTO.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (userDTO.getStatus().toString().equals(ACTIVE_STRING_VALUE)) {
            return true;
        } else {
            return false;
        }
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
