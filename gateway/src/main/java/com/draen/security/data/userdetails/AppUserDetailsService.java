package com.draen.security.data.userdetails;

import com.draen.exception.NotFoundException;
import com.draen.security.data.user.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public AppUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new AppUserDetails(userService.findByPhoneNumber(username));
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
