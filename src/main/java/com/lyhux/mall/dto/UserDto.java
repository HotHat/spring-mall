package com.lyhux.mall.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    @NotNull(message = "用户不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "邮箱不能为空")
    @Email(message = "邮箱地址格式错误")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
