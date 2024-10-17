package com.spring.curd.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.curd.application.dao.ProductDao;
import com.spring.curd.application.model.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao dao;
	
	public void addNewProduct(Product product) {
		dao.createProduct(product);
	}
	
	public List<Product> showAllProducts(){
		return dao.getAllProducts();
	}
	
	public void removeProduct(int pid) {
		dao.deleteProduct(pid);
	}
	
	public void editProduct(Product product) {
		dao.updateProduct(product);
	}
	
	public Product getSingleProduct(int pid) {
		return dao.getProduct(pid);
	}
}
