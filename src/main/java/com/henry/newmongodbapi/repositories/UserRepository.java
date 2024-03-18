package com.henry.newmongodbapi.repositories;

import com.henry.newmongodbapi.models.Role;
import com.henry.newmongodbapi.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);

    User findByRole(Role role);
}
