package ee.ut.math.tvt.salessystem.ui.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ee.ut.math.tvt.salessystem.domain.data.DisplayableItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;


public class HistoryTableModel extends SalesSystemTableModel {
	
	private static final long serialVersionUID = 1L;
	
	
	public HistoryTableModel() {
		super(new String [] {"Date of the order", "Time of the order", "Total order price"});
		
	}

	
	

	public String toString() {
		
		DateFormat dateFormatDate = new SimpleDateFormat("yyyy/MM/dd");
		Date DateOfTheOrder = new Date();
	
	
		DateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");
		Date TimeOfTheOrder = new Date();
		
		
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (int i = 0; i < 3; i++) {
			buffer.append(dateFormatDate.format(DateOfTheOrder) + "\t");
			buffer.append(dateFormatTime.format(TimeOfTheOrder) + "\t");
			buffer.append("price" + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
	
	

	protected Object getColumnValue(DisplayableItem item, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

	
