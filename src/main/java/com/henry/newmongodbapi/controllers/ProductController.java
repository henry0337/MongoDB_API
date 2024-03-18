package com.henry.newmongodbapi.controllers;

import com.henry.newmongodbapi.models.Product;
import com.henry.newmongodbapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> fetchAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> fetchUserById(@PathVariable ObjectId id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> store(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.ok("Lưu thông tin sản phẩm thành công!");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Product category, @PathVariable ObjectId id) {
        productService.update(category, id);
        return ResponseEntity.ok("Cập nhật thông tin sản phẩm thành công!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable ObjectId id) {
        productService.delete(id);
        return ResponseEntity.ok("Xóa sản phẩm thành công!");
    }
}
