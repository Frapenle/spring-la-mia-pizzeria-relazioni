package org.java.pizza;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.java.pizza.pojo.Pizza;
import org.java.pizza.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLaMiaPizzeriaCrudApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringLaMiaPizzeriaCrudApplication.class, args);
	}
	
	@Autowired
	private PizzaService pizzaService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub

		List<Pizza> pizza = Arrays.asList(
				new Pizza("Margherita", "Pomodoro e mozzarella", "https://primochef.it/wp-content/uploads/2019/08/SH_pizza_fatta_in_casa-640x350.jpg.webp", new BigDecimal("8.00")),
				new Pizza("Diavola", "Pomodoro, mozzarella, salame piccante", "https://www.iffco.it/sites/default/files/styles/free_crop/public/img/recipe/gran-cucina-pizza-diavola.jpg?h=de92a0b7&itok=eC0EvTVI", new BigDecimal("12.00")),
				new Pizza("Napoli", "Pomodoro, mozzarella, acciughe", "https://www.donnamoderna.com/content/uploads/2021/08/pizza-napoli-830x625.jpg", new BigDecimal("10.50")),
				new Pizza("Ortolana", "Pomodoro, mozzarella e verdure", "https://www.demetrafood.it/storage/media/recipes/recipe_images/000088.jpg", new BigDecimal("15.00")),
				new Pizza("Carbonara", "Pomodoro, mozzarella, uovo e guanciale", "https://www.pizzatales.it/wp50/wp-content/uploads/2021/10/la-pizza-alla-carbonara.jpg", new BigDecimal("13.50"))
			);
		for (Pizza piz : pizza) {
			pizzaService.save(piz);
		}
		
	}

}
