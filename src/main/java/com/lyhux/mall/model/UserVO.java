package com.lyhux.mall.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVO {
    private Integer id;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
