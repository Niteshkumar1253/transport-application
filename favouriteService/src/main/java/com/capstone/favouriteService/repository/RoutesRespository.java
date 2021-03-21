package com.capstone.favouriteService.repository;

import com.capstone.favouriteService.model.Routes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutesRespository extends MongoRepository<Routes, String> {

}
