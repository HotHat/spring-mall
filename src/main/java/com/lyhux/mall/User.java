package com.lyhux.mall;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("qs_admin")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

}
