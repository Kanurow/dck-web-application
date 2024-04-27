package com.rowland.engineering.dck.service;

import com.rowland.engineering.dck.model.User;
import com.rowland.engineering.dck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
