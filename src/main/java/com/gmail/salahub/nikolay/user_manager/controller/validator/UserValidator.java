package com.gmail.salahub.nikolay.user_manager.controller.validator;

import com.gmail.salahub.nikolay.user_manager.service.UserService;
import com.gmail.salahub.nikolay.user_manager.service.exception.NoResultUserServiceException;
import com.gmail.salahub.nikolay.user_manager.service.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MAX_SIZE_FIRST_NAME_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MAX_SIZE_LAST_NAME_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MAX_SIZE_PASSWORD_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MAX_SIZE_USERNAME_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MIN_SIZE_FIRST_NAME_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MIN_SIZE_LAST_NAME_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MIN_SIZE_PASSWORD_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.MIN_SIZE_USERNAME_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationConstant.ONLY_LATIN_SYMBOLS_AND_DIGITS;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_FIRST_NAME_USER_MESSAGE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_LAST_NAME_USER_MESSAGE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_LENGTH_FIRST_NAME_MESSAGE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_LENGTH_LAST_NAME_MESSAGE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_LENGTH_PASSWORD_MESSAGE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_LENGTH_USERNAME_VALUE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_SYMBOL_PASSWORD_MESSAGE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_USERNAME_LATIN_SYMBOL_INVALID_MESSAGE;
import static com.gmail.salahub.nikolay.user_manager.controller.constant.ValidationMessageConstant.VALIDATION_USERNAME_USER_EXIST_MESSAGE;

@Component("userValidator")
public class UserValidator implements Validator {
    private static final Logger logger = LoggerFactory.getLogger(UserValidator.class);

    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "",
                VALIDATION_FIRST_NAME_USER_MESSAGE);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "",
                VALIDATION_LAST_NAME_USER_MESSAGE);

        UserDTO userDTO = (UserDTO) o;

        validateEmail(userDTO, errors);
        validatePassword(userDTO, errors);
        validateFirstName(userDTO,errors);
        validateLastName(userDTO,errors);
    }

    private void validateEmail(UserDTO userDTO, Errors errors) {
        Pattern pattern;
        pattern = Pattern.compile(ONLY_LATIN_SYMBOLS_AND_DIGITS,
                Pattern.CASE_INSENSITIVE);
        if (!(pattern.matcher(userDTO.getUsername()).matches())) {
            errors.rejectValue("username", "", VALIDATION_USERNAME_LATIN_SYMBOL_INVALID_MESSAGE);
        }

        try {
            userService.getByUsername(userDTO.getUsername());
            errors.rejectValue("username", "", VALIDATION_USERNAME_USER_EXIST_MESSAGE);
        } catch (NoResultUserServiceException e) {
            logger.info(userDTO.getUsername() + " this email no exist in database");
        }

        if (userDTO.getUsername().length() < MIN_SIZE_USERNAME_VALUE |
                userDTO.getUsername().length() > MAX_SIZE_USERNAME_VALUE) {
            errors.rejectValue("username", "", VALIDATION_LENGTH_USERNAME_VALUE);
        }
    }

    private void validatePassword(UserDTO userDTO, Errors errors) {
        Pattern pattern;
        pattern = Pattern.compile(ONLY_LATIN_SYMBOLS_AND_DIGITS, Pattern.CASE_INSENSITIVE);
        if (!pattern.matcher(userDTO.getPassword()).matches()) {
            errors.rejectValue("password", "", VALIDATION_SYMBOL_PASSWORD_MESSAGE);
        }

        if (userDTO.getPassword().length() < MIN_SIZE_PASSWORD_VALUE |
                userDTO.getPassword().length() > MAX_SIZE_PASSWORD_VALUE){
            errors.rejectValue("username", "", VALIDATION_LENGTH_PASSWORD_MESSAGE);
        }
    }

    private void validateFirstName(UserDTO userDTO ,Errors errors){
        if (userDTO.getFirstName().length() < MIN_SIZE_FIRST_NAME_VALUE |
                userDTO.getLastName().length() > MAX_SIZE_FIRST_NAME_VALUE){
            errors.rejectValue("username", "", VALIDATION_LENGTH_FIRST_NAME_MESSAGE);
        }
    }

    private void validateLastName(UserDTO userDTO ,Errors errors){
        if (userDTO.getLastName().length() < MIN_SIZE_LAST_NAME_VALUE |
                userDTO.getLastName().length() > MAX_SIZE_LAST_NAME_VALUE){
            errors.rejectValue("username", "", VALIDATION_LENGTH_LAST_NAME_MESSAGE);
        }
    }
}
