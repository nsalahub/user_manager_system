package com.gmail.salahub.nikolay.user_manager.service.model;

import javax.validation.constraints.NotNull;

public class UpdateUserDTO {
    @NotNull
    private String username;
    @NotNull
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
