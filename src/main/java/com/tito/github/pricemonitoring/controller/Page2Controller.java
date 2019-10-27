package com.tito.github.pricemonitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/page-2")
public class Page2Controller {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String showPage2(){
        return "page-2";
    }
}
