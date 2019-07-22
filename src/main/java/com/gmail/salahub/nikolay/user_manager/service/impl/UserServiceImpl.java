package com.gmail.salahub.nikolay.user_manager.service.impl;

import com.gmail.salahub.nikolay.user_manager.repository.UserRepository;
import com.gmail.salahub.nikolay.user_manager.repository.model.Status;
import com.gmail.salahub.nikolay.user_manager.repository.model.User;
import com.gmail.salahub.nikolay.user_manager.service.PageService;
import com.gmail.salahub.nikolay.user_manager.service.UserService;
import com.gmail.salahub.nikolay.user_manager.service.converter.UserConverter;
import com.gmail.salahub.nikolay.user_manager.service.exception.NoResultUserServiceException;
import com.gmail.salahub.nikolay.user_manager.service.model.UpdateUserDTO;
import com.gmail.salahub.nikolay.user_manager.service.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.gmail.salahub.nikolay.user_manager.service.constant.ServiceConstant.LIMIT_USER_VALUE;

@Service("userService")
public class UserServiceImpl implements UserService {
    private static final String NO_RESULT_SERVICE_EXCEPTION_MESSAGE = "User with this username no exist";

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PageService pageService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           UserConverter userConverter,
                           PageService pageService,
                           PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.pageService = pageService;
        this.userConverter = userConverter;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return userConverter.toDTO(user);
        } else {
            throw new NoResultUserServiceException(NO_RESULT_SERVICE_EXCEPTION_MESSAGE + username);
        }
    }

    @Override
    @Transactional
    public List<UserDTO> getByPageNumber(int pageNumber) {
        List<UserDTO> userDTOS;
        List<User> users = userRepository.findAll(pageService
                .getLimitValue(LIMIT_USER_VALUE, pageNumber), LIMIT_USER_VALUE);
        userDTOS = users.stream()
                .map(userConverter::toDTO)
                .collect(Collectors.toList());
        return userDTOS;
    }

    @Override
    @Transactional
    public int getNumberOfPages() {
        Integer valueOfUsers = userRepository.getCountOfEntities();
        Integer valueOfPages = pageService.getValueOfPages(valueOfUsers, LIMIT_USER_VALUE);
        return valueOfPages;
    }

    @Override
    @Transactional
    public void create(UserDTO userDTO) {
        userDTO.setCreatedDate(new Date());
        User user = userConverter.fromDTO(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.persist(user);
    }

    @Override
    @Transactional
    public UserDTO getById(Long userId) {
        User user = userRepository.findById(userId);
        return userConverter.toDTO(user);
    }

    @Override
    @Transactional
    public void updateStatus(UpdateUserDTO updateUserDTO) {
        User user = userRepository.findByUsername(updateUserDTO.getUsername());
        user.setStatus(Status.valueOf(updateUserDTO.getStatus()));
        userRepository.merge(user);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        System.out.println(userDTO.getLastName());
        System.out.println(userDTO.getFirstName());
        System.out.println(userDTO.getRole());
        User user = userRepository.findByUsername(userDTO.getUsername());
        System.out.println(user.getUsername());
        user.setLastName(userDTO.getLastName());
        user.setFirstName(userDTO.getFirstName());
        user.setRole(userDTO.getRole());
        userRepository.merge(user);
    }
}
