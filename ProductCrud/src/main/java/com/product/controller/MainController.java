package com.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import com.product.dao.ProductDao;
import com.product.model.ProductModel;
 
@Controller
public class MainController {
	@Autowired
	private ProductDao productDao;
	
    @RequestMapping("/")
	public String home(Model m) {
    	List<ProductModel> products = productDao.getProducts();
    	m.addAttribute("product", products);
		return "index";	
	}
    
    @RequestMapping("/add-product")
    public String addProduct(Model m){
    	m.addAttribute("title","Add Product");
		return "add_product_form";	
    }
    @RequestMapping(value = "/handel-product",method = RequestMethod.POST)
    public RedirectView handelProduct(@ModelAttribute ProductModel productModel, HttpServletRequest request) {
    	
    	System.out.println(productModel);
        productDao.createProduct(productModel);
    	RedirectView redirectView=new RedirectView();
    	redirectView.setUrl(request.getContextPath()+ "/");
		return redirectView;
    }
    @RequestMapping("/delete/{productId}")
    public RedirectView deleteProduct(@PathVariable("productId") int productId,HttpServletRequest request) {
    	this.productDao.deleteProduct(productId);
    	RedirectView redirectView=new RedirectView();
    	redirectView.setUrl(request.getContextPath()+"/");
		return redirectView;
    }
    @RequestMapping("/update/{productId}")
    public String updateForm(@PathVariable("productId") int pid,Model model) {
    	ProductModel productModel=this.productDao.getProduct(pid);
    	model.addAttribute("productModel", productModel);
		return "update_form"; 
    	
    }
}
