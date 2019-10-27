package com.tito.github.pricemonitoring.product;

import com.tito.github.pricemonitoring.model.ImageThumbnail;
import com.tito.github.pricemonitoring.model.Product;
import com.tito.github.pricemonitoring.repository.ImageThumbnailRepository;
import com.tito.github.pricemonitoring.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IntegrationTestStoreProductWithRepositoryUseCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageThumbnailRepository imageThumbnailRepository;


    @Test
    public void storeProductWithThumbnailsTest() {
        Product product = new Product();
        product.setName("Kursi Makan");
        product.setImage("image.jpg");
        product.setDescription("This is the description");
        product.setCreated(new Date());
        Product productSaved = productRepository.save(product);

        List<String> thumbnails = Arrays.asList("thumbnail-1", "thumbnail-2", "thumbnail-3");
        List<ImageThumbnail> imageThumbnails = new ArrayList<>();
        for (String thumbnail : thumbnails) {
            ImageThumbnail imageThumbnail = new ImageThumbnail();
            imageThumbnail.setName(thumbnail);
            imageThumbnail.setProduct(productSaved);
            imageThumbnails.add(imageThumbnail);
        }
        Iterable<ImageThumbnail> imageThumbnailsSaved = imageThumbnailRepository.saveAll(imageThumbnails);

        logger.info("image thumbnail string: {}", imageThumbnails);
        logger.info("product saved: {}", productSaved);
        logger.info("thumbnail saved: {}", imageThumbnailRepository.findAll());

        assertNotNull(productSaved);
        assertEquals("Kursi Makan", productRepository.findById(1L).get().getName());

        assertNotNull(imageThumbnailsSaved);
        assertEquals(3, imageThumbnailRepository.count());

    }
}
