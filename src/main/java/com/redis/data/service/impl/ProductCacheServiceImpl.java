package com.redis.data.service.impl;

import com.redis.data.dto.request.ProductDTO;
import com.redis.data.model.Product;
import com.redis.data.repo.ProductRepo;
import com.redis.data.route.interfaces.ProductRouteCacheService;
import com.redis.data.service.ProductCacheService;
import com.redis.data.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductCacheServiceImpl implements ProductCacheService {

    private final ProductRepo productRepo;

    @Override
    public Product save(ProductDTO dto) {
        Product product = new Product();
        product.setId(Utils.getRandomID());
        product.setUuid(UUID.randomUUID().toString());
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        return productRepo.save(product);
    }

    @Override
    public Product update(Product dto) {
        return productRepo.save(dto);
    }

    @Override
    public Product findByID(Long id) {
        Product p = productRepo.findProductByID(id);
        if (Objects.isNull(p)) {
            return null;
        }
        return p;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = productRepo.findAll();
        if (list.isEmpty()) {
            return new ArrayList<>();
        }
        return list;
    }

    @Override
    public String delete(Long id) {
        return productRepo.deleteProduct(id);
    }
}
