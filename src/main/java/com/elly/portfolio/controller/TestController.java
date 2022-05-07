package com.elly.portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/test")
public class TestController {
   
    @GetMapping
    public String test() {
        return "test.html";
    }
    
}
