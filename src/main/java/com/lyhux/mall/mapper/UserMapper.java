package com.lyhux.mall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lyhux.mall.User;
import com.lyhux.mall.dto.QueryDTO;
import com.lyhux.mall.model.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper extends BaseMapper<User> {
    // 查询订单列表
    UserVO selectUser(Integer userId);

    // 查询订单列表
    List<UserVO> selectUsers();

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetUsersByName")
    List<UserVO> getUsersByName(
            String name, String orderByColumn);

    @SelectProvider(type = UserSqlBuilder.class, method = "buildGetDto")
    List<UserVO> getUsersByName2(int id, QueryDTO dto);

    @InsertProvider(type = UserSqlBuilder.class, method = "buildAddUser")
    @Options(
            useGeneratedKeys = true,
            keyProperty = "id",
            keyColumn = "id"
    )
    int addUser(UserVO user);

    @UpdateProvider(type = UserSqlBuilder.class, method = "buildUpdateUser")
    int updateUser(UserVO user);


    class UserSqlBuilder {
        // If not use @Param, you should be define same arguments with mapper method
        public static String buildGetUsersByName(
                final String name, final String orderByColumn) {
            return "select * from admin where username like #{param1} order by " + orderByColumn;
        }

        public static String buildGetDto(int id, QueryDTO dto) {
            return "select * from admin where id = #{id} and username like #{dto.name} order by " + dto.getOrderBy();
        }

        public static String buildAddUser(UserVO user) {
            return "insert into admin (username, created_at, updated_at) values (#{username}, #{createdAt}, #{updatedAt})";

        }

        public static String buildUpdateUser(UserVO user) {
            return "update admin set updated_at=#{updatedAt} where id=#{id}";

        }
    }
}