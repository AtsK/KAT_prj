package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Date;

public class Order {
	
	private Date date;
	private Double price;
	
	public Order(Date date, Double price) {
		this.date = date;
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public Double getPrice() {
		return price;
	}
	
	
}
