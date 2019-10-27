package com.tito.github.pricemonitoring.service.product;

import com.tito.github.pricemonitoring.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product storeProductWithTimeStamp(Product product);
    public Product findById(Long id);
    public Optional<Product> findByProductId(Long id);
    public List<Product> findByMinute(Integer minute);
    public Iterable<Product> getAllProduct();
}
