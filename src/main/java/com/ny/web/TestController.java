package com.ny.web;

import com.ny.annotation.AccessLimit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yang
 * @version 1.0
 * @date 2021/4/12 15:12
 */
@RestController
public class TestController {
    @GetMapping("test")
    @AccessLimit(seconds = 15, maxCount = 10) //15秒内 允许请求5次
    public String testAccessLimit() {
        return "success";
    }
}
