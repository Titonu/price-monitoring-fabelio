package com.tito.github.pricemonitoring.repository;

import com.tito.github.pricemonitoring.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
