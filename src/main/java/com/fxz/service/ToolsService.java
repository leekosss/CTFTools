package com.fxz.service;

import com.fxz.pojo.Result;
import com.fxz.pojo.Tools;
import org.springframework.stereotype.Service;


public interface ToolsService {

    Result base(Tools tools);

    Result caesar(Tools tools);

    Result sha(Tools tools);

    Result md(Tools tools);

    Result convert(Tools tools);

    Result encryption(Tools tools);
}
