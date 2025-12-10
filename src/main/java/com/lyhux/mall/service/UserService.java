package com.lyhux.mall.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lyhux.mall.User;
import com.lyhux.mall.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}