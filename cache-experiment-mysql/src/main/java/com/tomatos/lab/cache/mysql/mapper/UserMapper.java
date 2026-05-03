package com.tomatos.lab.cache.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomatos.lab.cache.mysql.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 连接查询：查询用户及其订单
     */
    @Select("SELECT u.id as user_id, u.username, u.email, u.phone, u.address, " +
            "o.id as order_id, o.product_name, o.amount, o.create_time " +
            "FROM t_user u LEFT JOIN t_order o ON u.id = o.user_id " +
            "WHERE u.id = #{userId}")
    List<Map<String, Object>> selectUserWithOrders(@Param("userId") Long userId);
}
