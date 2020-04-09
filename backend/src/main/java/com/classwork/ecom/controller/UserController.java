package com.classwork.ecom.controller;

import com.classwork.ecom.entity.User;
import com.classwork.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity createUser(@Valid @RequestBody User newUser) {
        User user = userService.createUser(newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@Valid @RequestBody User newUser) {
        if (newUser.getId() != null) {
            userService.updateUser(newUser);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            User user = userService.createUser(newUser);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(user.getId()).toUri();
            return ResponseEntity.created(location).build();
        }
    }

    @GetMapping("/")
    public Page<User> getUsers(Pageable pageable, @RequestParam Optional<String> firstName, @RequestParam Optional<String> lastName) {
        return userService.getUsers(pageable, firstName, lastName);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/delete")
    public void deleteUsers() {
        userService.deleteUsers();
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteUserById(@PathVariable int userId) {
        userService.deleteUserById(userId);
    }
}
