package com.techelevator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.techelevator.Logger.Logger;

public class Change {

	private BigDecimal dollar = new BigDecimal(1);
	private BigDecimal quarter = new BigDecimal(0.25);
	private BigDecimal dime = new BigDecimal(0.10);
	private BigDecimal nickle = new BigDecimal(0.05);
	private int dollarCount = 0;
	private int quarterCount = 0;
	private int dimeCount = 0;
	private int nickleCount = 0;

	public Change(BigDecimal dollar, BigDecimal quarter, BigDecimal dime, BigDecimal nickle, int dollarCount,
			int quarterCount, int dimeCount, int nickleCount) {
		this.dollar = dollar;
		this.quarter = quarter;
		this.dime = dime;
		this.nickle = nickle;
		this.dollarCount = dollarCount;
		this.quarterCount = quarterCount;
		this.dimeCount = dimeCount;
		this.nickleCount = nickleCount;
	}
	public Change(){}
	
	public void makeChange(BigDecimal balance) {
		
	// call the logger
		try {
			DecimalFormat f = new DecimalFormat("##0.00");
			Logger log = new Logger ("Log.txt");
			String fileOutput = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss a")) + 
								" GIVE CHANGE: $" + f.format(balance) + " $0.00");
			log.write(fileOutput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	// end of logger

			while (balance.compareTo(dollar) >= 0) {
				balance = balance.subtract(dollar);
				dollarCount++;
			}
			while (balance.compareTo(quarter) >= 0) {
				balance = balance.subtract(quarter);
				quarterCount++;
			}
			while (balance.compareTo(dime) >= 0) {
				balance = balance.subtract(dime);
				dimeCount++;
			}
			while (balance.compareTo(nickle) >= 0) {
				balance = balance.subtract(nickle);
				nickleCount++;
			}
		System.out.println("\nYour change is " + dollarCount + " dollar(s), " + quarterCount + " quarter(s), " + dimeCount
				+ " dime(s), and " + nickleCount + " nickle(s).");
	}

}
