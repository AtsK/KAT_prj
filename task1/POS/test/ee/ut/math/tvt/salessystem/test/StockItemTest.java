package ee.ut.math.tvt.salessystem.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {

	private StockItem item1;
	private StockItem item2;

	@Before
	public void setUp() {
		item1 = new StockItem(29l, "TestStockitem",
				"This is the description of the test stockitem.", 10.0, 100);
	}

	@Test
	public void testClone() {
		item2 = (StockItem) item1.clone();
		Assert.assertNotSame(item2, item1);
		Assert.assertEquals(item1, item2);
	}
	
	@Test
	public void testColumn() {
		Assert.assertEquals(item1.getColumn(0), item1.getStockItemId());
		Assert.assertEquals(item1.getColumn(1), item1.getName());
		Assert.assertEquals(item1.getColumn(2), item1.getPrice());
		Assert.assertEquals(item1.getColumn(3), item1.getQuantity());
	}
}
