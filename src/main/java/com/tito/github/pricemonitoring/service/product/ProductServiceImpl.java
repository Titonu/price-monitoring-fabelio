package com.tito.github.pricemonitoring.service.product;

import com.tito.github.pricemonitoring.Model.ImageThumbnail;
import com.tito.github.pricemonitoring.Model.Product;
import com.tito.github.pricemonitoring.Repository.ImageThumbnailRepository;
import com.tito.github.pricemonitoring.Repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Function used to store new product
     */
    public Product storeProductWithTimeStamp(Product product){
        product.setCreated(new Date());
        return productRepository.save(product);
    }

    /**
     * @param id product id
     * @return detail product by specific id
     */
    public Product getProductById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
    }

    /**
     * @return list product from database
     */
    public Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

}
