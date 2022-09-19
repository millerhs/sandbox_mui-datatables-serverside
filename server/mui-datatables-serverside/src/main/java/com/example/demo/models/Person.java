package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "PEOPLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
	
	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "height", nullable = false)
	private int height;

	@Column(name = "weight", nullable = false)
	private int weight;
	
}
