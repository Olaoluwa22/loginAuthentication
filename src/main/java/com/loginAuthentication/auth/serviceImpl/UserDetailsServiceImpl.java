package com.loginAuthentication.auth.serviceImpl;

import com.loginAuthentication.auth.model.User;
import com.loginAuthentication.auth.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
            if (user == null){
                throw new UsernameNotFoundException("Username not found...");
            }
        return new UserDetailsImpl(user);
    }
}