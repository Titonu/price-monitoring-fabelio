package com.tito.github.pricemonitoring.service.product;

import com.tito.github.pricemonitoring.model.Product;
import com.tito.github.pricemonitoring.repository.ProductRepository;
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
    public Product storeProductWithTimeStamp(Product product) {
        // check product doesnt exist in db
        if (!productRepository.findByProductId(product.getProductId()).isPresent()) {
            product.setCreated(new Date());
        }
        product.setUpdated(new Date());

        //Resize to bigger image
        product.setImage(product.getImage().replace("/265x265/", "/700x350/"));
        return productRepository.save(product);
    }

    /**
     * @param id product id
     * @return detail product by specific id
     */
    public Product findById(Long id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public Optional<Product> findByProductId(Long id) {
        return productRepository.findByProductId(id);
    }

    @Override
    public List<Product> findByMinute(Integer minute) {
        return productRepository.findByMinute(minute);
    }

    /**
     * @return list product from database
     */
    public Iterable<Product> getAllProduct() {
        return productRepository.findAll();
    }

}
