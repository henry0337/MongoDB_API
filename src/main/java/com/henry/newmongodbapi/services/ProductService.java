package com.henry.newmongodbapi.services;

import com.henry.newmongodbapi.models.Product;
import com.henry.newmongodbapi.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(ObjectId id) {
        return productRepository.findById(id).isPresent() ? productRepository.findById(id).get() : null;
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public void update(Product product, ObjectId id) {
        Optional<Product> currentProduct = productRepository.findById(id);
        if (currentProduct.isPresent()) {
            Product otherProduct = currentProduct.get();
            otherProduct.setName(product.getName());
            otherProduct.setImage(product.getImage());
            otherProduct.setDescription(product.getDescription());
            otherProduct.setStatus(product.getStatus());
            productRepository.save(otherProduct);
        } else {
            throw new NoSuchElementException("Không tìm thấy sản phẩm cần cập nhật");
        }
    }

    public void delete(ObjectId id) {
        productRepository.deleteById(id);
    }
}
