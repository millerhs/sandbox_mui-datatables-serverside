package com.example.demo.services;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.beans.support.SortDefinition;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.demo.models.PageableProps;
import com.example.demo.models.Person;
import com.example.demo.models.SortableProps;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	
	
	public List<Person> getPeople(PageableProps pageable, SortableProps sortable) {
		return getPagedSortedList(personRepository.findAllPeople(), pageable, sortable);
	}
	
	public void exportPeople(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] csvHeader = { "Name", "Age", "Height", "Weight" };
		String[] nameMapping = { "name", "age", "height", "weight" };
		
		csvWriter.writeHeader(csvHeader);
		
		for (Person person : personRepository.findAllPeople()) {
			csvWriter.write(person, nameMapping);
		}
		
		csvWriter.close();
	}
	
	private List<Person> getPagedSortedList(List<Person> people, PageableProps pageable, SortableProps sortable) {
		PagedListHolder<Person> page = new PagedListHolder<>(people, sortable.buildSortDefinition());
		
		page.resort();
		page.setPage(pageable.getPageNumber());
		page.setPageSize(pageable.getPageSize());
		
		return page.getPageList();
	}
	
}
