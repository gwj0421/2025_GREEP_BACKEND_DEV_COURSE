package org.example.springaopdemo.controller;

import lombok.RequiredArgsConstructor;
import org.example.springaopdemo.service.AopService;
import org.example.springaopdemo.service.NonAopService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class AopController {
    private final AopService aopService;
    private final NonAopService nonAopService;

    @ResponseBody
    @GetMapping("/")
    public String temp() {
        aopService.logic();
        nonAopService.logic();
        return "OK";
    }
}
