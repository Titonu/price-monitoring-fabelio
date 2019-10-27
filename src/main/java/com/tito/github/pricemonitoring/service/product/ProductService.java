package com.tito.github.pricemonitoring.service.product;

import com.tito.github.pricemonitoring.Model.Product;

public interface ProductService {
    public Product storeProductWithTimeStamp(Product product);
    public Product getProductById(Long id);
    public Iterable<Product> getAllProduct();
}
