//package com.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DataConverter {

	public static void main(String[] args) {

		// Create a FlatFileReader object
		FlatFileReader fr = new FlatFileReader();

		/* fr Reads data from the flat file;
		 * Creates Person objects; and
		 */
		ArrayList<Person> personList = fr.readPerson();
		ArrayList<Product> Products = fr.readProduct();
		ArrayList<Customer> readCustomer = fr.readCustomer();
		
		
		
		// Write Person ArrayList into a Json file
		PersonJsonWriter perWriter = new PersonJsonWriter();
		perWriter.jsonConverter(personList);
	
		CustomerJsonWriter customWriter = new CustomerJsonWriter();
		customWriter.jsonConverter(readCustomer);
		
		ProductJsonWriter product= new ProductJsonWriter();
		product.jsonConverter(Products);
		
		

		// Write Person ArrayList into an XML file
	   PersonXMLWriter xmlWriter = new PersonXMLWriter();
		xmlWriter.xmlConverter(personList);
		
		CustomerXMLWriter xmlWriter2 = new CustomerXMLWriter();
			xmlWriter2.xmlConverter(readCustomer);
			
			ProductXMLWriter xmlWriter1 = new ProductXMLWriter();
			xmlWriter1.xmlConverter(Products);
		
		
	}
}
