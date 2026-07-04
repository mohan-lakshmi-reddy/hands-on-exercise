package com.library;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SampleController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Library Management System - Spring WebMVC is working!";
    }
}
