package com.tito.github.pricemonitoring.Repository;

import com.tito.github.pricemonitoring.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
