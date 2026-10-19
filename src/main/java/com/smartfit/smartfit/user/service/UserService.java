
package com.smartfit.smartfit.user.service;

import com.smartfit.smartfit.user.dto.RegisterRequest;
import com.smartfit.smartfit.user.entity.User;
import com.smartfit.smartfit.user.repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public User registerUser(RegisterRequest request) {

        String normalizedEmail = request.getEmail()
                .trim()
                .toLowerCase();

        if (userRepository.existsByEmail(normalizedEmail)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email is already registered"
            );
        }

        User user = new User();

        user.setFirstName(request.getFirstName().trim());
        user.setLastName(request.getLastName().trim());
        user.setEmail(normalizedEmail);

        String hashedPassword = passwordEncoder.encode(
                request.getPassword()
        );

        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }
}