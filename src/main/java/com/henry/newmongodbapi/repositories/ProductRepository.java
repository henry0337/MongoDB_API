package com.henry.newmongodbapi.repositories;

import com.henry.newmongodbapi.models.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
}
