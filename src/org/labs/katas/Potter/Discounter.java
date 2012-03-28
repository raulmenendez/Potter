package org.labs.katas.Potter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Discounter {

	private static final int PRICE = 8;	
	
	private static final double DISCOUNT_2 = 0.05;
	private static final double DISCOUNT_3 = 0.10;
	private static final double DISCOUNT_4 = 0.20;
	private static final double DISCOUNT_5 = 0.25;
	
	public double calculateBestDiscountAvailable(BookShopCart shopcart) {
		double finalPrice=0.0;
		int bookSeries = shopcart.getDifferentBookSeries();
					
		//Mentre quedin llibres al ShopCart
		//1. 8 llibres = 5 + 3 vs 4*2 (best offer!)
		//Emmagatzemar els diferents cistells [51.6, 51.2,...] 
		ArrayList<Object> discountSet = new ArrayList<Object>();
		
		//Quans llibres diferents tenim?. Si tenim 5 podem aplicar com a minim la oferta de 5!
		//Si el llibre que queda es 1 no fa falta aplicar cap descompte ja que no hi ha descompte aplicable.
		while (bookSeries>1){
			//Fem copia del carret per iterar i esborrar els elements iterats sense fer malbé el carret de la compra.
			HashMap<String, Integer> shopCartCopy = new HashMap<String, Integer>();
			shopCartCopy.putAll(shopcart.getBooksShopCart());
			
			//La primera iteracio ve donada pels diferents llibres que tenim. Absurd mira descompte de 5 si només tenim 4 llibres diferents(!)
			double discountPrice = this.calculateDiscount(shopCartCopy, bookSeries);
			
			//Guardem el descompte aplicat
			discountSet.add(discountPrice);	
			
			//Passem a mirar una iteració basada en un llibre menys per veure el seu preu 
			bookSeries -= 1;			
		}
		//Mirem d'obtenir el millor descompte possible. 
		finalPrice = getBestPriceFrom(shopcart,discountSet);
		return finalPrice;
	}
	
	private double calculateDiscount(HashMap<String, Integer> shopCart, int maxDiscountSetToApply) {
			
			double finalPrice = 0.0;					
			
			//Mentre quedin llibres al ShopCart, hem de mirar el descompte que podem oferir (a partir del Set m‡xim de llibres diferents per SËrie)
			while (shopCart.isEmpty() == false){			
				
				int differentBooks = 0;			
				double originalPrice = maxDiscountSetToApply*PRICE;
				double partialPrice = 0.0;
				int elements = maxDiscountSetToApply;
				
				//Agafem el llibres diferents del ShopCart			
				for(Iterator<String> bookSeries = shopCart.keySet().iterator();elements>0;){
					String bookSerie = bookSeries.next();
					int quantityBooksOfThisSerie = shopCart.get(bookSerie);
					//Marquem que es un llibre diferent de la serie 
					differentBooks = differentBooks+1;
					//Restem aquest llibre de la sËrie 
					quantityBooksOfThisSerie = quantityBooksOfThisSerie - 1;
					//Mirem si el l'˙ltim llibre de la sËrie
					if (quantityBooksOfThisSerie == 0){
						//El.liminen aquesta serie del ShopCart
						bookSeries.remove();	
					}else{
						//Refresquem el quantitat amb un llibre menys perquË l'hem tret del Shopcart.
						shopCart.put(bookSerie, quantityBooksOfThisSerie);
					}	
					elements = elements - 1;	
				}	
			
				partialPrice = originalPrice - (originalPrice * discountToApply(differentBooks));					
				finalPrice = finalPrice +  partialPrice;
				
				//Marquem el Set de descompte m‡xim que es podria fer per llibres diferents de la SËrie (5, 4, 3? etc...)
				//Si la mida del shopCart es igual o mÈs gran es mantÈ el maxDiscountSet per mirar de treure lo m‡xim possible.
				int shopCartSize = shopCart.keySet().size();
				if (maxDiscountSetToApply > shopCartSize){ 
					maxDiscountSetToApply = shopCartSize;	
				}
				
			}			
			return finalPrice;
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
	
	private double getBestPriceFrom(BookShopCart shopCart, ArrayList<Object> prices) {
		
		double bestDiscount = shopCart.getQuantityOfBooks()*PRICE;		
		
		for(Object price:prices){
			double currentDiscountAvailable = ((Double)price).doubleValue();
			if (bestDiscount>currentDiscountAvailable){
				//tenim un descompte millor que podem aplicar. 
				bestDiscount = currentDiscountAvailable;
			}
		}
		
		return bestDiscount;
	}

}
