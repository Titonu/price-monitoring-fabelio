package com.tito.github.pricemonitoring.controller;

import com.tito.github.pricemonitoring.model.ImageThumbnail;
import com.tito.github.pricemonitoring.model.Product;
import com.tito.github.pricemonitoring.service.HtmlParseService;
import com.tito.github.pricemonitoring.service.product.ProductService;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        Product product = parseHtmlIntoProduct(url);
        productService.storeProductWithTimeStamp(product);

        if (url == null){
            redirectAttributes.addFlashAttribute("error","Url not found!");
            return "redirect:/";
        }
        return "redirect:/page-2";
    }

    private Product parseHtmlIntoProduct(String url) throws IOException {
        Document dom = htmlParseService.getDocumentByUrl(url);
        String productId = htmlParseService.getProductIdFromDom(dom);
        String price = htmlParseService.getProductPriceFromDomByProductId(dom, productId);
        String productName = htmlParseService.getProductNameFromDom(dom);
        String image = htmlParseService.getProductImageFromDom(dom);
        String description = htmlParseService.getProductDescriptionFromDom(dom);
//        List<String> thumbnails = htmlParseService.getImageThumbnailsByProductImage(image);

        Product product = new Product();
        product.setName(productName);
        product.setPrice(price);
        product.setImage(image);
        product.setDescription(description);
//        product.setImageThumbnails(initializeNewImageThumbnailsByListString(thumbnails));
        return product;
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
