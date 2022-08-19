package com.example.demo.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.PageableProps;
import com.example.demo.models.Person;
import com.example.demo.models.SortableProps;
import com.example.demo.services.PersonService;

@Controller()
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
	
	@Autowired
	PersonService personService;

	@GetMapping(value = "/people")
	public ResponseEntity<List<Person>> getPeople(PageableProps pageable, SortableProps sortable) {
		return ResponseEntity.ok(personService.getPeople(pageable, sortable));
	}
	
	@GetMapping(value = "/people/export")
	public void exportPeople(HttpServletResponse response, PageableProps pageable, SortableProps sortable) throws IOException {
		personService.exportPeople(response);
	}
	
}
