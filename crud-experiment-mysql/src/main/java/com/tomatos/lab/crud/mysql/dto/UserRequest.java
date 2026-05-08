package com.tomatos.lab.crud.mysql.dto;

import lombok.Data;

@Data
public class UserRequest {
    private Integer userId;
    private String username;
    private String email;
    private String phone;
    private String address;
    private Integer status;
}
