package com.tomatos.lab.cache.h2.controller.cache;

import com.tomatos.lab.cache.h2.entity.User;
import com.tomatos.lab.cache.h2.service.cache.UserCacheService;
import com.tomatos.lab.common.timing.annotation.TimingTrack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/cache")
@RequiredArgsConstructor
public class CacheController {

    private final UserCacheService userCacheService;
    private final StringRedisTemplate redisTemplate;

    private static final String USER_KEY_PREFIX = "user:";
    private static final String ORDERS_SUFFIX = ":orders";

    /**
     * 场景1：单表查询 - 先查缓存
     */
    @GetMapping("/users/{id}")
    @TimingTrack
    public User getUser(@PathVariable Long id) {
        boolean hitCache = redisTemplate.hasKey(USER_KEY_PREFIX + id);
        User user = userCacheService.findById(id);
        log.info("[场景1-缓存-{}] userId={}", hitCache ? "命中" : "未命中", id);
        return user;
    }

    /**
     * 场景2：连接查询 - 先查缓存
     */
    @GetMapping("/users/{id}/orders")
    @TimingTrack
    public List<Map<String, Object>> getUserOrders(@PathVariable Long id) {
        boolean hitCache = redisTemplate.hasKey(USER_KEY_PREFIX + id + ORDERS_SUFFIX);
        List<Map<String, Object>> orders = userCacheService.findUserWithOrders(id);
        log.info("[场景2-缓存-{}] userId={}", hitCache ? "命中" : "未命中", id);
        return orders;
    }
}
