package com.tickettoride.tickettoride.service;

import com.tickettoride.tickettoride.entity.Traveller;
import com.tickettoride.tickettoride.repository.TravellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Custom user details service that loads user-specific data.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private TravellerRepository travellerRepository;

    /**
     * Loads the user by username (email).
     *
     * @param email the email identifying the user whose data is required.
     * @return a fully populated user record.
     * @throws UsernameNotFoundException if the user could not be found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Traveller traveller = travellerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Traveller not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                traveller.getEmail(), traveller.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
