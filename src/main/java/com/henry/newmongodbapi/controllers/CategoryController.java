package com.henry.newmongodbapi.controllers;

import com.henry.newmongodbapi.dto.CategoryDTO;
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
    public ResponseEntity<Category> fetchCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> store(@RequestBody CategoryDTO category) {
        Category cate1 = new Category();

        cate1.setName(category.getName());
        cate1.setDescription(category.getDescription());
        cate1.setStatus(category.getStatus());

        categoryService.insert(cate1);
        return ResponseEntity.ok("Đã thêm danh mục mới!");
    }

    @PutMapping("{categoryId}")
    public ResponseEntity<String> update(@RequestBody Category category, @PathVariable String categoryId) {
        categoryService.update(category, categoryId);
        return ResponseEntity.ok("Đã cập nhật thông tin danh mục!");
    }

    @DeleteMapping("{categoryId}")
    public ResponseEntity<String> delete(@PathVariable String categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok("Đã xóa thành công danh mục này!");
    }
}
