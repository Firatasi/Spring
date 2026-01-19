package com.demo.crud.controller;

import com.demo.crud.entity.User;
import com.demo.crud.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final DataSourceTransactionManager dataSourceTransactionManager;

    public UserController(UserService userService, DataSourceTransactionManager dataSourceTransactionManager) {
        this.userService = userService;
        this.dataSourceTransactionManager = dataSourceTransactionManager;
    }

    @PostMapping("/add")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); //eklendi kodu 201ok
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers =  userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id) {
        User s  =  userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(s);

    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) { //degisecek olan pathvariable , yeni değerler requestbody
        userService.updateUsers();
    }



}
