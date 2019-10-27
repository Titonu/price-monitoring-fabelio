package com.tito.github.pricemonitoring.Repository;

import com.tito.github.pricemonitoring.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {
    Optional <Product> findByProductId(Long id);
    List <Product> findByMinute(Integer minute);
}
