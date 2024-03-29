package com.tito.github.pricemonitoring.controller;

import com.tito.github.pricemonitoring.Model.Product;
import com.tito.github.pricemonitoring.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page-2")
public class Page2Controller {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ProductService productService;

    public Page2Controller(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String showPage2(Model model){
        Iterable<Product> allProduct = productService.getAllProduct();
        logger.info("All product: {}", allProduct);
        model.addAttribute("listProduct", allProduct);
        return "page-2";
    }
}
