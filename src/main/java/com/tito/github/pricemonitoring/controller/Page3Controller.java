package com.tito.github.pricemonitoring.controller;

import com.tito.github.pricemonitoring.Model.Product;
import com.tito.github.pricemonitoring.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page-3")
public class Page3Controller {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProductService productService;

    public Page3Controller(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String showPage3(@PathVariable Long id, Model model){
        Product productById = productService.findById(id);
        model.addAttribute("product", productById);
        return "page-3";
    }
}
