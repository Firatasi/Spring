package com.demo.crud.controller;

import com.demo.crud.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    @GetMapping("/get-users")
    public List<User> getUsers() {
        return List.of(new User(1L,"zad"),
                new User(2L,"ahmet"));
    }

    @GetMapping("/get-user/{id}")
    public User getUser(@PathVariable Long id) {
        return new User(1L,"zad");
    }

    @PostMapping("/add-user")
    public String addUser(@RequestBody User user) {
        return user.toString();
    }

    @PutMapping("/update-user/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return user.toString();
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
            return deleteUser(id);
    }


}
