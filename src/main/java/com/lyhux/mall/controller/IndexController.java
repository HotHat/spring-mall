package com.lyhux.mall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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


    @GetMapping("/redis/set")
    public String redisSet() {
        Book book = new Book();
        book.setNumber("controller no.0001");
        book.setTitle("hello world");

        redisTemplate.opsForValue().set("c_book01", book);

        return "redis user set success!";
    }


}
