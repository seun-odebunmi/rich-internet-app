package com.classwork.ecom.service;

import com.classwork.ecom.entity.User;
import com.classwork.ecom.exceptions.ItemNotFoundException;
import com.classwork.ecom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateUser(User newUser) {
        Optional<User> foundUser = userRepository.findById(newUser.getId());
        if (foundUser.isPresent()) {
            return userRepository.save(newUser);
        } else {
            throw new ItemNotFoundException("Unable to find user with id: " + newUser.getId());
        }
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User getUserById(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        if (foundUser.isPresent()) {
            return foundUser.get();
        } else {
            throw new ItemNotFoundException("Unable to find user with id: " + id);
        }
    }

    public long usersCount() {
        return userRepository.count();
    }

    public void deleteUsers() {
        try {
            userRepository.deleteAll();
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to delete users");
        }
    }

    public void deleteUserById(long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ItemNotFoundException("Unable to delete user with id: " + id);
        }
    }
}
