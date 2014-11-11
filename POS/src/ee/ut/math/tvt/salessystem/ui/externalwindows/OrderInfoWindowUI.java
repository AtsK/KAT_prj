package ee.ut.math.tvt.salessystem.ui.externalwindows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class OrderInfoWindowUI extends JFrame {
	private static final Logger log = Logger.getLogger(OrderInfoWindowUI.class);

	private static final long serialVersionUID = 1L;

	private Order order;

	public OrderInfoWindowUI(Order order, final JTable orderTable) {
		this.order = order;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String title = "Order " + dateFormat.format(order.getDate());
		
		setTitle(title);
		int height = 200;
		int width = 500;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {

		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				orderTable.clearSelection();
			}
		});

		drawThings();

	}

	private void drawThings() {
		PurchaseInfoTableModel infoTable = new PurchaseInfoTableModel();
		infoTable.populateWithData(order.getItems());
		JTable table = new JTable(infoTable);

		setLayout(new GridBagLayout());

		JScrollPane scrollPane = new JScrollPane(table);

		add(scrollPane, getBacketScrollPaneConstraints());

	}

	private GridBagConstraints getBacketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		return gc;
	}

}
