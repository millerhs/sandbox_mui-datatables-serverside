package com.example.demo.models;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.SortDefinition;

import lombok.Data;

@Data
public class SortableProps {
	
	private String sortColumn;
	
	private String sortDirection;
	
	public SortDefinition buildSortDefinition() {
		return new MutableSortDefinition(sortColumn, true, sortDirection.equalsIgnoreCase("ASC"));
	}
	
}
