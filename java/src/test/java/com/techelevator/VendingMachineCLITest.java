package com.techelevator;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.view.Menu;

public class VendingMachineCLITest {
	
	File file = new File("vendingmachine.csv");
	Menu menu = new Menu(System.in, System.out);
	VendingMachineCLI cli = new VendingMachineCLI(menu);
	
	List<VendingItems> items = new ArrayList<>();


	@Test
	public void test_vendingmachinecli_add_balance() {
//		FeedMoney feedMoney = new FeedMoney();
//		
//		feedMoney.moneyInput(scanner, 5);
//		feedMoney.billInsterted(5);
//		balance.equals(10);
//		
//		assertEquals(feedMoney.getBalance(5), 10);
	}
	
	
}

