package com.tommy.productsCategories.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tommy.productsCategories.models.CategoriesModel;
import com.tommy.productsCategories.models.ProductsModel;
import com.tommy.productsCategories.services.MasterService;

@Controller
public class MasterController {
	@Autowired
	private MasterService masterService;
	
	private ProductsModel productGlobal;
	
//	private ProductsModel productNext;

	@RequestMapping("/")
	public String redirect() {
		return "redirect:/products/new";
	}
	
	@RequestMapping("/products/new")
	public String productsNew(@ModelAttribute("product") ProductsModel product) {
		return "productsCategories/products-new.jsp";
	}
	
	@RequestMapping(value="/products/new/action", method=RequestMethod.POST)
	public String productsNewAction(@Valid @ModelAttribute("product") ProductsModel product, BindingResult result) {
		if (result.hasErrors()) {
			return "productsCategories/products-new.jsp";
		} else {
			masterService.addProduct(product);
			Long id = product.getId();
			return "redirect:/products/"+id;
		}
	}
	
	@RequestMapping(value="/products/{id}", method=RequestMethod.GET)
	public String productsId(@PathVariable("id") Long id, Model model) {
	  ProductsModel product = masterService.showProduct(id);
	  model.addAttribute("product", product);
	  model.addAttribute("nonCategories", masterService.nonCategories(product));
	  return "productsCategories/products-id.jsp";
	}
	
	@RequestMapping(value="/products/category/add/{id}", method=RequestMethod.POST)
	public String addCategoryToProduct(@PathVariable("id") Long product_id, @RequestParam("category_add") Long category_id) {
		masterService.addCategoryToProduct(masterService.showCategory(category_id), masterService.showProduct(product_id));
		return "redirect:/products/"+product_id;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping("/categories/new")
	public String categoriesNew(@ModelAttribute("category") ProductsModel category) {
		return "productsCategories/categories-new.jsp";
	}
	
	@RequestMapping(value="/categories/new/action", method=RequestMethod.POST)
	public String categoriesNewAction(@Valid @ModelAttribute("category") CategoriesModel category, BindingResult result) {
		if (result.hasErrors()) {
			return "productsCategories/categories-new.jsp";
		} else {
			masterService.addCategory(category);
			Long id = category.getId();
			return "redirect:/categories/"+id;
		}
	}
	
	@RequestMapping(value="/categories/{id}", method=RequestMethod.GET)
	public String categoriesId(@PathVariable("id") Long id, Model model) {
	  CategoriesModel category = masterService.showCategory(id);
	  model.addAttribute("category", category);
	  model.addAttribute("nonProducts", masterService.nonProducts(category));
	  return "productsCategories/categories-id.jsp";
	}
	
	@RequestMapping(value="/categories/product/add/{id}", method=RequestMethod.POST)
	public String addProductToCategory(@PathVariable("id") Long category_id, @RequestParam("product_add") Long product_id) {
		masterService.addProductToCategory(masterService.showProduct(product_id), masterService.showCategory(category_id));
		return "redirect:/categories/"+category_id;
	}
}