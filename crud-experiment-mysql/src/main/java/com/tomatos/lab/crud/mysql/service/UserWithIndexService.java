package com.tomatos.lab.crud.mysql.service;

import com.tomatos.lab.crud.mysql.entity.UserWithIndex;
import com.tomatos.lab.crud.mysql.mapper.UserWithIndexMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserWithIndexService {

    private final UserWithIndexMapper userWithIndexMapper;

    public UserWithIndex queryByUserId(Integer userId) {
        return userWithIndexMapper.selectByUserId(userId);
    }

    public int updateStatusByUserId(Integer userId, Integer status) {
        return userWithIndexMapper.updateStatusByUserId(userId, status);
    }

    public List<UserWithIndex> queryListByUserId(Integer userId) {
        return userWithIndexMapper.selectListByUserId(userId);
    }

    public int deleteByUserId(Integer userId) {
        return userWithIndexMapper.deleteByUserId(userId);
    }
}
