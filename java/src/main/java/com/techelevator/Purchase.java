package com.techelevator;


import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.DateFormatter;

import com.techelevator.Logger.Logger;

public class Purchase {
	private boolean makePurchase = true;

	public Purchase(boolean makePurchase) {
		this.makePurchase = makePurchase;
	}
	
	public Purchase() {
	}
	
	public boolean isMakePurchase() {
		return makePurchase;
	}

	public void setMakePurchase(boolean makePurchase) {
		this.makePurchase = makePurchase;
	}

	public BigDecimal payments (BigDecimal balance, List <VendingItems> items, Scanner scanner) {
		makePurchase = true;
		
		for(VendingItems itemOutput : items) {
			if (itemOutput.getQuantity() == 0)	 {
				System.out.println(itemOutput.getSlot() + " : SOLD OUT");
			} else System.out.println(itemOutput.getSlot() + " : " + itemOutput.getQuantity() + " : $" + itemOutput.getPrice() + " " + itemOutput.getName());
		}
		
		while (makePurchase) {
			System.out.println();
			System.out.println("Current money provided: $" + balance);
			System.out.println();
			System.out.print("Please make your selection >>> ");
			String itemSelected = scanner.nextLine();
			
			boolean found = false; // flags when product is found
			
				for (VendingItems list : items) {
			
					if (list.getSlot().equals(itemSelected.toUpperCase()) && list.getPrice().compareTo(balance) > 0) {
						System.out.println();
						System.out.println("Please add funding for this selection.");
						makePurchase = false;
						found = true;
						return balance;
//						break;
					} else found = false;
					
					if (list.getSlot().equals(itemSelected.toUpperCase()) && list.getQuantity() > 0) {
						balance = balance.subtract(list.getPrice());
						list.dispence();

						System.out.println();
						System.out.println("Thank you for purchasing " + list.getName() + ". " + "Your remaining balance is "
								+ balance + ".");
						System.out.println(list.getSound());
						
						found = true;
						
				// call the logger
						try {
							Logger log = new Logger ("Log.txt");
							String fileOutput = (LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/dd/YYYY HH:mm:ss a")) + 
									" " + list.getName() + " " + list.getSlot() + " $" + balance.add(list.getPrice()) + " $" + balance);
							log.write(fileOutput);
						} catch (IOException e) {
							e.printStackTrace();
						}
				// end of logger
						
				// call the sales report logger
						try {
							Logger log = new Logger ("SalesItemLog.txt");
							String fileOutput = list.getName() + "|" + list.getPrice();
							log.write(fileOutput);
						} catch (IOException e) {
							e.printStackTrace();
						}
				// end of sales report logger
						makePurchase = false;
						found = true;
						return balance;
						
						} else if (list.getSlot().equals(itemSelected.toUpperCase()) && list.getQuantity() == 0) {
						System.out.println();
						System.out.println("Selected item is out of stock.");
						makePurchase = false;
						found = true;
						return balance;
					} 
			}
				if (!found) {
					System.out.println();
					System.out.println("Incorrect selection entered.");
					makePurchase = false;
					found = true;
					return balance;
				}				
		} return balance;
	} 
}
