package com.crq.article.mapper;

import com.crq.article.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @description:
 * @author: crq
 * @create: 2022-03-22 16:22
 **/
public interface UserMapper {

    /**
     * 新增用户 并返回自增主键
     * @param user user
     */
    @Insert("INSERT INTO t_user (phone,password,nickname,avatar,gender,birthday,address,create_time)" +
    "VALUE (#{phone},#{password},#{nickname},#{avatar},#{gender},#{birthday},#{address},#{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    /**
     * 根据手机号查询用户信息
     * @param phone phone
     * @return user
     */
    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    User findUserByPhone(@Param("phone")String phone);



}
