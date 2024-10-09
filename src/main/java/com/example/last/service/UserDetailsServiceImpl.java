package com.example.last.service;

 // Correct the casing in the package name
import com.example.last.Repository.UserRepository;
import com.example.last.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder; // Import UserBuilder
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Fetch user from the database using the email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Build and return UserDetails object
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
        userBuilder.password(user.getPassword()); // Ensure this matches how you store passwords
        userBuilder.authorities(new ArrayList<>()); // Add authorities if necessary

        // Optionally, you can add additional user attributes like account status (enabled, account non-expired, etc.)
        userBuilder.accountLocked(false);
        userBuilder.disabled(false);

        return userBuilder.build();
    }
}
