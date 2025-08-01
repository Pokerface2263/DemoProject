package com.solvatech.onboarding.demoproject.security;

import com.solvatech.onboarding.demoproject.service.PersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final PersonDetailService userDetailServiceImpl;

    @Autowired
    public AuthProviderImpl(PersonDetailService userDetailServiceImpl) {
        this.userDetailServiceImpl = userDetailServiceImpl;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        UserDetails personDetails = userDetailServiceImpl.loadUserByUsername(username);
        String password = authentication.getCredentials().toString();

        if(!password.equals(personDetails.getPassword()))
            throw new BadCredentialsException("Wrong password");
        return new UsernamePasswordAuthenticationToken(personDetails, password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
