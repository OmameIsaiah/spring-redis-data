package com.redis.data.route;

import com.redis.data.dto.request.ProductDTO;
import com.redis.data.dto.response.ApiResponse;
import com.redis.data.model.Product;
import com.redis.data.repo.ProductRepo;
import com.redis.data.route.interfaces.ProductRouteCacheService;
import com.redis.data.route.interfaces.ProductRouteService;
import com.redis.data.service.ProductCacheService;
import com.redis.data.utils.MessageUtils;
import com.redis.data.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductCacheRoute implements ProductRouteCacheService {

    private final ProductCacheService service;

    @Override
    public ResponseEntity save(ProductDTO dto) {
        Product p = service.save(dto);
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.name(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                p));
    }

    @Override
    public ResponseEntity update(Product dto) {
        Product p = service.update(dto);
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.name(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                p));
    }

    @Override
    public ResponseEntity findByID(Long id) {
        Product p = service.findByID(id);
        if (Objects.isNull(p)) {
            return ResponseEntity.badRequest().body(new ApiResponse(
                    HttpStatus.NOT_FOUND.name(),
                    MessageUtils.FAILED,
                    HttpStatus.NOT_FOUND.value(),
                    "Oops! No product found with the id: " + id));
        }
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.toString(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                p));
    }

    @Override
    public ResponseEntity findAll() {
        List<Product> list = service.findAll();
        if (list.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse(
                    HttpStatus.NOT_FOUND.name(),
                    MessageUtils.FAILED,
                    HttpStatus.NOT_FOUND.value(),
                    new ArrayList<>()));
        }
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.toString(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                list));
    }

    @Override
    public ResponseEntity delete(Long id) {
        String message = service.delete(id);
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.name(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                message));
    }
}
