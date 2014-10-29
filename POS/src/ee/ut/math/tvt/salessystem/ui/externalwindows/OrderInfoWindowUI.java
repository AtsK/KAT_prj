package ee.ut.math.tvt.salessystem.ui.externalwindows;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class OrderInfoWindowUI extends JFrame {
	private static final Logger log = Logger
			.getLogger(OrderInfoWindowUI.class);

	private static final long serialVersionUID = 1L;

	private PurchaseInfoTableModel infoTable;

	public OrderInfoWindowUI(PurchaseInfoTableModel infoTable) {
		this.infoTable = infoTable;

		setTitle("Order details");
		int height = 200;
		int width = 500;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {

		}

		drawThings();

	}

	private void drawThings() {
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
