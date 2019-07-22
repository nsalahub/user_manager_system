package com.gmail.salahub.nikolay.user_manager.service;

import com.gmail.salahub.nikolay.user_manager.service.model.UpdateUserDTO;
import com.gmail.salahub.nikolay.user_manager.service.model.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getByUsername(String username);

    List<UserDTO> getByPageNumber(int pageNumber);

    int getNumberOfPages();

    void create(UserDTO userDTO);

    UserDTO getById(Long userId);

    void updateStatus(UpdateUserDTO updateUserDTO);

    void update(UserDTO userDTO);
}
