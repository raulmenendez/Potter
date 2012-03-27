package org.labs.katas.Potter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Cashier {
	
	private BookShopCart shopcart;
	
	private static final int PRICE = 8;	
	private static final double DISCOUNT_2 = 0.05;
	private static final double DISCOUNT_3 = 0.10;
	private static final double DISCOUNT_4 = 0.20;
	private static final double DISCOUNT_5 = 0.25;
	
	public double calculate(BookShopCart shopcart) {
		
		double totalAmount = 0.0;
		
		this.shopcart = shopcart;
		
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
		
		double finalPrice=0.0;		
		int setsOfBooks = shopcart.getDifferentBookSeries();
					
		//Mentre quedin llibres al ShopCart
		//1. 8 llibres = 5 + 3 vs 4*2 (best offer!)
		//Emmagatzemar els diferents cistells [51.6, 51.2,...] 
		ArrayList<Object> discountSet = new ArrayList<Object>();
		
		//Quans llibres diferents tenim?. Si tenim 5 podem aplicar com a minim la oferta de 5!
		//Si el llibre que queda es 1 no fa falta aplicar cap descompte ja que no hi ha descompte aplicable.
		while (setsOfBooks>1){
			//Fem copia del carret per iterar i esborrar els elements iterats sense fer malbé el carret de la compra.
			HashMap<String, Integer> shopCartCopy = new HashMap<String, Integer>();
			shopCartCopy.putAll(shopcart.getBooksShopCart());
			
			//La primera iteracio ve donada pels diferents llibres que tenim. Absurd mira descompte de 5 si només tenim 4 llibres diferents(!)
			double discountPrice = calculateDiscountForASetOfBooks(shopCartCopy, setsOfBooks);
			
			//Guardem el descompte aplicat
			discountSet.add(discountPrice);	
			
			//Passem a mirar una iteració basada en un llibre menys per veure el seu preu 
			setsOfBooks -= 1;			
		}
		//Mirem d'obtenir el millor descompte possible. 
		finalPrice = getBestPriceFromAllDiscounts(discountSet);
		return finalPrice;
	}
	
	private double getBestPriceFromAllDiscounts(ArrayList<Object> prices) {
		
		double bestDiscount = calculatePriceWithoutDiscount();		
		
		for(Object price:prices){
			double currentDiscountAvailable = ((Double)price).doubleValue();
			if (bestDiscount>currentDiscountAvailable){
				//tenim un descompte millor que podem aplicar. 
				bestDiscount = currentDiscountAvailable;
			}
		}
		
		return bestDiscount;
	}

	private double calculateDiscountForASetOfBooks(HashMap<String, Integer> shopCart, int maxDiscountSetToApply) {
		
		double finalPrice = 0.0;					
		
		//Mentre quedin llibres al ShopCart, hem de mirar el descompte que podem oferir (a partir del Set màxim de llibres diferents per Sèrie)
		while (shopCart.isEmpty() == false){			
			
			int differentBooks = 0;			
			double originalPrice = maxDiscountSetToApply*PRICE;
			double partialPrice = 0.0;
			int elements = maxDiscountSetToApply;
			
			//Agafem el llibres diferents del ShopCart			
			for(Iterator<String> it = shopCart.keySet().iterator();elements>0;){
				String book = it.next();
				int quantityBooksOfThisType = shopCart.get(book);
				//Marquem que es un llibre diferent de la serie 
				differentBooks = differentBooks+1;
				//Restem aquest llibre de la sèrie 
				quantityBooksOfThisType = quantityBooksOfThisType - 1;
				//Mirem si el l'últim llibre de la sèrie
				if (quantityBooksOfThisType == 0){
					//El.liminen aquesta serie del ShopCart
					it.remove();	
				}else{
					//Refresquem el quantitat amb un llibre menys perquè l'hem tret del Shopcart.
					shopCart.put(book, quantityBooksOfThisType);
				}	
				elements = elements - 1;	
			}	
		
			partialPrice = originalPrice - (originalPrice * discountToApply(differentBooks));					
			finalPrice = finalPrice +  partialPrice;
			
			//Marquem el Set de descompte màxim que es podria fer per llibres diferents de la Sèrie (5, 4, 3? etc...)
			//Si la mida del shopCart es igual o més gran es manté el maxDiscountSet per mirar de treure lo màxim possible.
			int shopCartSize = shopCart.keySet().size();
			if (maxDiscountSetToApply > shopCartSize){ 
				maxDiscountSetToApply = shopCartSize;	
			}
			
		}			
		return finalPrice;
	}
	

	
	private boolean discountNotAvailable() {
		return this.shopcart.getDifferentBookSeries() == 1;
	}
	
	private double discountToApply(int differentBooksToBuy) {
		double discountToApply = 0.0;

		if (differentBooksToBuy == 2){
			discountToApply = DISCOUNT_2;
		}
		if (differentBooksToBuy == 3){				
			discountToApply = DISCOUNT_3;				
		}		
		if (differentBooksToBuy == 4){				
			discountToApply = DISCOUNT_4;				
		}			
		if (differentBooksToBuy == 5){				
			discountToApply = DISCOUNT_5;				
		}
		return discountToApply;
	}
	
	private int calculatePriceWithoutDiscount() {
		return this.shopcart.getQuantityOfBooks() * PRICE;
	}

}
