package com.demo.crud.service;

import com.demo.crud.entity.User;
import com.demo.crud.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
    return userRepository.findAll();
    }
    
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User deleteById(Long id) {
        return deleteById(id);
    }

    public User updateUser(Long id, User user) {
        User userFind = userRepository.findById(id).orElse(null);
        user.setName(userFind.getName());
        userRepository.save(userFind);
        return user;
    }


}
