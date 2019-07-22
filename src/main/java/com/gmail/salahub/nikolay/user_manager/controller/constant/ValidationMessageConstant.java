package com.gmail.salahub.nikolay.user_manager.controller.constant;

public class ValidationMessageConstant {

    public static final String VALIDATION_FIRST_NAME_USER_MESSAGE = "first name empty";
    public static final String VALIDATION_LAST_NAME_USER_MESSAGE = "last name empty";

    public static final String VALIDATION_USERNAME_USER_EXIST_MESSAGE = "username exist, try again";

    public static final String VALIDATION_USERNAME_LATIN_SYMBOL_INVALID_MESSAGE = "only latin symbol";

    public static final String VALIDATION_SYMBOL_PASSWORD_MESSAGE = "password incorrect";

    public static final String VALIDATION_LENGTH_USERNAME_VALUE = "username have incorrect length";
    public static final String VALIDATION_LENGTH_PASSWORD_MESSAGE = "password have incorrect length";
    public static final String VALIDATION_LENGTH_FIRST_NAME_MESSAGE = "first name have incorrect length";
    public static final String VALIDATION_LENGTH_LAST_NAME_MESSAGE = "last name have incorrect length";

    private ValidationMessageConstant() {
    }
}
