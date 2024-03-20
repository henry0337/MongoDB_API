package com.henry.newmongodbapi.repositories;

import com.henry.newmongodbapi.models.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
