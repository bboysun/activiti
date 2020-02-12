package com.darryl.activiti.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Darryl
 * @Description: demo
 * @Date: created in 2020/2/7 17:56
 */
@RestController
public class DemoController {

    @GetMapping
    public String go() {
        return "Hello spring boot world";
    }
}
