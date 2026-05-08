package com.tomatos.lab.crud.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tomatos.lab.crud.mysql.entity.UserNoIndex;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserNoIndexMapper extends BaseMapper<UserNoIndex> {

    @Select("SELECT * FROM t_user_no_index WHERE user_id = #{userId}")
    UserNoIndex selectByUserId(@Param("userId") Integer userId);

    @Update("UPDATE t_user_no_index SET status = #{status} WHERE user_id = #{userId}")
    int updateStatusByUserId(@Param("userId") Integer userId, @Param("status") Integer status);

    @Select("SELECT * FROM t_user_no_index WHERE user_id = #{userId}")
    List<UserNoIndex> selectListByUserId(@Param("userId") Integer userId);

    @Delete("DELETE FROM t_user_no_index WHERE user_id = #{userId}")
    int deleteByUserId(@Param("userId") Integer userId);

    int insertBatch(@Param("list") List<UserNoIndex> list);
}

