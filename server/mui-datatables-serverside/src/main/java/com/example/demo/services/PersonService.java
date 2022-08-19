package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
import org.springframework.stereotype.Service;

import com.example.demo.models.PageableProps;
import com.example.demo.models.Person;
import com.example.demo.models.SortableProps;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	public List<Person> getAllPeople(PageableProps pageable, SortableProps sortable) {
		return getPagedSortedList(personRepository.findAllPeople(), pageable, sortable);
	}
	
	private List<Person> getPagedSortedList(List<Person> people, PageableProps pageable, SortableProps sortable) {
		PagedListHolder<Person> page = new PagedListHolder<>(people, sortable.buildSortDefinition());
		
		page.resort();
		page.setPage(pageable.getPageNumber());
		page.setPageSize(pageable.getPageSize());
		
		return page.getPageList();
	}
	
}
