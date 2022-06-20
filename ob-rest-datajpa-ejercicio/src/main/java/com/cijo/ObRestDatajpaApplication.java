package com.cijo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cijo.model.Laptop;
import com.cijo.repository.LaptopRepository;

@SpringBootApplication
public class ObRestDatajpaApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(ObRestDatajpaApplication.class, args);
		
		LaptopRepository repository = context.getBean(LaptopRepository.class);
		
		Laptop laptop = new Laptop(null,"Lenovo", "Lenovo2", 500, 16);
		Laptop laptop2 = new Laptop(null,"Asus", "Asus Pro", 1000, 32);
		
		repository.save(laptop);
		repository.save(laptop2);
	}

}
