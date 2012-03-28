package org.labs.katas.Potter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Cashier {
	
	private BookShopCart shopcart;
	private Discounter discounter;
	
	private static final int PRICE = 8;	
	
	public double calculate(BookShopCart shopcart, Discounter discounter) {
		
		double totalAmount = 0.0;
		this.shopcart = shopcart;
		this.discounter = discounter;
		
		if (shopcart.hasBooks()){	
			totalAmount = calculatePriceToApply();
		}
		
		return totalAmount;
	}
	
	private double calculatePriceToApply() {
		
		//Si tots els llibres son el mateix no fa falta calcular cap descompte, el preu es l'original.
		if (discountNotAvailable()){
			return calculatePriceWithoutDiscount();
		}else{
			return calculatePriceWithDiscount();			
		}
		
	}
	
	private double calculatePriceWithDiscount(){
		return this.discounter.calculateBestDiscountAvailable(shopcart);
	}

	private boolean discountNotAvailable() {
		return this.shopcart.getDifferentBookSeries() == 1;
	}
	
	private int calculatePriceWithoutDiscount() {
		return this.shopcart.getQuantityOfBooks() * PRICE;
	}

}
