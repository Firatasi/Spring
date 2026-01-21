package com.demo.yemekmasasi.controller;

import com.demo.yemekmasasi.dto.request.UserRequestDto;
import com.demo.yemekmasasi.dto.response.UserResponseDto;
import com.demo.yemekmasasi.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("/yemekmasasi")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add-user")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto response = userService.saveUser(userRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUserByID(id));
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id ,
                                                      @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<UserResponseDto> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
