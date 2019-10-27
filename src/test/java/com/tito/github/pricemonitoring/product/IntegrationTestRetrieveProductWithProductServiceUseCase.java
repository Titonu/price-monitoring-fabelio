package com.tito.github.pricemonitoring.product;

import com.tito.github.pricemonitoring.Model.ImageThumbnail;
import com.tito.github.pricemonitoring.Model.Product;
import com.tito.github.pricemonitoring.Repository.ProductRepository;
import com.tito.github.pricemonitoring.service.product.ProductService;
import com.tito.github.pricemonitoring.service.product.ProductServiceImpl;
import org.junit.Before;
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
public class IntegrationTestRetrieveProductWithProductServiceUseCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @Before
    public void initUseCase(){
        //Store new product into database, to retrieve later
        productRepository.save(initializeNewProduct());
    }


    @Test
    public void shouldNotNullGetAllProduct() {
        //Access product service method storeProductWithTimeStamp
        List<Product> allProduct = (List<Product>) productService.getAllProduct();
        logger.info("All Product: {}", allProduct);

        //Assert the product retrieved not null
        assertNotNull(allProduct);
        //Assert contain child table image thumbnails
        assertNotNull(allProduct.get(0).getImageThumbnails());
    }

    @Test
    public void shouldNotNullGetAllProductById() {
        //Access product service method storeProductWithTimeStamp
        Product productById = productService.findById(1L);

        //Assert the product by id retrieved not null
        assertNotNull(productById);
        //Assert the product retrieved contain product name "kursi Santai"
        assertEquals("Kursi Santai", productById.getName());

        //Assert child table image thumbnail is not null
        assertNotNull(productById.getImageThumbnails());
        //Assert child table image thumbnail has name "thumbnail-1"
        assertEquals("thumbnail-1", productById.getImageThumbnails().get(0).getName());

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
