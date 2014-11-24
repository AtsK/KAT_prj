package ee.ut.math.tvt.salessystem.test;

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
		// Something more here to test if the objects actually have same
		// attributes.
	}
}
