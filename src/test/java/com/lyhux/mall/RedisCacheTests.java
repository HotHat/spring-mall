package com.lyhux.mall;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

@SpringBootTest
public class RedisCacheTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheTests.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public static class Book implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;

        private String title;
        private String number;

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

    public static class Book2 {

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


    @Test
    public void testPing() {
//        String pong = redisTemplate.opsForValue().get("PING");

//        LOGGER.info("redis respond:" + pong);
        byte[] response = redisTemplate.execute((RedisCallback<byte[]>) connection -> {
            return (byte[]) connection.execute("PING");
        });

        LOGGER.info("redis respond:" + new String(response, StandardCharsets.UTF_8));
    }

    @Test
    public void testStringTemp() {

    }

    @Test
    public void testObjectTempSet() {
        Book book = new Book();
        book.setNumber("no.0001");
        book.setTitle("hello");

        redisTemplate.opsForValue().set("book01", book);
    }

    @Test
    public void testObjectTempGet() {

        Book book = (Book) redisTemplate.opsForValue().get("book01");
        if (book != null) {
            LOGGER.info("redis book number: {} title: {}", book.getNumber(), book.getTitle());
        } else {
            LOGGER.info("redis book empty");
        }
    }

    @Test
    public void testObjectTempSet2() {
        Book2 book = new Book2();
        book.setNumber("no.0002");
        book.setTitle("world");

        redisTemplate.opsForValue().set("book02", book);
    }

    @Test
    public void testObjectTempGet2() {

        Book2 book = (Book2) redisTemplate.opsForValue().get("book02");
        if (book != null) {
            LOGGER.info("redis book number: {} title: {}", book.getNumber(), book.getTitle());
        } else {
            LOGGER.info("redis book empty");
        }
    }
}
