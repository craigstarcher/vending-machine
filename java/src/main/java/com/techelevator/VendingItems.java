package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

abstract public class VendingItems {

	private String slot;
	private String name;
	private BigDecimal price;
	private int quantity;
	private String sound;
	
	public VendingItems(String slot, String name, BigDecimal price, int quantity, String sound) {
		this.slot = slot;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.sound = sound;
	}
	
	public VendingItems() {
	}
	
	public String getSound() {
		return sound;
	}
	
	public String getSlot() {
		return slot;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public void dispence () {
		quantity --;
	}
}
