package com.fxz.mapper;

import com.fxz.pojo.Event;
import com.fxz.pojo.MonthlyEventDays;
import com.fxz.pojo.Result;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventMapper {

    @Select("select * from event")
    List<Event> selectContest();

    @Select("select * from event limit #{offset},#{pageSize}")
    List<Event> selectByPageSize(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);


    @Select("select count(*) from event")
    Integer getTotal();

    @Delete("delete from event where id = #{id}")
    Integer delete(Integer id);

    @Insert("insert into event(name,url,type,start_time,end_time) values(#{name},#{url},#{type},#{startTime},#{endTime})")
    Integer add(Event event);

    @Select("select * from event where id = #{id}")
    Event selectById(Integer id);

    @Update("update event set name=#{name},url=#{url},type=#{type},start_time=#{startTime},end_time=#{endTime} where id = #{id}")
    Integer update(Event event);

    @Select("SELECT DATE_FORMAT(start_time, '%Y-%m') AS month, COUNT(DISTINCT DATE(start_time)) AS days " +
            "FROM event " +
            "GROUP BY month")
    List<MonthlyEventDays> count();
}
