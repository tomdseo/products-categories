package com.tommy.productsCategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tommy.productsCategories.models.ProductsModel;

public interface ProductsRepository extends CrudRepository<ProductsModel, Long> {
	List<ProductsModel> findByNameNotIn(List<String> names);
}


