package com.tomatos.lab.crud.mysql.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_user_no_index")
public class UserNoIndex {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer userId;
    private String username;
    private String email;
    private String phone;
    private String address;
    private Integer status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
