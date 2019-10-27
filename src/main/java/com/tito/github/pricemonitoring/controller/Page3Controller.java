package com.tito.github.pricemonitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page-3")
public class Page3Controller {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String showPage3(){
        return "page-3";
    }
}
