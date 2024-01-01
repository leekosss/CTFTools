package com.fxz.controller;

import com.fxz.pojo.Result;
import com.fxz.pojo.User;
import com.fxz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {    // 用户登录
        User u = userService.queryByUsername(user.getUsername());
        if (u == null) {
            return Result.error("用户名或密码错误!");
        }
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (u.getPassword().equals(pwd)) {
            // 登录成功，将用户信息存储到Session中
            session.setAttribute("username", u.getUsername());
            session.setAttribute("role",u.getRole());
            return Result.success("登录成功!");
        }
        return Result.error("用户名或密码错误!");
    }

    @RequestMapping("/logout")
    public Result logout(HttpSession session) {    // 用户退出
        // 获取当前登录用户的用户名
        String username = (String) session.getAttribute("username");
        // 移除特定用户的Session
        if (username != null) {
            session.removeAttribute("username");
            session.removeAttribute("role");
            return Result.success("用户 " + username + " 已注销");
        } else {
            return Result.error("用户未登录");
        }
    }

    @RequestMapping("/getRole")
    public Result getRole(HttpSession session) {    // 获取当前用户权限
        // 获取当前登录用户的权限
        Integer role = (Integer) session.getAttribute("role");

        // 移除特定用户的Session
        if (role == 1) {
            return Result.success();    // 是管理员
        } else {
            return Result.error("用户没权限");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {  // (注册)添加用户
//        System.out.println(user.getUsername()+",,,,,"+user.getPassword());
        User u = userService.queryByUsername(user.getUsername());
        if (u != null) {
            return Result.error("用户已存在!");
        }
        int rel = userService.add(user);
        if (rel > 0) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");

    }

    @RequestMapping("/registerByAdmin")
    public Result registerByAdmin(@RequestBody User user) {  // (注册)添加用户
        User u = userService.queryByUsername(user.getUsername());
        if (u != null) {
            return Result.error("用户已存在!");
        }
        int rel = userService.addByAdmin(user);
        if (rel > 0) {
            return Result.success("注册成功");
        }
        return Result.error("注册失败");

    }

    @RequestMapping("/delete")
    public Result delete(String username) {    // 删除用户
        User u = userService.queryByUsername(username);
        if (u == null) {
            return Result.error("用户不存在!");
        }
        int rel = userService.delete(username);
        if (rel > 0) {
            return Result.success("删除用户成功");
        }
        return Result.error("删除用户失败");
    }
    @RequestMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean rel = userService.deleteById(id);
        if(rel) {
            return Result.success("delete success");
        }
        return Result.error("delete error");
    }

    @RequestMapping("/update")
    public Result update(@RequestBody User user) {
        boolean rel = userService.update(user);
        if(rel) {
            return Result.success("update user success");
        }
        return Result.error("update user error");
    }

    @RequestMapping("/")
    public Result query() {     // 查询用户
        List<User> userList = userService.query();
        return Result.success(userList);
    }
    @RequestMapping("/select/{id}")
    public Result selectById(@PathVariable Integer id) {
        return Result.success(userService.selectById(id));
    }

    @RequestMapping("/getTotal")
    public Result getTotal() { // 获得所有条数
        Integer total = userService.getTotal();
        return Result.success(total);
    }

    @RequestMapping("/selectByPageSize")    // 根据页码和页面大小 分页查询
    public Result selectByPageSize(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize) {
        List<User> users = userService.selectByPageSize(currentPage,pageSize);
        return Result.success(users);
    }

}
