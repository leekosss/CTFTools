package com.fxz.mapper;

import com.fxz.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{username}")
    User queryByUsername(String username);

    @Insert("insert into user(username,password) values(#{username},#{password})")
    int add(User user);

    @Insert("insert into user(username,password,role) values(#{username},#{password},#{role})")
    int addByAdmin(User u);

    @Delete("delete from user where username=#{username}")
    int delete(String username);

    @Select("select * from user")
    List<User> query();

    @Select("select * from user limit #{offset},#{pageSize}")
    List<User> selectByPageSize(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from user")
    Integer getTotal();

    @Select("select * from user where id=#{id}")
    User selectById(Integer id);

    @Delete("delete from user where id = #{id}")
    Integer deleteById(Integer id);

    @Update("update user set username=#{username},password=#{password},role=#{role} where id = #{id}")
    Integer update(User user);
}
