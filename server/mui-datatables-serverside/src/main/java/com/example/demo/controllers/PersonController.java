package com.example.demo.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;

@Controller()
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {
	
	@Autowired
	PersonService personService;

	@GetMapping(value = "/people")
	public ResponseEntity<Page<Person>> getPeople(Pageable pageable, Sort sort, String search) {
		return ResponseEntity.ok(personService.getPeople(pageable, sort, search));
	}
	
	@GetMapping(value = "/people/export")
	public void exportPeople(HttpServletResponse response, Sort sort, String search) throws IOException {
		personService.exportPeople(response);
	}
	
}
