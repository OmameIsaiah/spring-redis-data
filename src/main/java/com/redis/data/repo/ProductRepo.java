package com.redis.data.repo;

import com.redis.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Slf4j
public class ProductRepo {
    @Autowired
    private RedisTemplate redisTemplate;
    public static final String HASH_KEY = "Product";

    public Product save(Product product) {
        log.info("Saving new record in database...");
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public Product update(Product product) {
        log.info("Updating record from database...");
        redisTemplate.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        log.info("Fetching all records from database...");
        return redisTemplate.opsForHash().values(HASH_KEY);
    }

    public Product findProductByID(Long id) {
        log.info("Fetching record from database...");
        Object result = redisTemplate.opsForHash().get(HASH_KEY, id);
        if (Objects.nonNull(result)) {
            return (Product) result;
        }
        throw new RuntimeException("Oops! No record found with the id: " + id);

    }

    public String deleteProduct(Long id) {
        log.info("Deleting record from database...");
        redisTemplate.opsForHash().delete(HASH_KEY, id);
        return "Product deleted!";
    }
}
