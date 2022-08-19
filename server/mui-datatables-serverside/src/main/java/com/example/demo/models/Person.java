package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Person {

	private String name;
	
	private int age;
	
	private int height;
	
	private int weight;
	
}
