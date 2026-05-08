package com.tomatos.lab.crud.mysql.service;

import com.tomatos.lab.crud.mysql.entity.UserNoIndex;
import com.tomatos.lab.crud.mysql.mapper.UserNoIndexMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserNoIndexService {

    private final UserNoIndexMapper userNoIndexMapper;

    public UserNoIndex queryByUserId(Integer userId) {
        return userNoIndexMapper.selectByUserId(userId);
    }

    public int updateStatusByUserId(Integer userId, Integer status) {
        return userNoIndexMapper.updateStatusByUserId(userId, status);
    }

    public List<UserNoIndex> queryListByUserId(Integer userId) {
        return userNoIndexMapper.selectListByUserId(userId);
    }

    public int deleteByUserId(Integer userId) {
        return userNoIndexMapper.deleteByUserId(userId);
    }
}
