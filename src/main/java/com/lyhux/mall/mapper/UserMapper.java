package com.lyhux.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyhux.mall.User;
import com.lyhux.mall.model.UserVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    // 查询订单列表
    UserVO selectUser(Integer userId);

    // 查询订单列表
    List<UserVO> selectUsers();

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUsersByName")
    List<UserVO> getUsersByName(
            String name, String orderByColumn);


    class UserSqlBuilder {
        // If not use @Param, you should be define same arguments with mapper method
        public static String buildGetUsersByName(
                final String name, final String orderByColumn) {
            return "select * from admin where username like #{param1, jdbcType=VARCHAR} order by " + orderByColumn;
        }
    }
}