package com.lyhux.mall.controller;

import com.lyhux.mall.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class IndexController {

    // @Autowired
    // private RedisTemplate<String, Object> redisTemplate;

    public static class Book {

        private String number;
        private String title;

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }

    @GetMapping("/index")
    public String index() {
        return "index page";
    }


    // @GetMapping("/redis/set")
    // public String redisSet() {
    //     Book book = new Book();
    //     book.setNumber("controller no.0001");
    //     book.setTitle("hello world");
    //
    //     redisTemplate.opsForValue().set("c_book01", book);
    //
    //     return "redis user set success!";
    // }

    @PostMapping("/hello")
    public String hello(@Validated @RequestBody  UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 处理校验失败
            Map<String, String> errorMap = new LinkedHashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });

            System.out.printf("has errors use dto: %s\n", errorMap.entrySet());
        }
        System.out.printf("use dto: %s, %s, %s\n", userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
        return "200";
    }

}
