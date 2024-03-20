package com.henry.newmongodbapi.controllers;

import com.henry.newmongodbapi.dto.ProductDTO;
import com.henry.newmongodbapi.models.Category;
import com.henry.newmongodbapi.models.Product;
import com.henry.newmongodbapi.services.CategoryService;
import com.henry.newmongodbapi.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Product>> fetchAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> fetchProductsByTheirId(@PathVariable String id) {
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> store(@RequestBody ProductDTO product) {
        Product prod = new Product();
        prod.setName(product.getName());
        prod.setImage(product.getImage());
        prod.setDescription(product.getDescription());
        prod.setStatus(product.getStatus());

        Category cate0 = categoryService.getById(product.getCategoryId());
        if (cate0 != null) {
            Category cate1 = new Category();
            cate1.set_id(cate0.get_id());
            cate1.setName(cate0.getName());
            cate1.setDescription(cate0.getDescription());
            cate1.setStatus(cate0.getStatus());
            prod.setCategory(cate1);
        } else {
            prod.setCategory(null);
        }

        productService.save(prod);
        return ResponseEntity.ok("Lưu thông tin sản phẩm thành công!");
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@RequestBody Product product, @PathVariable String id) {
        productService.update(product, id);
        return ResponseEntity.ok("Cập nhật thông tin sản phẩm thành công!");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable String id) {
        productService.delete(id);
        return ResponseEntity.ok("Xóa sản phẩm thành công!");
    }
}
