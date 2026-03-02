package com.demo.carapi.service;

import com.demo.carapi.dto.request.UserRequestDto;
import com.demo.carapi.dto.response.UserResponseDto;
import com.demo.carapi.entity.User;
import com.demo.carapi.mapper.UserMapper;
import com.demo.carapi.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        User user = userMapper.toUser(userRequestDto);
        User savedUser = userRepository.save(user);

        return userMapper.toUserResponseDto(savedUser);
    }

    public UserResponseDto getUserByID(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return userMapper.toUserResponseDto(user.get());
        }
        return null;
    }

    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userMapper.toUserResponseDto(userList);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        User updatedUser = userMapper.toUser(userRequestDto);
        User savedUser = userRepository.save(updatedUser);
        return userMapper.toUserResponseDto(savedUser);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

}
