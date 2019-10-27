package com.tito.github.pricemonitoring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String showPage1(){
        return "page-1";
    }

    @PostMapping
    public String postUrl(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String url = request.getParameter("url");
        logger.info("hit from controller: {}", url);
        if (url == null){
            redirectAttributes.addFlashAttribute("error","Url not found!");
            return "redirect:/";
        }
        return "redirect:/page-2";
    }

}
