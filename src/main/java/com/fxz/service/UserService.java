package com.fxz.service;


import com.fxz.pojo.Event;
import com.fxz.pojo.User;

import java.util.List;

public interface UserService {
    int add(User user);
    int addByAdmin(User user);
    int delete(String username);

    List<User> query();

    User queryByUsername(String username);

    List<User> selectByPageSize(Integer currentPage, Integer pageSize);

    Integer getTotal();

    User selectById(Integer id);

    boolean deleteById(Integer id);

    boolean update(User user);
}
