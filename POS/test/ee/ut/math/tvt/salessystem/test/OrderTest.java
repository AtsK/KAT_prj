package ee.ut.math.tvt.salessystem.test;


import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class OrderTest {
	
	private Order order1;
	private Order order2;
	private Order order3;
	private Order order4;
	private SoldItem item1;
	private SoldItem item2;
	private List <SoldItem> items;
	private List <SoldItem> noitems;
	private List <SoldItem> oneitem;
	private List <SoldItem> items2;
		
	
	
	
	@Before
	public void setUp() throws ParseException{
		item1 = new SoldItem(new StockItem(30l, "TestSoldItem1", 
				"This is the description of the test solditem.", 10.0, 100), 10);
		item2 = new SoldItem(new StockItem(31l, "TestSoldItem2", 
				"This is the description of the test solditem.", 15.0, 100), 2);
		
		
		
		items = new ArrayList<SoldItem>();
		oneitem = new ArrayList<SoldItem>();
		items2 = new ArrayList<SoldItem>();
		noitems = new ArrayList<SoldItem>();
		
		items.add(item1);
		items.add(item2);
		oneitem.add(item1);
		items2.add(item1);
		
		
		String date = "2014-10-10 12:12:12";
		Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
		
		
		order1 = new Order(date1, noitems);
		order2 = new Order(date1, oneitem);
		order3 = new Order(date1, items);
		order4 = new Order(date1, items2);
		
	}
		
	
	@Test
	public void testAddSoldItem(){
		List<SoldItem> solditems = new ArrayList<SoldItem>();
		solditems = order4.getItems();
		
		int i = 0;
				
		for (SoldItem x : solditems){
			if (x.equals(item1)){
				i = 1;
			}
			
		}
		assertEquals(1, i);
	}
		
	@Test
	public void testGetSumWithNoItems(){
		double price = order1.getPrice();
		
		assertEquals(0.0, price, 0.00001);
	}
	
	@Test
	public void testGetSumWithOneItem(){
		double price1 = order2.getPrice();
		
		assertEquals(100.0, price1, 0.00001);
	}
	
	@Test
	public void testGetSumWithMultipleItems(){
		double price2 = order3.getPrice();
		
		assertEquals(130.0, price2, 0.00001);
	}
	
	
	
	
	
}
