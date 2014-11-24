package ee.ut.math.tvt.salessystem.domain.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;


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
	
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "ORDERS_TO_SOLDITEMS",
        joinColumns = @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    )
	private List<SoldItem> items;
	
	public Order(Date date, Double price, List<SoldItem> items) {
		this.date = date;
		this.price = price;
		this.items = items;
	}
	
	public Order() {}

	public Date getDate() {
		return date;
	}

	public Double getPrice() {
		return price;
	}
	
	public List<SoldItem> getItems() {
		return items;
	}
	
}
