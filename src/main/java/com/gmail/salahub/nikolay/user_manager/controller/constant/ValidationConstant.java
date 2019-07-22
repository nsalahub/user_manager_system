package com.gmail.salahub.nikolay.user_manager.controller.constant;

public class ValidationConstant {
    public static final String ONLY_LATIN_SYMBOLS_AND_DIGITS = "^[a-zA-Z0-9\\\\s]+$";

    public static final int MIN_SIZE_USERNAME_VALUE = 3;
    public static final int MAX_SIZE_USERNAME_VALUE = 16;
    public static final int MIN_SIZE_FIRST_NAME_VALUE = 1;
    public static final int MAX_SIZE_FIRST_NAME_VALUE = 16;
    public static final int MIN_SIZE_LAST_NAME_VALUE = 1;
    public static final int MAX_SIZE_LAST_NAME_VALUE = 16;
    public static final int MIN_SIZE_PASSWORD_VALUE = 3;
    public static final int MAX_SIZE_PASSWORD_VALUE = 16;

    private ValidationConstant() {
    }
}
