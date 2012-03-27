package org.labs.katas.Potter;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class KataPotterTest {
	
	private BookShopCart shopcart;
	private Cashier cashier;
	
	@Before
	public void setUp(){
		 shopcart = new BookShopCart();	
		 cashier = new Cashier();		 
	}
	
	@Test
	public void no_book_at_all(){		
		assertEquals(0.0,cashier.calculate(shopcart),0.0);
	}
	
	@Test
	public void one_book_to_buy(){
		shopcart.addBook("b1");
		assertEquals(8.0,cashier.calculate(shopcart),0.0);		
	}	
	
	@Test
	public void two_different_books_to_buy(){		
		
		shopcart.addBook("b1");
		shopcart.addBook("b2");
		
		assertEquals(15.2,cashier.calculate(shopcart),0.0);	
	}	
	
	@Test
	public void two_identical_books_to_buy(){
		
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		
		assertEquals(16.0,cashier.calculate(shopcart),0.0);	
	}	
	
	@Test
	public void three_different_books_to_buy(){
		
		shopcart.addBook("b1");
		shopcart.addBook("b2");
		shopcart.addBook("b3");
		
		assertEquals(21.6,cashier.calculate(shopcart),0.0);	
	}	
		
	@Test
	public void three_identical_books_to_buy(){		
		
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		
		assertEquals(24.0,cashier.calculate(shopcart),0.0);
	}	
	
	@Test
	public void four_different_books_to_buy(){
	
		shopcart.addBook("b1");
		shopcart.addBook("b2");
		shopcart.addBook("b3");
		shopcart.addBook("b4");
		
		assertEquals(25.6,cashier.calculate(shopcart),0.0);
		
	}
	
	@Test
	public void four_identical_books_to_buy(){
		
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		
		assertEquals(32.0,cashier.calculate(shopcart),0.0);
		
	}
	
	@Test
	public void five_different_books_to_buy(){

		shopcart.addBook("b1");
		shopcart.addBook("b2");
		shopcart.addBook("b3");
		shopcart.addBook("b4");
		shopcart.addBook("b5");
		
		assertEquals(30.0,cashier.calculate(shopcart),0.0);
		
	}
	
	@Test
	public void five_identical_books_to_buy(){

		shopcart.addBook("b1");
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		shopcart.addBook("b1");
		
		assertEquals(40.0,cashier.calculate(shopcart),0.0);
		
	}
	
	@Test
	public void three_books_to_buy_but_two_different_series(){
	
		shopcart.addBook("b1");
		shopcart.addBook("b2");
		shopcart.addBook("b1");
		
		assertEquals(23.2,cashier.calculate(shopcart),0.0);
		
	}
	
	@Test
	public void four_books_to_buy_but_two_different_series(){
		
		shopcart.addBook("b1");
		shopcart.addBook("b2");
		shopcart.addBook("b1");
		shopcart.addBook("b2");
		
		assertEquals(30.4,cashier.calculate(shopcart),0.0);
	}
	
	@Test
	public void various_books_with_different_discount_sets(){

		shopcart.addBook("b1");		
		shopcart.addBook("b1");
		shopcart.addBook("b2");
		shopcart.addBook("b2");
		shopcart.addBook("b3");
		shopcart.addBook("b3");
		shopcart.addBook("b4");
		shopcart.addBook("b5");
		
		assertEquals(51.20,cashier.calculate(shopcart),0.0);
	}
		
}
