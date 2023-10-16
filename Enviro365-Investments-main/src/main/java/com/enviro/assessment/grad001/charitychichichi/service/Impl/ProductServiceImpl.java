package com.enviro.assessment.grad001.charitychichichi.service.Impl;

import com.enviro.assessment.grad001.charitychichichi.domain.Product;
import com.enviro.assessment.grad001.charitychichichi.repository.ProductRepository;
import com.enviro.assessment.grad001.charitychichichi.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

   private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> products() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }
}
