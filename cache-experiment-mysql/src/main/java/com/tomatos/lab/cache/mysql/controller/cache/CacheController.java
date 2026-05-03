package com.tomatos.lab.cache.mysql.controller.cache;

import com.tomatos.lab.cache.mysql.entity.User;
import com.tomatos.lab.cache.mysql.service.cache.UserCacheService;
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
    public User getUser(@PathVariable Long id) {
        boolean hitCache = redisTemplate.hasKey(USER_KEY_PREFIX + id);
        long start = System.nanoTime();
        User user = userCacheService.findById(id);
        long elapsed = (System.nanoTime() - start) / 1_000_000;
        log.info("[场景1-缓存-{}] userId={}, 耗时={}ms", hitCache ? "命中" : "未命中", id, elapsed);
        return user;
    }

    /**
     * 场景2：连接查询 - 先查缓存
     */
    @GetMapping("/users/{id}/orders")
    public List<Map<String, Object>> getUserOrders(@PathVariable Long id) {
        boolean hitCache = redisTemplate.hasKey(USER_KEY_PREFIX + id + ORDERS_SUFFIX);
        long start = System.nanoTime();
        List<Map<String, Object>> orders = userCacheService.findUserWithOrders(id);
        long elapsed = (System.nanoTime() - start) / 1_000_000;
        log.info("[场景2-缓存-{}] userId={}, 耗时={}ms", hitCache ? "命中" : "未命中", id, elapsed);
        return orders;
    }
}
