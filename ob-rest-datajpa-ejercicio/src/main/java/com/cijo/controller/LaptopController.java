package com.cijo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cijo.model.Laptop;
import com.cijo.repository.LaptopRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

@Slf4j
@RestController
@RequestMapping("/api/v1.0")
public class LaptopController {
	
	@Autowired
	private LaptopRepository repository;
	

	@GetMapping("/laptops")
	public List<Laptop> findAll() {
		return repository.findAll();
	}
	
	@ApiOperation("Buscar un Laptop por la clave primaria tipo Long")
	@GetMapping("/laptop/{id}")
	public ResponseEntity<?> findById(@ApiParam("Clave primaria de tipo long") @PathVariable Long id) {
		Optional<Laptop> laptopOpt = repository.findById(id);
		
		// Si encuentra el libro lo devuelve si no devuelve un 404
		if(laptopOpt.isPresent()) {
			return ResponseEntity.ok(laptopOpt.get());
		}else {
			// Construye una respuesta Not Found 404
			return ResponseEntity.notFound().build();
		}
		
		// Opcion con Lambdas
		//return bookOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping("/laptop")
	public ResponseEntity<Laptop> create(@RequestBody Laptop laptop) {
		if(laptop.getId() != null) { 
			log.warn("trying to create a book with id");
			return ResponseEntity.badRequest().build();
		}
		Laptop resutl = repository.save(laptop); 
		return ResponseEntity.ok(resutl); 
	}
	
	@PutMapping("/laptops")
	public ResponseEntity<?> update(@RequestBody Laptop laptop){
		
		if(laptop.getId() == null) {
			log.warn("trying to update a non existent Laptop");
			return ResponseEntity.badRequest().build();
		}
		if(!repository.existsById(laptop.getId())) {
			log.warn("trying to update a non existent Laptop");
			return ResponseEntity.notFound().build();
		}
		
		Laptop result = repository.save(laptop);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/laptop/{id}")
	public ResponseEntity<Laptop> delete(@PathVariable Long id){
		if(!repository.existsById(id)) {
			log.warn("trying to delete a non existent laptop");
			return ResponseEntity.notFound().build();
		}
		
		repository.deleteById(id);
		
		return ResponseEntity.notFound().build();
	}
	
	@ApiIgnore
	@DeleteMapping("/laptops")
	public ResponseEntity<Laptop> deleteAll(){
		repository.deleteAll();
		return ResponseEntity.noContent().build();
	}
	

}
