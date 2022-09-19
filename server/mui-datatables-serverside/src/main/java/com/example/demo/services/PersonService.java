package com.example.demo.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.specifications.PersonSpecification;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	public Page<Person> getPeople(Pageable pageable, Sort sort, String search) {
		// Realistically this would be provided in the API request
		List<String> columns = Arrays.asList("name", "age", "height", "weight");
		
		return personRepository.findAll(
				PersonSpecification.build(columns, search), 
				PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));
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
		
		for (Person person : personRepository.findAll()) {
			csvWriter.write(person, nameMapping);
		}
		
		csvWriter.close();
	}
	
}
