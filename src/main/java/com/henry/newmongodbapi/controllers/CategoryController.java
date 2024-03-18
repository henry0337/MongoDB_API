package com.henry.newmongodbapi.controllers;

import com.henry.newmongodbapi.models.Category;
import com.henry.newmongodbapi.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/category")
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> fetchAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> fetchUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(categoryService.getByCategoryId(id));
    }

    @PostMapping
    public ResponseEntity<String> store(@RequestBody Category category) {
        categoryService.insert(category);
        return ResponseEntity.ok("Đã thêm danh mục mới!");
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<String> update(@RequestBody Category category, @PathVariable Integer categoryId) {
        categoryService.update(category, categoryId);
        return ResponseEntity.ok("Đã cập nhật thông tin danh mục!");
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> delete(@PathVariable Integer categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok("Đã xóa thành công danh mục này!");
    }
}
