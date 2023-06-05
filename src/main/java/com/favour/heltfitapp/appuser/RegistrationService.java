package com.favour.heltfitapp.appuser;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegistrationService {
    private final AppUserRepository appUsers;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signupUser(Registration registration) {
        try {
            String result = appUsers.save(appUserFromRegistration(registration))
                    .getUsername() == registration.username() ? "onboarding" : "redirect:/signup";
            return result;
        } catch (Exception e) {
            log.error("signupUser() : {}",e.getCause());
            throw new UnsupportedOperationException("User signup failed because " + e.getCause());
        }
    }

    private AppUser appUserFromRegistration(Registration registration) {
        AppUser appUser = new AppUser(
                registration.username(),
                passwordEncoder.encode(registration.password()),
                registration.email(),
                registration.age(),
                registration.weight(),
                registration.history());

        return appUser;
    }
}
