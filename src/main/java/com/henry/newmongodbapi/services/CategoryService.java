package com.henry.newmongodbapi.services;

import com.henry.newmongodbapi.models.Category;
import com.henry.newmongodbapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getByCategoryId(Integer id) {
        return categoryRepository.findByCategoryId(id).isPresent() ? categoryRepository.findByCategoryId(id).get() : null;
    }

    public void insert(Category category) {
        categoryRepository.save(category);
    }

    public void update(Category category, Integer id) {
        Optional<Category> currentCategory = categoryRepository.findByCategoryId(id);
        if (currentCategory.isPresent()) {
            Category otherCategory = currentCategory.get();
            otherCategory.setName(category.getName());
            otherCategory.setDescription(category.getDescription());
            otherCategory.setStatus(category.getStatus());
            categoryRepository.save(otherCategory);
        } else {
            throw new NoSuchElementException("Can't find any document relating with the current id");
        }
    }

    public void delete(Integer categoryId) {
        categoryRepository.deleteByCategoryId(categoryId);
    }
}
