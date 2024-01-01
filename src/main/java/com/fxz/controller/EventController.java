package com.fxz.controller;

import com.fxz.pojo.Event;
import com.fxz.pojo.Result;
import com.fxz.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "*")
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping("/getTotal")
    public Result getTotal() { // 获得所有条数
        Integer total = eventService.getTotal();
        return Result.success(total);
    }

    @RequestMapping("/")
    public Result selectContest() { //查询比赛信息
        List<Event> events = eventService.selectContest();
        return Result.success(events);
    }

    @RequestMapping("/select/{id}")
    public Result selectById(@PathVariable Integer id) {
        return Result.success(eventService.selectById(id));
    }

    @RequestMapping("/update")
    public Result update(@RequestBody Event event) {
        boolean rel = eventService.update(event);
        if(rel) {
            return Result.success("update Event success");
        }
        return Result.error("update Event error");
    }

    @RequestMapping("/selectByPageSize")    // 根据页码和页面大小 分页查询
    public Result selectByPageSize(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        List<Event> events = eventService.selectByPageSize(currentPage,pageSize);
        return Result.success(events);
    }

    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean rel = eventService.delete(id);
        if(rel) {
            return Result.success("delete success");
        }
        return Result.error("delete error");
    }
    @RequestMapping("/add")
    public Result add(@RequestBody Event event) {
        boolean rel = eventService.add(event);
        if(rel) {
            return Result.success("add Event success");
        }
        return Result.error("add Event error");
    }

    @RequestMapping("/count")
    public Result count() {     // 统计每个月比赛天数
        return Result.success(eventService.count());
    }
}
