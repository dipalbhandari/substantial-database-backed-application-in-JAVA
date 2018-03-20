//package com.fileWriter;

//import java.io.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ProductJsonWriter {
	
	public void jsonConverter(List<Product> products) {
		
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		File jsonOutput = new File("Products.json");
		
		PrintWriter jsonPrintWriter = null;
		
		try {
			jsonPrintWriter = new PrintWriter(jsonOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		for(Product aProduct : products) {
			// Use toJson method to convert Person object into a String
			String personOutput = gson.toJson(aProduct); 
			jsonPrintWriter.write(personOutput + "\n");
		}
		
		jsonPrintWriter.close();
	}
}
