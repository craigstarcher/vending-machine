package com.techelevator;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import com.techelevator.Logger.Logger;

public class FeedMoney {

	private boolean addFunds = true;

	public FeedMoney(boolean addFunds) {

		this.addFunds = addFunds;
	}

	public FeedMoney() {
	}

	public boolean isAddFunds() {
		return addFunds;
	}

	public void setAddFunds(boolean addFunds) {
		this.addFunds = addFunds;
	}

	
	public BigDecimal moneyInput(Scanner scanner, BigDecimal balance) {
	
		
		while (true) {
			System.out.println();
			System.out.print("Insert bills ($1, $2, $5 or $10) >>> ");
			String billInserted = scanner.nextLine();

			if (billInserted.equals("$1") || billInserted.equals("$2") || billInserted.equals("$5")
					|| billInserted.equals("$10")) {
				billInserted = billInserted.substring(1);
			}
			if (billInserted.equals("1") || billInserted.equals("2") || billInserted.equals("5")
					|| billInserted.equals("10")) {
				balance = balance.add(new BigDecimal(billInserted));
			} else {
				System.out.println("Incorrect value entered.");
				break;
			}

			System.out.println();
			System.out.println("Current money provided: $" + balance);
			
		// call the logger
			try {
				DecimalFormat f = new DecimalFormat("##0.00");
				Logger log = new Logger ("Log.txt");
				String fileOutput = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss a")) +
									" FEED MONEY: $" + (f.format(balance.subtract(new BigDecimal(billInserted)))) + " $" + f.format(balance));
				log.write(fileOutput);
			} catch (IOException e) {
				e.printStackTrace();
			}
		// end of logger
			
			break;
		} return balance;
	}
}
