package com.tomatos.lab.cache.h2.controller.direct;

import com.tomatos.lab.cache.h2.entity.User;
import com.tomatos.lab.cache.h2.service.direct.UserDirectService;
import com.tomatos.lab.common.timing.annotation.TimingTrack;
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
    @TimingTrack
    public User getUser(@PathVariable Long id) {
        User user = userDirectService.findById(id);
        log.info("[场景1-直查] userId={}", id);
        return user;
    }

    /**
     * 场景2：连接查询 - 直接查数据库
     */
    @GetMapping("/users/{id}/orders")
    @TimingTrack
    public List<Map<String, Object>> getUserOrders(@PathVariable Long id) {
        List<Map<String, Object>> orders = userDirectService.findUserWithOrders(id);
        log.info("[场景2-直查] userId={}", id);
        return orders;
    }
}
