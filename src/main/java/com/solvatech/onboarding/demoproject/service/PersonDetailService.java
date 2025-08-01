package com.solvatech.onboarding.demoproject.service;

import com.solvatech.onboarding.demoproject.model.User;
import com.solvatech.onboarding.demoproject.repository.UserRepository;
import com.solvatech.onboarding.demoproject.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public PersonDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new PersonDetails(user.get());
    }
}
