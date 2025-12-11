package com.lyhux.mall.service;

import com.lyhux.mall.mapper.UserMapper;
import com.lyhux.mall.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserTransactionService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void testTrans() throws Exception {
        UserVO user = new UserVO();
        user.setUsername("add_user1");
        LocalDateTime now = LocalDateTime.now();
        user.setCreatedAt(now);
        user.setUpdatedAt(now);

        int userId = userMapper.addUser(user);
        System.out.printf("method return id: %d\n", userId);
        System.out.printf("create user id: %d\n", user.getId());

        if (true) {
            // RuntimeException才会回滚
            throw new RuntimeException("test transaction");
        }

        UserVO userUp = new UserVO();
        userUp.setId(1);
        userUp.setUpdatedAt(now);
        int ret = userMapper.updateUser(userUp);
        System.out.printf("return val: %d\n", ret);

    }
}
