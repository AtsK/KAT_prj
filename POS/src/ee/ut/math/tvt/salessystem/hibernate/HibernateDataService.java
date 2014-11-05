package ee.ut.math.tvt.salessystem.hibernate;

import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;


@SuppressWarnings("unchecked")
public class HibernateDataService {

	private Session session = HibernateUtil.currentSession();
	
	public List<StockItem> getStockItems() {
		List<StockItem> result = session.createQuery("from StockItem").list();
		return result;
	}
//	public List<Student> getStudents() {
//		List<Student> result = session.createQuery("from Student").list();
//		return result;
//	}
//
//	public List<Lecturer> getLecturers() {
//		return Collections.checkedList(session.createQuery("from Lecturer").list(), Lecturer.class);
//	}
//
//	public List<Course> getCourses() {
//		List<Course> result = session.createQuery("from Course").list();
//		return result;
//	}
//
//	public List<Speciality> getSpecialities() {
//		List<Speciality> result = session.createQuery("from Speciality").list();
//		return result;
//	}

}