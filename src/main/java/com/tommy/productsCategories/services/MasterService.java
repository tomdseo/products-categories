package com.tommy.productsCategories.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tommy.productsCategories.models.CategoriesModel;
import com.tommy.productsCategories.models.ProductsModel;
import com.tommy.productsCategories.repositories.CategoriesRepository;
import com.tommy.productsCategories.repositories.ProductsRepository;

@Service
public class MasterService {
	@Autowired
	private CategoriesRepository categoriesRepository;
	@Autowired
	private ProductsRepository productsRepository;
	
	//creates a product
	public ProductsModel addProduct(ProductsModel p) {
		return productsRepository.save(p);
	}
	
	//creates a category
	public CategoriesModel addCategory(CategoriesModel c) {
		return categoriesRepository.save(c);
	}
	
	//retrieves a product by id (singular)
	public ProductsModel showProduct(Long id) {
		Optional<ProductsModel> optionalProducts = productsRepository.findById(id);
		if(optionalProducts.isPresent()) {
			return optionalProducts.get();
		}
		return null;
	}
	
	//retrieves a category by id (singular)
		public CategoriesModel showCategory(Long id) {
			Optional<CategoriesModel> optionalCategories = categoriesRepository.findById(id);
			if(optionalCategories.isPresent()) {
				return optionalCategories.get();
			}
			return null;
		}
		
	//returns all the categories
	public List<CategoriesModel> allCategories() {
		return (List<CategoriesModel>) categoriesRepository.findAll();
	}
	
	//returns all the products
		public List<ProductsModel> allProducts() {
			return (List<ProductsModel>) productsRepository.findAll();
		}
		
	//returns all non-added categories
	public List<CategoriesModel> nonCategories(ProductsModel p) {
		List<CategoriesModel> currentCats = p.getCategories();
		List<String> currentCatsNames = new ArrayList<String>();
		
		if(currentCats.size() == 0) {
			currentCatsNames.add("");
		} else {
			for(CategoriesModel cat : currentCats) {
				currentCatsNames.add(cat.getName());
			}
		}
		return categoriesRepository.findByNameNotIn(currentCatsNames);
	}
	
	//returns all non-added products
	public List<ProductsModel> nonProducts(CategoriesModel c) {
		List<ProductsModel> currentProducts = c.getProducts();
		List<String> currentProductsNames = new ArrayList<String>();
		
		if(currentProducts.size() == 0) {
			currentProductsNames.add("");
		} else {
			for(ProductsModel product : currentProducts) {
				currentProductsNames.add(product.getName());
			}
		}
		
		return productsRepository.findByNameNotIn(currentProductsNames);
	}
	
	//adds a new category to the product
	public void addCategoryToProduct(CategoriesModel c, ProductsModel p) {
		List<CategoriesModel> listCategories = p.getCategories();
		listCategories.add(c);
		productsRepository.save(p);
	}
	
	//adds a new product to the category 
	public void addProductToCategory(ProductsModel p, CategoriesModel c) {
		List<ProductsModel> listProducts = c.getProducts();
		listProducts.add(p);
		categoriesRepository.save(c);
		}
}


