package com.tomatos.lab.crud.mysql.controller;

import com.tomatos.lab.common.timing.annotation.TimingTrack;
import com.tomatos.lab.crud.mysql.entity.UserNoIndex;
import com.tomatos.lab.crud.mysql.mapper.UserNoIndexMapper;
import com.tomatos.lab.crud.mysql.service.UserNoIndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/no-index")
@RequiredArgsConstructor
public class NoIndexController {

    private final UserNoIndexService userNoIndexService;
    private final UserNoIndexMapper userNoIndexMapper;

    @TimingTrack
    @GetMapping("/users/query/{userId}")
    public UserNoIndex query(@PathVariable Integer userId) {
        return userNoIndexService.queryByUserId(userId);
    }

    @TimingTrack
    @PutMapping("/users/update/{userId}")
    public int update(@PathVariable Integer userId, @RequestParam Integer status) {
        return userNoIndexService.updateStatusByUserId(userId, status);
    }

    @TimingTrack
    @GetMapping("/count")
    public long count() {
        return userNoIndexMapper.selectCount(null);
    }

    @TimingTrack
    @GetMapping("/users/list/{userId}")
    public List<UserNoIndex> queryList(@PathVariable Integer userId) {
        return userNoIndexService.queryListByUserId(userId);
    }

    @TimingTrack
    @DeleteMapping("/users/delete/{userId}")
    public int delete(@PathVariable Integer userId) {
        return userNoIndexService.deleteByUserId(userId);
    }
}
