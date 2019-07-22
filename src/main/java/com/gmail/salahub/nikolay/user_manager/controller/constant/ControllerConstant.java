package com.gmail.salahub.nikolay.user_manager.controller.constant;

public class ControllerConstant {

    public static final String ADMINISTRATOR_ROLE_STRING_VALUE = "ADMINISTRATOR";
    public static final String USER_ROLE_STRING_VALUE = "USER";

    public static final String ACTIVE_STRING_VALUE = "ACTIVE";
    public static final String INACTIVE_STRING_VALUE = "INACTIVE";

    public static final String SHOW_USERS_ADMIN_URL = "/private/user";
    public static final String SHOW_USERS_USER_URL = "/public/user";

    public static final String REDIRECT_USER_URL = "redirect:/private/user";
    public static final String REDIRECT_SHOW_USER_URL = "redirect:/private/user/edit";

    private ControllerConstant() {
    }
}
