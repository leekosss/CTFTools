package com.fxz.service;

import com.fxz.pojo.Event;
import com.fxz.pojo.MonthlyEventDays;
import com.fxz.pojo.Result;

import java.util.List;

public interface EventService {
    List<Event> selectContest();
    Event selectById(Integer id);
    List<Event> selectByPageSize(Integer currentPage,Integer pageSize);


    Integer getTotal();

    boolean delete(Integer id);

    boolean add(Event event);

    boolean update(Event event);

    List<MonthlyEventDays> count();
}
