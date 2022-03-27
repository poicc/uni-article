package com.crq.article.mapper;

import com.crq.article.model.User;
import org.apache.ibatis.annotations.*;

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
    @Insert("INSERT INTO t_user (phone,wx_openid,password,nickname,avatar,gender,birthday,address,bg,create_time) " + "VALUES (#{phone},#{wxOpenId}, #{password}, #{nickname},#{avatar}, #{gender}, #{birthday},#{address},#{bg},#{createTime}) ")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user);

    /**
     * 根据手机号查询用户信息
     * @param phone phone
     * @return user
     */
    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    User findUserByPhone(@Param("phone")String phone);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     */
    @Update("UPDATE t_user SET password=#{password},nickname=#{nickname},avatar=#{avatar}," +
            "gender=#{gender},birthday=#{birthday},address=#{address},bg=#{bg} WHERE id=#{id} ")
    void updateUser(User user);


    /**
     * 根据微信openId查询用户信息
     *
     * @param wxOpenId 微信openId
     * @return User 用户对象
     */
    @Select("SELECT * FROM t_user WHERE wx_openid = #{wxOpenId} ")
    User fineUserByOpenId(@Param("wxOpenId") String wxOpenId);

    /**
     * 绑定手机号
     *
     * @param phone    手机号
     * @param wxOpenId wxOpenId
     */
    @Update(("UPDATE t_user SET wx_openid=#{wxOpenId} WHERE phone=#{phone} "))
    void bandPhone(@Param("phone") String phone, @Param("wxOpenId") String wxOpenId);


    /**
     * 根据wxOpenId删除用户
     *
     * @param wxOpenId wxOpenId
     */
    @Delete(("DELETE FROM t_user WHERE wx_openid=#{wxOpenId} "))
    void deleteUserByOpenId(@Param("wxOpenId") String wxOpenId);


}
