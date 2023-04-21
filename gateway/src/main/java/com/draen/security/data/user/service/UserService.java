package com.draen.security.data.user.service;

import com.draen.security.data.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findByPhoneNumber(String phoneNumber);
    User save(User user);
}
