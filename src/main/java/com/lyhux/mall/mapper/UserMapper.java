package com.lyhux.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyhux.mall.User;
import com.lyhux.mall.model.UserVO;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    // 查询订单列表
    UserVO selectUser(Integer userId);

    // 查询订单列表
    List<UserVO> selectUsers();
}
