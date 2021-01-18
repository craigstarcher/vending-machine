package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReport {
	
	public void report (String path) {
		
		File inputFile = new File (path); // initiate input file
		String outputFile = "SalesReport " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-YYYY HH.mm.ss")) +".txt"; // identifying output file
//		+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm:ss").toString()
		Map<String, Integer> sales = new HashMap<>(); //create map
		String line = ""; // create line variable
		BigDecimal salesTotal = new BigDecimal(0);
		
		try (Scanner saleScanner = new Scanner (inputFile)) {	// connect to input file
			while (saleScanner.hasNextLine()) { // input file loop
				String saleScannerLine = saleScanner.nextLine();
				String[] saleArray = saleScannerLine.split("\\|");
				line = saleArray[0]; // convert each input line to a String
				salesTotal = new BigDecimal(saleArray[1]).add(salesTotal);
				
				if (!sales.containsKey(line)) { // if map doensn't contain key...
					sales.put(line, 1); // ... adds line as key and gives a count
				} else sales.put(line, sales.get(line) + 1); // ...else overwrites existing key with new count 				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try(PrintWriter dataOutput = new PrintWriter (outputFile)) { // connecting to the output file
			for (Map.Entry<String, Integer> salesLoop : sales.entrySet()) { // looping through map
				dataOutput.println(salesLoop.getKey() + "|" + salesLoop.getValue()); // writing a new line for each map pair to the output file
			}
			dataOutput.println("\n\nTOTAL SALES: $" + salesTotal.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
