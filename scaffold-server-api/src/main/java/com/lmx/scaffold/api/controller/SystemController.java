package com.lmx.scaffold.api.controller;

import com.lmx.scaffold.api.response.Result;
import com.lmx.scaffold.api.service.SystemService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lmx
 * @create: 2021/1/13
 **/
@RestController
@RequestMapping("/sys")
@Slf4j
public class SystemController extends BaseController {

    @Autowired
    private SystemService systemService;

    @GetMapping("/sys/test")
    @ApiOperation(value = "dashboard 测试接口", notes = "dashboard 测试接口 - 对外提供")
    public Result test() {
        systemService.test();
        return success("hello dashboard , is ok");
    }


}
