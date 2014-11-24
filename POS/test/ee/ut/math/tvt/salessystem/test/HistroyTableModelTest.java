package ee.ut.math.tvt.salessystem.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;

public class HistroyTableModelTest {
	private HistoryTableModel model1;
	private HistoryTableModel model2;
	private Order order1;
	private Order order2;
	private SoldItem soldItem1;
	private SoldItem soldItem2;
	private List<SoldItem> items1;
	private List<SoldItem> items2;
	private List<Order> orders1;

	@Before
	public void setUp() throws ParseException {
		soldItem1 = new SoldItem(new StockItem(30l, "TestSoldItem1",
				"This is the description of the test solditem.", 10.0, 100), 10);
		soldItem2 = new SoldItem(new StockItem(31l, "TestSoldItem2",
				"This is the description of the test solditem.", 15.0, 100), 2);
		model1 = new HistoryTableModel();
		model2 = new HistoryTableModel();

		items1 = new ArrayList<SoldItem>();
		items2 = new ArrayList<SoldItem>();

		items1.add(soldItem1);
		items1.add(soldItem2);

		String date = "2014-10-10 12:12:12";
		Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);

		order1 = new Order(date1, items1);
		order2 = new Order(date1, items2);

		model1.addOrder(order1);
		model1.addOrder(order2);
		
		orders1 = new ArrayList<Order>();
		orders1.add(order1);
		orders1.add(order2);
		
		model2.populateWithData(orders1);
	}

	@Test
	public void testgetColumnValue() {
		DateFormat dateFormatDate = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");
		
		Assert.assertEquals(dateFormatDate.format(order1.getDate()), model1.getValueAt(0, 0));
		Assert.assertEquals(dateFormatTime.format(order1.getDate()), model1.getValueAt(0, 1));
		Assert.assertEquals(order1.getPrice(), model1.getValueAt(0, 2));

		Assert.assertEquals(dateFormatDate.format(order2.getDate()), model1.getValueAt(1, 0));
		Assert.assertEquals(dateFormatTime.format(order2.getDate()), model1.getValueAt(1, 1));
		Assert.assertEquals(order2.getPrice(), model1.getValueAt(1, 2));

	}
	
	@Test
	public void testPopulateWithData() {
		Assert.assertEquals(model1.getRowCount(), model2.getRowCount());
		Assert.assertEquals(model1.getColumnCount(), model2.getColumnCount());
		for (int r=0; r < model1.getRowCount(); r++) {
			for (int c=0; c < model1.getColumnCount(); c++) {
				Assert.assertEquals(model1.getValueAt(r, c), model2.getValueAt(r, c));
			}
		}
	}

}
