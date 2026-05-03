package com.tomatos.lab.cache.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomatos.lab.cache.mysql.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
