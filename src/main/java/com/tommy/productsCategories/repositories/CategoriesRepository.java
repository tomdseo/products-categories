package com.tommy.productsCategories.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tommy.productsCategories.models.CategoriesModel;

public interface CategoriesRepository extends CrudRepository<CategoriesModel, Long> {
	List<CategoriesModel> findByNameNotIn(List<String> names);
}


