package org.java.pizza.service;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Pizza;
import org.java.pizza.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
	@Autowired
	private PizzaRepository pizzaRepository;
	
	public List<Pizza> findAll() {
		return pizzaRepository.findAll();
	}
	
	public Pizza save(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}
	public Optional<Pizza> findById(Integer id){
		return pizzaRepository.findById(id);
	}
	public List<Pizza> findByName(String name){
		return pizzaRepository.findByNameContaining(name);
	}
	
	public void delete(Pizza pizza) {
		pizzaRepository.delete(pizza);
	}
}
