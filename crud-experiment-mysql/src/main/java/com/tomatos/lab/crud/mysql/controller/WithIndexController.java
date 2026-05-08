package com.tomatos.lab.crud.mysql.controller;

import com.tomatos.lab.common.timing.annotation.TimingTrack;
import com.tomatos.lab.crud.mysql.entity.UserWithIndex;
import com.tomatos.lab.crud.mysql.mapper.UserWithIndexMapper;
import com.tomatos.lab.crud.mysql.service.UserWithIndexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/with-index")
@RequiredArgsConstructor
public class WithIndexController {

    private final UserWithIndexService userWithIndexService;
    private final UserWithIndexMapper userWithIndexMapper;

    @TimingTrack
    @GetMapping("/users/query/{userId}")
    public UserWithIndex query(@PathVariable Integer userId) {
        return userWithIndexService.queryByUserId(userId);
    }

    @TimingTrack
    @PutMapping("/users/update/{userId}")
    public int update(@PathVariable Integer userId, @RequestParam Integer status) {
        return userWithIndexService.updateStatusByUserId(userId, status);
    }

    @TimingTrack
    @GetMapping("/count")
    public long count() {
        return userWithIndexMapper.selectCount(null);
    }

    @TimingTrack
    @GetMapping("/users/list/{userId}")
    public List<UserWithIndex> queryList(@PathVariable Integer userId) {
        return userWithIndexService.queryListByUserId(userId);
    }

    @TimingTrack
    @DeleteMapping("/users/delete/{userId}")
    public int delete(@PathVariable Integer userId) {
        return userWithIndexService.deleteByUserId(userId);
    }
}
