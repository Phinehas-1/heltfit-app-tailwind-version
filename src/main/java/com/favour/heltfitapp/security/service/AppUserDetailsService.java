package com.favour.heltfitapp.security.service;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.favour.heltfitapp.appuser.AppUser;
import com.favour.heltfitapp.appuser.AppUserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUsers;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appuser = appUsers.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException(username + " not found.");
                });
        return new User(appuser.getUsername(), appuser.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("user")));
    }
}
