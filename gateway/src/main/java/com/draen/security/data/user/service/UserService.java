package com.draen.security.data.user.service;

import com.draen.security.data.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByUsername(String username);
    User save(User user);
}
