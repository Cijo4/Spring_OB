package com.cijo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Clase Laptop del ejercicio OB") // documentar Swagger
@Entity
@Table(name= "books")
@Data @NoArgsConstructor @AllArgsConstructor
public class Laptop {

	@ApiModelProperty("Clave primaria de la clase Laptop") // Documentar Swagger
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String marca;
	private String modelo;
	private Integer almacenamiento;
	private Integer ram;
}
