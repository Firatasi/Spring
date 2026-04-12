package com.demo.carapi.mapper;

import com.demo.carapi.dto.request.UserRequestDto;
import com.demo.carapi.dto.response.UserResponseDto;
import com.demo.carapi.entity.User;
import org.mapstruct.Mapper;


import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDto userRequestDto);
    UserResponseDto toUserResponseDto(User user);
    List<UserResponseDto> toUserResponseDto(List<User> userList);


}
