package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.view.Menu;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Sales Report";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT };

	
	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION, ""};

	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() throws FileNotFoundException {
		List<VendingItems> items = new ArrayList<>();

// Identifying and connecting to input file
		Scanner scanner = new Scanner(System.in);

		System.out.print("Which vending machine would you like? >>> ");
		String vendingInput = scanner.nextLine();

		File vendingFile = new File(vendingInput);
		Scanner fileScanner = new Scanner(vendingFile);

// Read file and creates a list of description, slot, name, price, and quantity
		while (fileScanner.hasNext()) {

			String itemLine = fileScanner.nextLine();
			String[] itemArray = itemLine.split("\\|");

			String slot = itemArray[0];
			String name = itemArray[1];
			BigDecimal price = new BigDecimal(itemArray[2]);
			String type = itemArray[3];

			if (type.equals("Chip")) {
				Chips chip = new Chips(slot, name, price, 5, "Crunch Crunch, Yum!");
				items.add(chip);
			} else if (type.equals("Drink")) {
				Drink drink = new Drink(slot, name, price, 5, "Glug Glug, Yum!");
				items.add(drink);
			} else if (type.equals("Candy")) {
				Candy candy = new Candy(slot, name, price, 5, "Munch Munch, Yum!");
				items.add(candy);
			} else if (type.equals("Gum")) {
				Gum gum = new Gum(slot, name, price, 5, "Chew Chew, Yum!");
				items.add(gum);
			}
		}
		
		BigDecimal balance = new BigDecimal(0);
		FeedMoney feedMoney = new FeedMoney();
		Purchase purchase = new Purchase();
		SalesReport salesReport = new SalesReport();

// Menus Start
		while (true) {
			System.out.println();
			System.out.println("-----------------------------------------");
			System.out.println("|<|>|<|>|<|>|<| Main Menu |>|<|>|<|>|<|>|");
			System.out.println("-----------------------------------------");
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

// Display Items Option
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				
				System.out.println("\n------------ Available Items-------------\n");
				for (VendingItems itemOutput : items) {
					if (itemOutput.getQuantity() == 0) {
						System.out.println(itemOutput.getSlot() + " : SOLD OUT");
					} else
						System.out.println(
								itemOutput.getSlot() + " : " + itemOutput.getQuantity() + " : $" + itemOutput.getPrice() + " " + itemOutput.getName());
				}

// Purchase	Option
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

// Purchase Menu Start
				while (true) {
					System.out.println();
					System.out.println("-----------------------------------------");
					System.out.println("|<|>|<|>|<|>| Purchase Menu |<|>|<|>|<|>|");
					System.out.println("-----------------------------------------");
					System.out.println("Current money provided: $" + balance);
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					
					

// Feed Money (Purchase Menu)
					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						System.out.println("\n-------------- Feed Money ---------------");
						balance = feedMoney.moneyInput(scanner,balance);

// Product Selection (Purchase Menu)
					}
					else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						System.out.println("\n------------ Select Product --------------\n");
						balance = purchase.payments(balance, items, scanner);
						
// Exit (Purchase Menu)
					} else {
						// give back change
						System.out.println("\n-------------- Your Change --------------");
						Change change = new Change();
						change.makeChange(balance);
						balance = new BigDecimal(0);
						break;
					}
				}

// Exit				
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				break;

// Sales Report
			} else if (choice.equals(MAIN_MENU_OPTION_SALES_REPORT)) {
				salesReport.report("SalesItemLog.txt");
//				break;
			}
		}
		fileScanner.close();
	}

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println();
		System.out.println("|=======================================|");
		System.out.println("|<<<<<<<<<<| =============== |>>>>>>>>>>|");
		System.out.println("|==========| Vendo-Matic 800 |==========|");
		System.out.println("|<<<<<<<<<<| =============== |>>>>>>>>>>|");
		System.out.println("|=======================================|");
		System.out.println();

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
