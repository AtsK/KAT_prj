package ee.ut.math.tvt.salessystem.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;


public class StockTableModelTest {
	StockTableModel stockTableModel;
	@Before
	  public void setUp() {
		stockTableModel = new StockTableModel();
	  }

	  @Test
	  public void testValidateNameUniqueness() {
	    
	  }
}
