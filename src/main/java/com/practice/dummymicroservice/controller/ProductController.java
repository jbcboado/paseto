package com.practice.dummymicroservice.controller;

import com.practice.dummymicroservice.exception.ProductNotFoundException;
import com.practice.dummymicroservice.model.request.ProductRequest;
import com.practice.dummymicroservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductRequest productRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts(){
//        System.out.println("IM HERE");
//        throw new ProductNotFoundException();
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String id){
        throw new ProductNotFoundException();
        //return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchProductId(@RequestParam String searchStr){
        return ResponseEntity.ok(productService.searchProductByName(searchStr));
    }

}
