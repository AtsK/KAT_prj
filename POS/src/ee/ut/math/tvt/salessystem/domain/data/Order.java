package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;


@Entity
@Table(name = "Order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "price")
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
