package com.tomatos.lab.cache.mysql.service.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tomatos.lab.cache.mysql.entity.User;
import com.tomatos.lab.cache.mysql.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserCacheService {

    private final UserMapper userMapper;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    private static final String USER_KEY_PREFIX = "user:";
    private static final String ORDERS_SUFFIX = ":orders";
    private static final long CACHE_TTL_MINUTES = 10;

    /**
     * 场景1：单表查询 - 先查缓存，未命中则查数据库
     */
    public User findById(Long id) {
        String key = USER_KEY_PREFIX + id;
        String cached = redisTemplate.opsForValue().get(key);

        if (cached != null) {
            log.debug("缓存命中: key={}", key);
            try {
                return objectMapper.readValue(cached, User.class);
            } catch (JsonProcessingException e) {
                log.error("反序列化失败", e);
            }
        }

        log.debug("缓存未命中: key={}", key);
        User user = userMapper.selectById(id);
        if (user != null) {
            try {
                String json = objectMapper.writeValueAsString(user);
                redisTemplate.opsForValue().set(key, json, CACHE_TTL_MINUTES, TimeUnit.MINUTES);
            } catch (JsonProcessingException e) {
                log.error("序列化失败", e);
            }
        }
        return user;
    }

    /**
     * 场景2：连接查询 - 先查缓存，未命中则查数据库
     */
    public List<Map<String, Object>> findUserWithOrders(Long userId) {
        String key = USER_KEY_PREFIX + userId + ORDERS_SUFFIX;
        String cached = redisTemplate.opsForValue().get(key);

        if (cached != null) {
            log.debug("缓存命中: key={}", key);
            try {
                return objectMapper.readValue(cached, new TypeReference<>() {});
            } catch (JsonProcessingException e) {
                log.error("反序列化失败", e);
            }
        }

        log.debug("缓存未命中: key={}", key);
        List<Map<String, Object>> orders = userMapper.selectUserWithOrders(userId);
        if (orders != null && !orders.isEmpty()) {
            try {
                String json = objectMapper.writeValueAsString(orders);
                redisTemplate.opsForValue().set(key, json, CACHE_TTL_MINUTES, TimeUnit.MINUTES);
            } catch (JsonProcessingException e) {
                log.error("序列化失败", e);
            }
        }
        return orders;
    }
}
