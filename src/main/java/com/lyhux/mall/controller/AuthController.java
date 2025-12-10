package com.lyhux.mall.controller;

import com.lyhux.mall.dto.LoginDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping(value = "login",
            consumes = APPLICATION_JSON_VALUE,
            produces= APPLICATION_JSON_VALUE
    )
    public LoginDTO login(@RequestBody LoginDTO loginDTO) {
        System.out.printf("loginDTO=%s", loginDTO);
        return loginDTO;
    }

    @GetMapping("login2")
    public String login1() {
        return "this api for username password login1";
    }
}
