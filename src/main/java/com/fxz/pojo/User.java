package com.fxz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer role;   // 用户权限

    public User(String username,String password) {
        this.username = username;
        this.password = password;
    }
    public User(String username,String password,Integer role) {
        this.username = username;
        this.password = password;
    }
}
