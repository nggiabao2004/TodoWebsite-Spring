package com.nggiabao2004.todo_website.service;

import com.nggiabao2004.todo_website.entity.User;
import com.nggiabao2004.todo_website.entity.UserPrincipal;
import com.nggiabao2004.todo_website.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("Username not found");
            throw new UsernameNotFoundException("Username not found");
    }
        return new UserPrincipal(user);
    }

    public Long getUserIdByUsername(String username){
        User user = userRepository.findByUsername(username);
        return user != null ? user.getId() : null;
    }
}
