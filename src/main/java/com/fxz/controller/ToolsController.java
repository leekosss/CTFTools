package com.fxz.controller;


import com.fxz.pojo.Result;
import com.fxz.pojo.Tools;
import com.fxz.service.ToolsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tools")
public class ToolsController {

    @Autowired
    private ToolsService toolsService;

    @RequestMapping("/base")
    public Result base(@RequestBody Tools tools) {  // 转换base编码
        return toolsService.base(tools);
    }

    @RequestMapping("/caesar")
    public Result caesar(@RequestBody Tools tools) {        // 转换caesar编码
        return toolsService.caesar(tools);
    }

    @RequestMapping("/sha")
    public Result sha(@RequestBody Tools tools) {       // sha加密
        return toolsService.sha(tools);
    }

    @RequestMapping("/md")
    public Result md(@RequestBody Tools tools) {       // md加密
        return toolsService.md(tools);
    }
    @RequestMapping("/convert")
    public Result convert(@RequestBody Tools tools) {       // 进制转换
        return toolsService.convert(tools);
    }

    @RequestMapping("/encryption")
    public Result encryption(@RequestBody Tools tools) {
        return toolsService.encryption(tools);
    }
}
