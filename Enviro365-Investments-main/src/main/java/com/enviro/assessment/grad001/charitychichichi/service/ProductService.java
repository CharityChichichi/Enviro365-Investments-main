package com.enviro.assessment.grad001.charitychichichi.service;

import com.enviro.assessment.grad001.charitychichichi.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    Optional<Product> findById(Long id);
    
    void deleteById(Long id);

    List<Product> products();

    Product update(Product product);
}
