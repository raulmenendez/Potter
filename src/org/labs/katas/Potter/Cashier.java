package org.labs.katas.Potter;

public class Cashier {
	
	private BookShopCart shopcart;
	private Discounter discounter;
	
	private static final int PRICE = 8;	
	
	public double calculate(BookShopCart shopcart, Discounter discounter) {
		this.shopcart = shopcart;
		this.discounter = discounter;
		return calculatePriceToApply();
	}
	
	private double calculatePriceToApply() {
		
		if (discounter.isDiscountAvailableFrom(shopcart)){
			return calculatePriceWithDiscount();	
		}else{
			return calculatePriceWithoutDiscount();			
		}
		
	}
	
	private double calculatePriceWithDiscount(){
		return this.discounter.calculateBestDiscountFrom(shopcart);
	}

	private double calculatePriceWithoutDiscount() {
		return this.shopcart.getQuantityOfBooks() * PRICE;
	}

}
