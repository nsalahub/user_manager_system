package com.gmail.salahub.nikolay.user_manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
}
