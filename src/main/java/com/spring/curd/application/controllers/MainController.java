package com.spring.curd.application.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.curd.application.model.Product;
import com.spring.curd.application.services.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	@Autowired
	private ProductService dao;
	
	@RequestMapping(path={"/home","/"}, method = RequestMethod.GET)
	public String home(Model m) {
		List<Product> products = dao.showAllProducts();
		m.addAttribute("products",products);
		System.out.println(products);
		return "home";
	}
	
	@RequestMapping("/addproduct")
	public String addProduct() {
		return "add_product_form";
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("image");
    }
	
	@RequestMapping(path="/addproductprocess", method=RequestMethod.POST)
	public RedirectView addNewProductHandler(@ModelAttribute Product product, @RequestParam("image") MultipartFile file, HttpServletRequest request, HttpSession s) {
		System.out.println("Working");
		RedirectView rv = new RedirectView();
		if(!file.isEmpty()) {
			byte[] data;
			try {
				data = file.getBytes();
				String path = s.getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"resources"+File.separator+"images"+File.separator+file.getOriginalFilename();
				System.out.println(path);
				FileOutputStream fos = new FileOutputStream(path);
				fos.write(data);
				fos.close();
				product.setImage(file.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		System.out.println(product);
		dao.addNewProduct(product);
		rv.setUrl(request.getContextPath()+"/");
		
		return rv;
	}
	
	@RequestMapping("/deleteproduct/{pid}")
	public RedirectView deleteProduct(@PathVariable("pid") int pid, HttpServletRequest request) {
		RedirectView rv = new RedirectView();
		
		System.out.println(pid);
		dao.removeProduct(pid);
		rv.setUrl(request.getContextPath()+"/");
		
		return rv;
	}
	
	@RequestMapping("/{pid}")
	public String updateProduct(@PathVariable("pid") int pid, Model m) {
		System.out.println(pid);
		Product product = dao.getSingleProduct(pid);
		m.addAttribute("product",product);
		System.out.println(product);
		return "update_product_form";
	}
	
	@RequestMapping(path="updateproductprocess", method=RequestMethod.POST)
	public RedirectView updateProductProcess(@ModelAttribute Product product, @RequestParam("image") MultipartFile file, HttpServletRequest request, HttpSession s) {
		RedirectView rv = new RedirectView();
		if(!file.isEmpty()) {
			byte[] data;
			try {
				data = file.getBytes();
				String path = s.getServletContext().getRealPath("/")+"WEB-INF"+File.separator+"resources"+File.separator+"images"+File.separator+file.getOriginalFilename();
				System.out.println(path);
				FileOutputStream fos = new FileOutputStream(path);
				fos.write(data);
				fos.close();
				product.setImage(file.getOriginalFilename());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			Product p = dao.getSingleProduct(product.getId());
			product.setImage(p.getImage());
		}
		System.out.println(product);
		dao.editProduct(product);
		rv.setUrl(request.getContextPath()+"/");
		
		return rv;
	} 
	
}
