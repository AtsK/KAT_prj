package ee.ut.math.tvt.salessystem.ui.externalwindows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.exception.DuplicateNameException;
import ee.ut.math.tvt.salessystem.domain.exception.StockAvailabilityException;
import ee.ut.math.tvt.salessystem.hibernate.HibernateDataService;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

public class StockItemAdditionWindowUI extends JFrame {
	private static final Logger log = Logger
			.getLogger(ConfirmationWindowUI.class);

	private SalesSystemModel model;

	private JTextField idField;
	private JTextField nameField;
	private JTextField priceField;
	private JTextField descriptionField;
	private JTextField quantityField;
	private JButton confirmationButton;
	private JButton cancelButton;

	private static final long serialVersionUID = 1L;

	public StockItemAdditionWindowUI(SalesSystemModel model) {
		this.model = model;

		setTitle("Add item to stock");
		int height = 200;
		int width = 300;
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

		long maxIdNum = 0;
		for (StockItem item : model.getWarehouseTableModel().getTableRows()) {
			if (item.getStockItemId() > maxIdNum) {
				maxIdNum = item.getStockItemId();
			}
		}
		idField = new JTextField(String.valueOf(maxIdNum + 1));
		nameField = new JTextField("Name");
		priceField = new JTextField("0");
		descriptionField = new JTextField("Description of good");
		quantityField = new JTextField("1");

		idField.setEnabled(false);
		
		JLabel id = new JLabel("Id: ");
		JLabel name = new JLabel("Name: ");
		JLabel price = new JLabel("Price: ");
		JLabel description = new JLabel("Description: ");
		JLabel quantity = new JLabel("Quantity: ");

		setLayout(new GridLayout(6, 2));

		add(id);
		add(idField);

		add(name);
		add(nameField);

		add(price);
		add(priceField);

		add(description);
		add(descriptionField);

		add(quantity);
		add(quantityField);

		confirmationButton = new JButton("Confirm");
		confirmationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StockItem addedItem = new StockItem(Long.parseLong(idField
						.getText()), nameField.getText(), descriptionField
						.getText(), Double.parseDouble(priceField.getText()),
						Integer.parseInt(quantityField.getText()));
				try {
					model.getWarehouseTableModel().addItem(addedItem);
					HibernateDataService service = new HibernateDataService();
					service.updateStockItem(addedItem);
				} catch (StockAvailabilityException e1) {
					log.error(e1.getMessage());
				} catch (DuplicateNameException e1) {
					JOptionPane.showMessageDialog(null,
						e1.getMessage(),
						"Error", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
			}
		});
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(confirmationButton);
		add(cancelButton);

	}
}
