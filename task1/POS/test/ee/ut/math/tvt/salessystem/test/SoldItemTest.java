package ee.ut.math.tvt.salessystem.test;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	private SoldItem item1;
	private SoldItem item2;
	
	@Before
	public void setUp(){
		item1 = new SoldItem(new StockItem(30l, "TestSoldItem1", 
				"This is the description of the test solditem.", 10.0, 100), 10);
		item2 = new SoldItem(new StockItem(31l, "TestSoldItem2", 
				"This is the description of the test solditem.", 10.0, 100), 0);
	}
	
	
	
	@Test
	public void testGetSum(){
		double a = item1.getSum();
		
		assertEquals(100.0, a, 0.00001);
	}
	
	@Test
	public void testGetSumWithZeroQuantity(){
		double b = item2.getSum();
		
		assertEquals(0.0, b, 0.00001);
	}

}
	
