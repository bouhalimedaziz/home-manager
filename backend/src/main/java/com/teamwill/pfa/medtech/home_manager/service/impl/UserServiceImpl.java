package com.teamwill.pfa.medtech.home_manager.service.impl;

import com.teamwill.pfa.medtech.home_manager.dto.UserDto;
import com.teamwill.pfa.medtech.home_manager.entity.User;
import com.teamwill.pfa.medtech.home_manager.exception.ResourceNotFoundException;
import com.teamwill.pfa.medtech.home_manager.mapper.UserMapper;
import com.teamwill.pfa.medtech.home_manager.repository.UserRepository;
import com.teamwill.pfa.medtech.home_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User saved = userRepository.save(user);
        return UserMapper.mapToUserDto(saved);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existing.setName(userDto.getName());
        existing.setEmail(userDto.getEmail());
        existing.setPhoneNumber(userDto.getPhoneNumber());
        existing.setRoles(UserMapper.mapToUser(userDto).getRoles());

        User updated = userRepository.save(existing);
        return UserMapper.mapToUserDto(updated);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
