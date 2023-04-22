package com.draen.security.data.user.service;

import com.draen.exception.NotFoundException;
import com.draen.security.data.user.entity.User;
import com.draen.security.data.user.entity.UserRole;
import com.draen.security.data.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final TransactionTemplate transactionTemplate;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, TransactionTemplate transactionTemplate,
                           PasswordEncoder encoder) {
        this.repository = repository;
        this.transactionTemplate = transactionTemplate;
        this.encoder = encoder;
    }

    @Override
    public User findByUsername(String username) {
        return transactionTemplate.execute(status -> {
            return repository.findByUsername(username).orElseThrow(
                    () -> new NotFoundException("User not found")
            );
        });
    }

    @Override
    public User save(User user) {
        return transactionTemplate.execute(status -> {
            if (repository.existsByUsername(user.getUsername()))
                throw new RuntimeException(); //TODO
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.getRoles().add(UserRole.ABONENT);
            return repository.save(user);
        });
    }
}
