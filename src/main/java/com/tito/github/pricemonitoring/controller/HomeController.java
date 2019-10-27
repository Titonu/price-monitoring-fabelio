package com.tito.github.pricemonitoring.controller;

import com.tito.github.pricemonitoring.Model.ImageThumbnail;
import com.tito.github.pricemonitoring.Model.Product;
import com.tito.github.pricemonitoring.service.HtmlParseService;
import com.tito.github.pricemonitoring.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HtmlParseService htmlParseService;
    private final ProductService productService;

    public HomeController(HtmlParseService htmlParseService, ProductService productService) {
        this.htmlParseService = htmlParseService;
        this.productService = productService;
    }

    @GetMapping
    public String showPage1(){
        return "page-1";
    }

    @PostMapping
    public String postUrl(HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        String url = request.getParameter("url");
        logger.info("hit from controller: {}", url);
        Product product = htmlParseService.parseHtmlIntoProduct(url);
        Optional<Product> productId = productService.findByProductId(product.getProductId());
        if (!productId.isPresent()) {
            productService.storeProductWithTimeStamp(product);
        }
        if (url == null){
            redirectAttributes.addFlashAttribute("error","Url not found!");
            return "redirect:/";
        }
        return "redirect:/page-2";
    }



    private List<ImageThumbnail> initializeNewImageThumbnailsByListString(List<String> thumbnails){
        List<ImageThumbnail> imageThumbnails = new ArrayList<>();
        for (String thumbnail : thumbnails) {
            ImageThumbnail imageThumbnail = new ImageThumbnail();
            imageThumbnail.setName(thumbnail);
            imageThumbnails.add(imageThumbnail);
        }
        return imageThumbnails;
    }

}
