package com.tomatos.lab.cache.mysql.controller.direct;

import com.tomatos.lab.cache.mysql.entity.User;
import com.tomatos.lab.cache.mysql.service.direct.UserDirectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/direct")
@RequiredArgsConstructor
public class DirectController {

    private final UserDirectService userDirectService;

    /**
     * 场景1：单表查询 - 直接查数据库
     */
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        long start = System.nanoTime();
        User user = userDirectService.findById(id);
        long elapsed = (System.nanoTime() - start) / 1_000_000;
        log.info("[场景1-直查] userId={}, 耗时={}ms", id, elapsed);
        return user;
    }

    /**
     * 场景2：连接查询 - 直接查数据库
     */
    @GetMapping("/users/{id}/orders")
    public List<Map<String, Object>> getUserOrders(@PathVariable Long id) {
        long start = System.nanoTime();
        List<Map<String, Object>> orders = userDirectService.findUserWithOrders(id);
        long elapsed = (System.nanoTime() - start) / 1_000_000;
        log.info("[场景2-直查] userId={}, 耗时={}ms", id, elapsed);
        return orders;
    }
}
