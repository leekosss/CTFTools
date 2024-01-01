package com.fxz.service.impl;

import com.fxz.mapper.UserMapper;
import com.fxz.pojo.User;
import com.fxz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
    @Autowired
    private UserMapper userMapper;

    @Override
    public int add(User user) {
        // md5加密一下
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        User u = new User(user.getUsername(),pwd);
        return userMapper.add(u);
    }

    @Override
    public int addByAdmin(User user) {
        // md5加密一下
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(pwd);
        return userMapper.addByAdmin(user);
    }

    @Override
    public int delete(String username) {
        return userMapper.delete(username);
    }

    @Override
    public List<User> query() {
        return userMapper.query();
    }

    @Override
    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

    @Override
    public List<User> selectByPageSize(Integer currentPage, Integer pageSize) {
        Integer offset = (currentPage - 1) * pageSize;
        return userMapper.selectByPageSize(offset, pageSize);
    }

    @Override
    public Integer getTotal() {
        return userMapper.getTotal();
    }

    @Override
    public User selectById(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        Integer rows = userMapper.deleteById(id);
        if (rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(pwd);
        Integer affectRows = userMapper.update(user);
        if(affectRows>0) {
            return true;
        }
        return false;
    }


}
