package com.product.dao;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.product.model.ProductModel;

@Component
public class ProductDao{
   
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Transactional
	public void createProduct(ProductModel productModel) {
		this.hibernateTemplate.saveOrUpdate(productModel);
		
	}

	 public List<ProductModel> getProducts() {
	 List<ProductModel> products=this.hibernateTemplate.loadAll(ProductModel.class);
		return products;
	}
    @Transactional
	public ProductModel deleteProduct(int pid) {
	ProductModel p=this.hibernateTemplate.load(ProductModel.class, pid);
	this.hibernateTemplate.delete(p);
	return p;	 
	}
    public ProductModel getProduct(int pid) {
    	return this.hibernateTemplate.get(ProductModel.class, pid);
    }

}
