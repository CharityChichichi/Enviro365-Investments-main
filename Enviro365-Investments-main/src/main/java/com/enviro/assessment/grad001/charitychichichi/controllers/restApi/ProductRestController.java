package com.enviro.assessment.grad001.charitychichichi.controllers.restApi;


import com.enviro.assessment.grad001.charitychichichi.domain.*;
import com.enviro.assessment.grad001.charitychichichi.exceptions.ResourceNotFound;
import com.enviro.assessment.grad001.charitychichichi.service.InvestorService;
import com.enviro.assessment.grad001.charitychichichi.service.ProductService;
import com.enviro.assessment.grad001.charitychichichi.service.WithdrawalNoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final WithdrawalNoticeService withdrawalNoticeService;

    private final ProductService productService;

    private final InvestorService investorService;

    public ProductRestController(WithdrawalNoticeService withdrawalNoticeService, ProductService productService, InvestorService investorService) {
        this.withdrawalNoticeService = withdrawalNoticeService;
        this.productService = productService;
        this.investorService = investorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.products();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) throws ResourceNotFound {

        if(product.getWithdrawalNotices()!=null){
            product.getWithdrawalNotices().forEach(n->n.setProduct(product));
        }

        if(product.getInvestor()!=null){
            Optional<Investor> investorOptional = investorService.findById(product.getInvestor().getId());
            if(investorOptional.isPresent()){
                Investor investor = investorOptional.get();
                product.setInvestor(investor);
            }
            else {
                throw new ResourceNotFound("Cant save product tha doesnt have an investor");
            }

        }
        System.out.println("product = " + product);


        Product createdProduct = productService.save(product);
        return ResponseEntity.created(URI.create("/products/" + createdProduct.getId())).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            Product updatedProduct = productService.update( product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProductField(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            Product updatedProduct = existingProduct.get();
            if (product.getName() != null) {
                updatedProduct.setName(product.getName());
            }
            if (product.getCurrentBalance() != null) {
                updatedProduct.setCurrentBalance(product.getCurrentBalance());
            }
            updatedProduct.setId(id);
            // add more fields as needed
            updatedProduct = productService.update( updatedProduct);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
