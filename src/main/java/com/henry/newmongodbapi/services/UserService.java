package com.henry.newmongodbapi.services;

import com.henry.newmongodbapi.models.User;
import com.henry.newmongodbapi.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public User insert(User user) {
        return userRepository.save(user);
    }

    public User update(User user, String username) {
        Optional<User> currentUser = userRepository.findByUsername(username);
        if (currentUser.isPresent()) {
            User otherUser = currentUser.get();
            otherUser.setPassword(user.getPassword());
            otherUser.setStatus(user.getStatus());
            return userRepository.save(otherUser);
        } else {
            throw new NoSuchElementException("User credentials with this username: " + username + " not found!");
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Can't find any user with this username!")
        );
    }
}