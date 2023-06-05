package com.favour.heltfitapp.appuser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    public Optional<AppUser> findByUsername(String username); 
    public Optional<AppUser> findByEmail(String email);
}
