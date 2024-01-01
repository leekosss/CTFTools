package com.fxz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tools {
    private String inputs;  // 输入
    private String type; // 类型
    private Integer options;  // 1 加密  2 解密
    private String key;    // 密钥
}
