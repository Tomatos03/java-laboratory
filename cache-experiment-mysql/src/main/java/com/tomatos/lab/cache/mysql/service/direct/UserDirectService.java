package com.tomatos.lab.cache.mysql.service.direct;

import com.tomatos.lab.cache.mysql.entity.User;
import com.tomatos.lab.cache.mysql.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDirectService {

    private final UserMapper userMapper;

    /**
     * 场景1：单表查询 - 直接查数据库
     */
    public User findById(Long id) {
        return userMapper.selectById(id);
    }

    /**
     * 场景2：连接查询 - 直接查数据库
     */
    public List<Map<String, Object>> findUserWithOrders(Long userId) {
        return userMapper.selectUserWithOrders(userId);
    }
}
