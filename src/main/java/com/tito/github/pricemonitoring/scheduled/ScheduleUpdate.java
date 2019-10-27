package com.tito.github.pricemonitoring.scheduled;


import com.tito.github.pricemonitoring.Model.Product;
import com.tito.github.pricemonitoring.service.HtmlParseService;
import com.tito.github.pricemonitoring.service.product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleUpdate {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final ProductService productService;
    @Autowired
    private HtmlParseService htmlParseService;

    public ScheduleUpdate(ProductService productService) {
        this.productService = productService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void scheduleDaily() throws IOException {
        logger.info("run scheduler job: {}", new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<Product> listProductByMinute = productService.findByMinute(LocalDateTime.now().getMinute());
        if (!listProductByMinute.isEmpty()) {
            for (Product prodOld:listProductByMinute) {
                Product productNew = htmlParseService.parseHtmlIntoProduct(prodOld.getUrl());
                logger.info("product updated: {}", prodOld.getName());
                prodOld.setPrice(productNew.getPrice());
                productService.storeProductWithTimeStamp(prodOld);
            }
        }
    }

}
