package com.redis.data.route;

import com.redis.data.dto.request.ProductDTO;
import com.redis.data.dto.response.ApiResponse;
import com.redis.data.model.Product;
import com.redis.data.repo.ProductRepo;
import com.redis.data.route.interfaces.ProductRouteService;
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
public class ProductRoute implements ProductRouteService {

    private final ProductRepo productRepo;

    @Override
    public ResponseEntity save(ProductDTO dto) {
        Product product = new Product();
        product.setId(Utils.getRandomID());
        product.setUuid(UUID.randomUUID().toString());
        product.setName(dto.getName());
        product.setQuantity(dto.getQuantity());
        product.setPrice(dto.getPrice());
        Product p = productRepo.save(product);
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.name(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                p));
    }

    @Override
    public ResponseEntity update(Product dto) {
        Product p = productRepo.update(dto);
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.name(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                p));
    }

    @Override
    public ResponseEntity findByID(Long id) {
        Product p = productRepo.findProductByID(id);
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
        List<Product> list = productRepo.findAll();
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
        String message = productRepo.deleteProduct(id);
        return ResponseEntity.ok().body(new ApiResponse(
                HttpStatus.OK.name(),
                MessageUtils.SUCCESS,
                HttpStatus.OK.value(),
                message));
    }
}
