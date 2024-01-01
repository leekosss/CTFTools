package com.fxz.service.impl;

import com.fxz.mapper.EventMapper;
import com.fxz.pojo.Event;
import com.fxz.pojo.MonthlyEventDays;
import com.fxz.pojo.Result;
import com.fxz.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Autowired
    private EventMapper eventMapper;

    @Override
    public List<Event> selectContest() {
        return eventMapper.selectContest();
    }

    @Override
    public Event selectById(Integer id) {
       return eventMapper.selectById(id);
    }

    @Override
    public List<Event> selectByPageSize(Integer currentPage, Integer pageSize) {
        Integer offset = (currentPage - 1) * pageSize;
        return eventMapper.selectByPageSize(offset, pageSize);
    }

    @Override
    public Integer getTotal() {
        return eventMapper.getTotal();
    }

    @Override
    public boolean delete(Integer id) {
        Integer rows = eventMapper.delete(id);
        if (rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean add(Event event) {
        Integer affectRows = eventMapper.add(event);
        if(affectRows>0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Event event) {
        Integer affectRows = eventMapper.update(event);
        if(affectRows>0) {
            return true;
        }
        return false;

    }

    @Override
    public List<MonthlyEventDays> count() {
        List<MonthlyEventDays> monthlyEventDays = eventMapper.count();
        System.out.println(monthlyEventDays);
        return monthlyEventDays;
    }
}
