package com.packtpub.onlineauction.service.security;

import com.packtpub.onlineauction.entity.User;
import com.packtpub.onlineauction.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User authentication = userRepository.findByUsername(username)
                .orElseThrow(() -> new
                        UsernameNotFoundException("User not found with username: " + username));

        return new UserDetailsCustom(
                authentication.getUsername(),
                authentication.getPassword(),
                authentication.getRoles().stream()
                        .map(r -> new SimpleGrantedAuthority(r.getName()))
                        .collect(Collectors.toList()),
                authentication.isEnabled(),
                authentication.isAccountNonExpired(),
                authentication.isAccountNonLocked(),
                authentication.isCredentialsNonExpired()
        );

    }
}