package com.gmail.salahub.nikolay.user_manager.controller;

import com.gmail.salahub.nikolay.user_manager.controller.validator.UserValidator;
import com.gmail.salahub.nikolay.user_manager.service.UserService;
import com.gmail.salahub.nikolay.user_manager.service.model.UpdateUserDTO;
import com.gmail.salahub.nikolay.user_manager.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.gmail.salahub.nikolay.user_manager.controller.constant.ControllerConstant.REDIRECT_USER_URL;

@Controller
@RequestMapping("/private")
public class AdministratorController {

    private static final Logger logger = LoggerFactory.getLogger(AdministratorController.class);

    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AdministratorController(UserService userService, UserValidator userValidator) {
        this.userValidator = userValidator;
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
        return "useradmin";
    }

    @GetMapping("/user/new")
    public String showAddUserPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        logger.info("start showing add user page");
        return "adduserpage";
    }

    @PostMapping("/user/new")
    public String getUserFromAddUserPage(@ModelAttribute(value = "user") UserDTO userDTO,
                                         BindingResult bindingResult, Model model) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "adduserpage";
        } else {
            userService.create(userDTO);
            logger.info(userDTO.getFirstName(), userDTO.getUsername() + " success add");
            return REDIRECT_USER_URL;
        }
    }

    @PostMapping("/user/edit")
    public String updateUser(
            UpdateUserDTO updateUserDTO) {
        userService.updateStatus(updateUserDTO);
        logger.info(updateUserDTO.getUsername() + " role success changed");
        return REDIRECT_USER_URL;
    }

    @GetMapping("/user/edit")
    public String showThisArticleCustomer(
            @RequestParam(value = "user") Long userId,
            Model model) {
        UserDTO userDTO = userService.getById(userId);
        model.addAttribute("user", userDTO);
        logger.info("start showing more information about user with id " + userDTO.getId());
        return "showuseradmin";
    }

    @PostMapping("/user/edit/update")
    public String updateUser(
            @ModelAttribute(value = "user")
                    UserDTO userDTO,
            BindingResult bindingResult,
            Model model) {
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", userDTO);
            return "showuseradmin";
        } else {
            userService.update(userDTO);
            model.addAttribute("user", userDTO);
            return "showuseradmin";
        }
    }
}
