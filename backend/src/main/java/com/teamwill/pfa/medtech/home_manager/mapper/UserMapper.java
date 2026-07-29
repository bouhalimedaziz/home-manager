package com.teamwill.pfa.medtech.home_manager.mapper;

import com.teamwill.pfa.medtech.home_manager.dto.UserDto;
import com.teamwill.pfa.medtech.home_manager.entity.Role;
import com.teamwill.pfa.medtech.home_manager.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .roles(user.getRoles().stream().map(Enum::name).collect(Collectors.toList()))
                .build();
    }

    public static User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phoneNumber(userDto.getPhoneNumber())
                .roles(userDto.getRoles().stream().map(Role::valueOf).collect(Collectors.toList()))
                .build();
    }
}
