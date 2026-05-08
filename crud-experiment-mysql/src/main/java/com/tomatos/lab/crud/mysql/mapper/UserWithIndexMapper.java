package com.tomatos.lab.crud.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomatos.lab.crud.mysql.entity.UserWithIndex;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserWithIndexMapper extends BaseMapper<UserWithIndex> {

    @Select("SELECT * FROM t_user_with_index WHERE user_id = #{userId}")
    UserWithIndex selectByUserId(@Param("userId") Integer userId);

    @Update("UPDATE t_user_with_index SET status = #{status} WHERE user_id = #{userId}")
    int updateStatusByUserId(@Param("userId") Integer userId, @Param("status") Integer status);

    @Select("SELECT * FROM t_user_with_index WHERE user_id = #{userId}")
    List<UserWithIndex> selectListByUserId(@Param("userId") Integer userId);

    @Delete("DELETE FROM t_user_with_index WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Integer userId);

    int insertBatch(@Param("list") List<UserWithIndex> list);
}

