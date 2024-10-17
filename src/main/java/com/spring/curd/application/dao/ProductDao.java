package com.spring.curd.application.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.spring.curd.application.model.Product;

@Component
public class ProductDao {
	@Autowired
	HibernateTemplate hibernateTeamplate;
	
	@Transactional
	public void createProduct(Product product) {
		this.hibernateTeamplate.save(product);
	}
	
	public List<Product> getAllProducts(){
		List<Product> products = this.hibernateTeamplate.loadAll(Product.class);
		return products;
	}
	
	@Transactional
	public void deleteProduct(int pid) {
		Product p = this.hibernateTeamplate.load(Product.class, pid);
		this.hibernateTeamplate.delete(p);
	}
	
	public Product getProduct(int pid) {
		
		return this.hibernateTeamplate.get(Product.class, pid);
	}
	
	@Transactional
	public void updateProduct(Product product) {
		this.hibernateTeamplate.update(product);
	}
	
}
