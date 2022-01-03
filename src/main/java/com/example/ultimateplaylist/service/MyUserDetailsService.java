package com.example.ultimateplaylist.service;
import com.example.ultimateplaylist.model.User;
import com.example.ultimateplaylist.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private UserService userService;

    public MyUserDetailsService() {
    }

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findUserByEmailAddress(email);
        return new MyUserDetails(user);
    }
}