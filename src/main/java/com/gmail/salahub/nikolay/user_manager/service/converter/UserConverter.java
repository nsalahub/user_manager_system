package com.gmail.salahub.nikolay.user_manager.service.converter;

import com.gmail.salahub.nikolay.user_manager.repository.model.User;
import com.gmail.salahub.nikolay.user_manager.service.model.UserDTO;

public interface UserConverter {
    UserDTO toDTO(User user);

    User fromDTO(UserDTO userDTO);
}
