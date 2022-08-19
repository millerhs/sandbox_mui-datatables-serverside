package com.example.demo.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.models.Person;

@Component
public class PersonRepository {

	public List<Person> findAllPeople() {
		List<Person> people = new ArrayList<>();
		
		people.add(Person.builder().name("Goatplanter Fulton").age(23).height(61).weight(156).build());
		people.add(Person.builder().name("Hoggreaser Pressley").age(42).height(67).weight(142).build());
		people.add(Person.builder().name("Oatchaser Banner").age(12).height(56).weight(131).build());
		people.add(Person.builder().name("Pighauler Finch").age(51).height(73).weight(232).build());
		people.add(Person.builder().name("Gumppicker MacNiven").age(72).height(62).weight(190).build());
		people.add(Person.builder().name("Pickupfarmer Hobson").age(32).height(57).weight(173).build());
		people.add(Person.builder().name("Shotgungrazer Neville").age(26).height(70).weight(204).build());
		people.add(Person.builder().name("Goatpusher Duane").age(83).height(67).weight(179).build());
		people.add(Person.builder().name("Chickengreaser Whitehead").age(63).height(64).weight(163).build());
		people.add(Person.builder().name("Sheeptoter MacIntyre").age(15).height(51).weight(95).build());
		
		return people;
	}
	
}
