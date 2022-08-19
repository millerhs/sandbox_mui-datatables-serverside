package com.example.demo.models;

import lombok.Data;

@Data
public class PageableProps {

	private int pageNumber = 1;
	
	private int pageSize = 5;
	
}
