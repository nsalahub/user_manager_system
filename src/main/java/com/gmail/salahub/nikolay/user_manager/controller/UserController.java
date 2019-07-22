package com.gmail.salahub.nikolay.user_manager.controller;

import com.gmail.salahub.nikolay.user_manager.service.UserService;
import com.gmail.salahub.nikolay.user_manager.service.model.UpdateUserDTO;
import com.gmail.salahub.nikolay.user_manager.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/public")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user")
    private String showUsers(@RequestParam(defaultValue = "1", value = "currentPage")
                                     Integer page, Model model) {
        List<UserDTO> users = userService.getByPageNumber(page);
        model.addAttribute("users", users);
        int valueOfPages = userService.getNumberOfPages();
        model.addAttribute("numberPage", valueOfPages);
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        model.addAttribute("updateUserDTO", updateUserDTO);
        logger.info("start showing page with users");
        return "user";
    }

    @GetMapping("/user/about")
    public String showThisArticleCustomer(
            @RequestParam(value = "user") Long userId,
            Model model) {
        UserDTO userDTO = userService.getById(userId);
        model.addAttribute("user", userDTO);
        logger.info("start showing more information about user with id " + userDTO.getId());
        return "showuser";
    }
}
