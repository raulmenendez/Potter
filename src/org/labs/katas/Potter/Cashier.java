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
		
		//Si tots els llibres son el mateix no fa falta calcular cap descompte, el preu es l'original.
		if (discountNotAvailable()){
			return calculatePriceWithoutDiscount();
		}else{
			return calculatePriceWithDiscount();			
		}
		
	}
	
	private double calculatePriceWithDiscount(){
		return this.discounter.calculateBestDiscountFrom(shopcart);
	}

	private boolean discountNotAvailable() {
		return this.shopcart.getDifferentBookSeries() == 1;
	}
	
	private int calculatePriceWithoutDiscount() {
		return this.shopcart.getQuantityOfBooks() * PRICE;
	}

}
