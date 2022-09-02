package com.example.demo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.opencsv.CSVReader;

public class JsonTests {
	
	@ParameterizedTest
	@MethodSource("buildCsvTestStream")
	public void CsvTest(String input, String expected) {
		Assertions.assertTrue(input.contains(expected));
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/com/example/demo/files/CsvTest.csv", numLinesToSkip = 1)
	void CsvTestFileSource(String input, String expected) {
		Assertions.assertTrue(input.contains(expected));
	}
	
	private static Stream<Arguments> buildCsvTestStream() throws IOException {
		List<Arguments> arguments = new ArrayList<>();
		Object test = new File("src/test/resources/CsvTest.csv");
		CSVReader csvReader = new CSVReader(new FileReader(new File("src/test/resources/CsvTest.csv")));
		
		Object[] values = null;
		csvReader.readNext(); // Skip first line
		while ((values = csvReader.readNext()) != null) {
			arguments.add(Arguments.arguments(values));
		}
		
		return arguments.stream();
	}

}
