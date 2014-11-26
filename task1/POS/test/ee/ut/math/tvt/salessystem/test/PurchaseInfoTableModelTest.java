package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {

	private PurchaseInfoTableModel purchaseInfoTableModel;
	private SoldItem item1;
	private SoldItem item2;
	
	@Before
	public void setUp(){
		item1 = new SoldItem(new StockItem(30l, "TestSoldItem1", 
				"This is the description of the test solditem.", 10.0, 100), 10);
		item2 = new SoldItem(new StockItem(31l, "TestSoldItem2", 
				"This is the description of the test solditem.", 10.0, 100), 5);
		
		purchaseInfoTableModel = new PurchaseInfoTableModel();
		
		purchaseInfoTableModel.addItem(item1);
		purchaseInfoTableModel.addItem(item2);
	}
	
	@Test
	public void testGetTotalSum(){
		double a = purchaseInfoTableModel.getTotalSum();
		
		assertEquals(150.0, a, 0.00001);
	}
	
	@Test
	public void testGetSoldItemByName(){
		SoldItem b = purchaseInfoTableModel.getItemByName("TestSoldItem1");
		
		assertEquals(item1, b);
	}
}
