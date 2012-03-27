package org.labs.katas.Potter;
import java.util.HashMap;


public class BookShopCart{
	
	private HashMap<String, Integer> shopCart = new HashMap<String, Integer>();
	
	private int totalBooksToBuy = 0;
	
	public void addBook(String book) {
		addToShopCart(book,getQuantityOfIdentical(book)+1);
		
	}

	private void addToShopCart(String book, int quantity) {
		shopCart.put(book, quantity);
		this.totalBooksToBuy += 1;
	}

	private int getQuantityOfIdentical(String book) {
		int quantity = 0;
		if (shopCartHasAlreadyThisSerieOf(book)){
			return ((Integer) shopCart.get(book)).intValue();
		}
		return quantity;
		
	}

	private boolean shopCartHasAlreadyThisSerieOf(String book) {
		return shopCart.containsKey(book);
	}

	public boolean hasBooks() {
		return this.totalBooksToBuy > 0;
	}
	
	public int getQuantityOfBooks(){
		return this.totalBooksToBuy;
	}
	
	public HashMap<String, Integer> getBooksShopCart(){
		return this.shopCart;
	}
	
	public int getDifferentBookSeries(){
		return this.shopCart.size();
	}	

}
