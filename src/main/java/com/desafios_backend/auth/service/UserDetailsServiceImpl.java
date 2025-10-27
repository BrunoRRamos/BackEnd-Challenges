package com.desafios_backend.auth.service;

import com.desafios_backend.auth.middleware.UserDetailsImpl;
import com.desafios_backend.auth.model.UserModel;
import com.desafios_backend.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDetailsImpl(user);
    }
}
