package org.java.pizza.controller;

import java.util.List;
import java.util.Optional;

import org.java.pizza.pojo.Pizza;
import org.java.pizza.pojo.SpecialOffer;
import org.java.pizza.service.PizzaService;
import org.java.pizza.service.SpecialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PizzaController {
	@Autowired
	private PizzaService pizzaService;
	@Autowired
	SpecialOfferService specialOfferService;
	
	@GetMapping("/")
	public String getIndex(Model model) {
		List<Pizza> pizze = pizzaService.findAll();
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("pizze", pizze);
		return "index";
	}
//	SHOW
	@GetMapping("/pizze/{id}")
	public String show(Model model, @PathVariable("id") Integer id) {
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		List<SpecialOffer> specialOffers = specialOfferService.findAll();
		Pizza pizza = optionalPizza.get();
		model.addAttribute("specialOffers", specialOffers);
		model.addAttribute("pizza", pizza);
		return "pizza";
	}
	
	@PostMapping("/pizze")
	public String getPizzaByName(Model model, @RequestParam(required = false) String name) {
		List<Pizza> pizze = pizzaService.findByName(name);
		System.err.println(pizze);
		model.addAttribute("pizze", pizze);
		model.addAttribute("name", name);
		return "index";
	}
	
//	CREATE
	@GetMapping("/pizze/create")
	public String createPizza(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "create";
	}
	
	@PostMapping("/pizze/create")
	public String storePizza(Model model,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println("Error: " + err.getDefaultMessage());
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			return "create";
		}
		pizzaService.save(pizza);
		return "redirect:/";
	}
	
//	DELETE
	@GetMapping("pizze/delete/{id}")
	public String delete(@PathVariable Integer id) {
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		Pizza pizza = optionalPizza.get();
		pizzaService.delete(pizza);
		
		return "redirect:/";
	}
	
//	UPDATE
	@GetMapping("/pizze/update/{id}")
	public String edit(@PathVariable("id") Integer id,
						Model model) {
		Optional<Pizza> optionalPizza = pizzaService.findById(id);
		Pizza pizza = optionalPizza.get();
		model.addAttribute("pizza", pizza);
		return "update";
	}
	
	@PostMapping("/pizze/update/{id}")
	public String update(
			@PathVariable Integer id,
			@Valid @ModelAttribute Pizza pizza,
			BindingResult bindingResult,
			Model model) {
		if(bindingResult.hasErrors()) {
			for (ObjectError err : bindingResult.getAllErrors())
				System.err.println("Error: " + err.getDefaultMessage());
			model.addAttribute("pizza", pizza);
			model.addAttribute("errors", bindingResult);
			return "update";
		}
		pizzaService.save(pizza);
		return "redirect:/";
	}
	
	@GetMapping("/pizze/{id}/create")
	public String getSpecialOfferCreate(Model model, @PathVariable Integer id) {
		
		Pizza pizza = pizzaService.findById(id).get();
		model.addAttribute("specialOffer", new SpecialOffer());
		model.addAttribute("pizza", pizza);
		
		return "spo-create";
	}
	
	@PostMapping("/pizze/{id}/create")
	public String storeSpecialOffer(
			Model model,
			@ModelAttribute SpecialOffer specialOffer) {
		
		specialOfferService.save(specialOffer);
		return "redirect:/";
	}
}
