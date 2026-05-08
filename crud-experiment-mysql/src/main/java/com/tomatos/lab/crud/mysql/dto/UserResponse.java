package com.tomatos.lab.crud.mysql.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
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
