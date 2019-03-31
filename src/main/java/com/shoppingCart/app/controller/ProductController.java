package com.shoppingCart.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.shoppingCart.app.exception.ProductNotFoundException;
import com.shoppingCart.app.model.Product;
import com.shoppingCart.app.service.ProductService;


@RestController
@RequestMapping(value="/",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProductController {

	@Autowired
	ProductService productService;
	
	public void test() {
		System.out.println(":::::::::Inside Test");
	}
	@RequestMapping("/")
	public ModelAndView welcome(Model model) {
//		model.put("message", "hello");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Product>> getProducts() throws ProductNotFoundException {
		System.out.println("::::::Inside  /products ::::::::");
		List<Product> products = productService.findAll();
		return new ResponseEntity<List<Product>> (products, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/products/{idProduct}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Product> getBy(@PathVariable("idProduct") Long idProduct) throws ProductNotFoundException  {
		Product product = productService.findBy(idProduct);
		return new ResponseEntity<Product> (product, HttpStatus.OK);
	}

	@RequestMapping(value = "/products?description={productDescription}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Product> getByDescription(@PathVariable("productDescription") String description) throws ProductNotFoundException  {
		Product product = productService.findBy(description);
		return new ResponseEntity<Product> (product, HttpStatus.OK);
	}

	@RequestMapping(value = "/products?category={catogoryDescription}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Product>> getByCategory(@RequestParam("catogoryDescription") String category) throws ProductNotFoundException  {
		List<Product> products = productService.findByCategory(category);
		return new ResponseEntity<List<Product>> (products, HttpStatus.OK);
	}
}
