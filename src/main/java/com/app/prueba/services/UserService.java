package com.app.prueba.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.prueba.models.Cards;
import com.app.prueba.models.User;
import com.app.prueba.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        if (!(id instanceof Integer)) {
            return null;
        } else if (id < 0) {
            return null;
        } else if (id == 0) {
            return null;
        }

        if (userRepository.existsById(id)) {
            return userRepository.findById(id).get();

        }
        return null;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public List<Cards> findCardsByUserId(int userId) {
        return userRepository.findCardsByUserId(userId);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByEmailAndPassword(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }
}
