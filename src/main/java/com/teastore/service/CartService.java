package com.teastore.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.teastore.dao.ProductDAO;
import com.teastore.entity.Product;

@SessionScope // name: scopedTarget.cartService
@Service
public class CartService {

	@Autowired
	ProductDAO dao;
	
	Map<Integer, Product> map = new HashMap<>();

	public void add(Integer id) {
		Product p = map.get(id);
		if(p == null) {
			p = dao.findById(id);
			p.setQuantity(1);
			map.put(id, p);
		}else {
			p.setQuantity(p.getQuantity()+1);
		}
	}

	public void remove(Integer id) {
		map.remove(id);
	}

	public void update(Integer id, int qty) {
		Product p = map.get(id);
		p.setQuantity(qty);
	}

	public void clear() {
		map.clear();
	}

	public int getCount() {
		Collection<Product> ps = this.getItems();
		int count = 0;
		for(Product p: ps){
			count += p.getQuantity();
		}
		return count;
	}
	public double getAmount() {
		Collection<Product> ps = this.getItems();
		double amount = 0;
		for(Product p: ps){
			amount += p.getQuantity()*p.getUnitPrice()*((100-p.getDiscount())/100);
		}
		return amount;
	}
	public Collection<Product> getItems() {
		return map.values();
	}
	
}