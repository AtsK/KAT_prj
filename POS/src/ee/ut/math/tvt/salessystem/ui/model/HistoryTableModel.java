package ee.ut.math.tvt.salessystem.ui.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ee.ut.math.tvt.salessystem.domain.data.Order;

public class HistoryTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Order> rows;
	private final String[] headers;

	public HistoryTableModel() {
		headers = new String[] { "Date of the order", "Time of the order",
				"Total order price" };
		rows = new ArrayList<>();
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

	public void addOrder(Order order) {
		rows.add(order);
		fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return getColumnValue(rows.get(rowIndex), columnIndex);
	}
	
	private Object getColumnValue(Order order, int columnIndex) {
		
		DateFormat dateFormatDate = new SimpleDateFormat("yyyy/MM/dd");
		DateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");
		
		switch (columnIndex) {
		case 0:
			return dateFormatDate.format(order.getDate());
		case 1:
			return dateFormatTime.format(order.getDate());
		case 2:
			return order.getPrice();
		
		}
		throw new IllegalArgumentException("Column index out of range");
	}
	
	@Override
    public String getColumnName(final int columnIndex) {
        return headers[columnIndex];
    }

}
