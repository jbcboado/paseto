package com.practice.dummymicroservice.service;

import com.practice.dummymicroservice.exception.ProductNotFoundException;
import com.practice.dummymicroservice.model.Product;
import com.practice.dummymicroservice.model.request.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableCaching
public class ProductService {

    private final ProductRepository productRepository;
    @Cacheable("products")
    public Product createProduct(ProductRequest productRequest) {
        Product p = new Product();
        p.setName(productRequest.getName());
        p.setDescription(productRequest.getDescription());
        p.setPrice(productRequest.getPrice());

       return productRepository.save(p);
    }

    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Cacheable(value = "products")
    public Product getProductById(String id) {
        Optional<Product> p = productRepository.findById(Long.parseLong(id));

        if(p.isPresent()) return p.get();
        else throw new ProductNotFoundException();
    }

    public List<Product> searchProductByName(String searchStr) {
        return productRepository.searchProductByName(searchStr);
    }
}
