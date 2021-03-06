package ee.ut.math.tvt.salessystem.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;


@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();
	
	public List<StockItem> getStockItems() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}
	// Not used, as updateStockItem already adds if no ID exists
	public void addStockItem(StockItem item) {
		Transaction tx = session.beginTransaction();
		session.save(item);
		tx.commit();
	}
	public void updateStockItem(StockItem item) {
		Transaction tx = session.beginTransaction();
		session.merge(item);
		tx.commit();
	}
	public void addOrder(Order order) {
		Transaction tx = session.beginTransaction();
		session.save(order);
		tx.commit();
	}
	
	public List<Order> getOrders() {
		List<Order> result = session.createQuery("from Order").list();
		return result;
	}
	
	public void syncStockDatabase(StockTableModel stockTableModel) {
		List<StockItem> Items = stockTableModel.getTableRows();
		for(StockItem item : Items) {
			updateStockItem(item);
		}
	}
}