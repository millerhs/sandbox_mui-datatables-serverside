package com.example.demo.specifications;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.demo.models.Person;

@Component
public class PersonSpecification {
	
	public static Specification<Person> build(List<String> columns, String search) {
		
		return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
            
            if (search != null &&  !search.trim().equals("")) {
            	if (columns.contains("name")) {
            		predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + search.toLowerCase() + "%"));
            	}
            	if (NumberUtils.isParsable(search)) {
                	if (columns.contains("age")) {
                		predicates.add(criteriaBuilder.equal(root.get("age"), search));
                	}
                	if (columns.contains("height")) {
                		predicates.add(criteriaBuilder.equal(root.get("height"), search));
                	}
                	if (columns.contains("weight")) {
                		predicates.add(criteriaBuilder.equal(root.get("weight"), search));
                	}
            	}
            	
            	// Here we may return an OR because there should be at least one predicate
        		return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            }
            
            // Here we must return an AND because there are no predicates
    		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
		
	}
	
}
