package com.henry.newmongodbapi.services;

import com.henry.newmongodbapi.models.Category;
import com.henry.newmongodbapi.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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

    public Category getById(String id) {
        return categoryRepository.findById(id).isPresent() ? categoryRepository.findById(id).get() : null;
    }

    public void insert(Category category) {
        categoryRepository.save(category);
    }

    public void update(Category category, String id) {
        Optional<Category> currentCategory = categoryRepository.findById(id);
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

    public void delete(String id) {
        categoryRepository.deleteById(id);
    }
}
