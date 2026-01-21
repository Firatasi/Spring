package com.demo.yemekmasasi.mapper;

import com.demo.yemekmasasi.dto.request.UserRequestDto;
import com.demo.yemekmasasi.dto.response.UserResponseDto;
import com.demo.yemekmasasi.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserRequestDto userRequestDto);
    UserResponseDto toUserResponseDto(User user);
    List<UserResponseDto> toUserResponseDto(List<User> userList);


}
