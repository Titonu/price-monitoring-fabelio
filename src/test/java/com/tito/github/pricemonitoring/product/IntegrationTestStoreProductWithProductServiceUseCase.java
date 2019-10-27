package com.tito.github.pricemonitoring.product;

import com.tito.github.pricemonitoring.model.ImageThumbnail;
import com.tito.github.pricemonitoring.model.Product;
import com.tito.github.pricemonitoring.repository.ProductRepository;
import com.tito.github.pricemonitoring.service.product.ProductService;
import com.tito.github.pricemonitoring.service.product.ProductServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(ProductServiceImpl.class)
public class IntegrationTestStoreProductWithProductServiceUseCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Test
    public void storeProductWithTimeStampTest() {
        //Access product service method storeProductWithTimeStamp
        productService.storeProductWithTimeStamp(initializeNewProduct());
        logger.info("all product: {}", productService.getAllProduct());
        logger.info("all product: {}", productRepository.findById(1L).get().getImageThumbnails());

        //Assert that product is stored in database, it has product name "Kursi Santai"
        assertNotNull(productRepository.findById(1L).get());
        assertEquals(initializeNewProduct().getName(), productRepository.findById(1L).get().getName());

        //Assert image thumbnail is stored in database as well, it has image name "thumbnail-1"
        assertNotNull(productRepository.findById(1L).get().getImageThumbnails());
        assertEquals(initializeNewImageThumbnails().get(0).getName(), productRepository.findById(1L).get().getImageThumbnails().get(0).getName());
    }



    private Product initializeNewProduct(){
        Product product = new Product();
        product.setName("Kursi Santai");
        product.setImage("image.jpg");
        product.setDescription("This is the description");
        product.setCreated(new Date());
        product.setImageThumbnails(initializeNewImageThumbnails());
        return product;
    }

    private List<ImageThumbnail> initializeNewImageThumbnails(){
        List<String> thumbnails = Arrays.asList("thumbnail-1", "thumbnail-2", "thumbnail-3");
        List<ImageThumbnail> imageThumbnails = new ArrayList<>();
        for (String thumbnail : thumbnails) {
            ImageThumbnail imageThumbnail = new ImageThumbnail();
            imageThumbnail.setName(thumbnail);
            imageThumbnails.add(imageThumbnail);
        }
        return imageThumbnails;
    }
}
