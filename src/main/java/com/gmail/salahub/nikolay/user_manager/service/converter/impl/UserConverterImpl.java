package com.gmail.salahub.nikolay.user_manager.service.converter.impl;

import com.gmail.salahub.nikolay.user_manager.repository.model.User;
import com.gmail.salahub.nikolay.user_manager.service.converter.UserConverter;
import com.gmail.salahub.nikolay.user_manager.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("userConverter")
public class UserConverterImpl implements UserConverter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserConverterImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setStatus(user.getStatus());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setRole(user.getRole());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedDate(user.getCreatedDate());
        userDTO.setDeleted(user.isDeleted());
        return userDTO;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setCreatedDate(userDTO.getCreatedDate());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setStatus(userDTO.getStatus());
        user.setRole(userDTO.getRole());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setDeleted(userDTO.isDeleted());
        return user;
    }
}
