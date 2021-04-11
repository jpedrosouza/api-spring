package com.study.apispring.repositories;

import org.springframework.data.repository.CrudRepository;

import com.study.apispring.models.ProductModel;

public interface ProductRepository extends CrudRepository<ProductModel, Integer>{
    
}
