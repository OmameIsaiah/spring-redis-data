package com.redis.data.route.interfaces;

import com.redis.data.dto.request.ProductDTO;
import com.redis.data.model.Product;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public interface ProductRouteService {

    public static final String HASH_KEY = "Product";

    @PostMapping("/product")
    ResponseEntity save(@RequestBody ProductDTO dto);

    @PutMapping("/product")
    ResponseEntity update(@RequestBody Product product);

    @GetMapping("/product/{id}")
    ResponseEntity findByID(@PathVariable("id") Long id);

    @GetMapping("/products")
    ResponseEntity findAll();

    @DeleteMapping("/product/{id}")
    ResponseEntity delete(@PathVariable("id") Long id);
}
