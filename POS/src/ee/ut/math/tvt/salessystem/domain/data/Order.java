package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Date;

import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class Order {
	
	private Date date;
	private Double price;
	private PurchaseInfoTableModel infoTable;
	
	public Order(Date date, Double price, PurchaseInfoTableModel infoTable) {
		this.date = date;
		this.price = price;
		this.infoTable = infoTable;
	}

	public Date getDate() {
		return date;
	}

	public Double getPrice() {
		return price;
	}
	
	public PurchaseInfoTableModel getInfoTable() {
		return infoTable;
	}
	
	
}
