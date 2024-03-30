package com.redis.data.service;

import com.redis.data.dto.request.ProductDTO;
import com.redis.data.model.Product;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface ProductCacheService {

    public static final String HASH_KEY = "Product";

    Product save(ProductDTO dto);

    @CachePut(value = HASH_KEY, key = "#result.id")
    Product update(Product product);

    @Cacheable(value = HASH_KEY, key = "#id")
        //@CachePut(value = HASH_KEY, key = "#id", unless = "#result.quantity > 10")
    Product findByID(Long id);

    @Cacheable(value = HASH_KEY)
    List<Product> findAll();

    //@CacheEvict(value = HASH_KEY, key = "#id")
    @CacheEvict(value = HASH_KEY, allEntries = true)
    String delete(Long id);
}
