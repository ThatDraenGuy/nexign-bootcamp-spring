package com.draen.security.data.user.repository;

import com.draen.security.data.user.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByClient_PhoneNumber(String phoneNumber);
    boolean existsByClient_PhoneNumber(String phoneNumber);
}
