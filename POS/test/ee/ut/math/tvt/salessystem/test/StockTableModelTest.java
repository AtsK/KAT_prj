package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.DuplicateNameException;
import ee.ut.math.tvt.salessystem.domain.exception.StockAvailabilityException;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

public class StockTableModelTest {
	StockTableModel stockTableModel;
	StockItem item;

	@Before
	public void setUp() {
		stockTableModel = new StockTableModel();
		item = new StockItem(1l, "Item", "description", 10.0, 10);
		try {
			System.out.println(stockTableModel.getRowCount());
			stockTableModel.addItem(item);
		} catch (StockAvailabilityException | DuplicateNameException e) {
			System.out.println(e.getMessage());
		}
	}

	@Test
	public void testValidateNameUniqueness() {
		StockItem item2 = new StockItem(2l, "Item", "description2", 10.0, 10);
		try {
			stockTableModel.addItem(item2);
		} catch (StockAvailabilityException | DuplicateNameException e) {}
		
		assertTrue(stockTableModel.getRowCount() == 1);
	}
	
	@Test(expected=StockAvailabilityException.class)
	public void testHasEnoughInStock() throws StockAvailabilityException {
			stockTableModel.getItemByName("Item").setQuantity(-1);
	}
	
	@Test
	public void testGetItemByIdWhenItemExists() {
		StockItem receivedItem = stockTableModel.getItemById(1l);
		assertTrue(receivedItem.equals(item));
	}
	
	@Test
	public void testGetItemByIdWhenThrowsException() {
		try {
			StockItem item1 = stockTableModel.getItemById(5l);
			assertTrue(item1 == null);
		} catch (NoSuchElementException e) {}
	}
}
