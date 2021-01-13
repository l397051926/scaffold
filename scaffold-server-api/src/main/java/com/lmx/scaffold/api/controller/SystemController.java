package com.lmx.scaffold.api.controller;

import com.lmx.scaffold.api.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lmx
 * @create: 2021/1/13
 **/
@RestController
public class SystemController {

    @Autowired
    private SystemService systemService;

    @GetMapping("/hello")
    public String hello() {
        systemService.test();
        return "hello world";
    }

}
