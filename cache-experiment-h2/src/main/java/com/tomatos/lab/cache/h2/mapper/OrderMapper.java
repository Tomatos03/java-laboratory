package com.tomatos.lab.cache.h2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomatos.lab.cache.h2.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
