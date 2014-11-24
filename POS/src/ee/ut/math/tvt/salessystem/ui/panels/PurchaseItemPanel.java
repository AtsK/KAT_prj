package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.hibernate.HibernateDataService;
import ee.ut.math.tvt.salessystem.ui.Utilities;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Purchase pane + shopping cart table UI.
 */
public class PurchaseItemPanel extends JPanel {
	private static final Logger log = Logger.getLogger(PurchaseItemPanel.class);

	private static final long serialVersionUID = 1L;

	// Text field on the dialogPane
	private JTextField barCodeField;
	private JTextField quantityField;
	private JTextField nameField;
	private JTextField priceField;
	private JComboBox<String> dropdown;
	private DefaultComboBoxModel<String> dropdownModel;

	private JButton addItemButton;

	// Warehouse model
	private SalesSystemModel model;

	/**
	 * Constructs new purchase item panel.
	 * 
	 * @param model
	 *            composite model of the warehouse and the shopping cart.
	 */
	public PurchaseItemPanel(SalesSystemModel model) {
		this.model = model;

		setLayout(new GridBagLayout());

		add(drawDialogPane(), getDialogPaneConstraints());
		add(drawBasketPane(), getBasketPaneConstraints());

		setEnabled(false);
	}

	// shopping cart pane
	private JComponent drawBasketPane() {

		// Create the basketPane
		JPanel basketPane = new JPanel();
		basketPane.setLayout(new GridBagLayout());
		basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

		// Create the table, put it inside a scollPane,
		// and add the scrollPane to the basketPanel.
		JTable table = new JTable(model.getCurrentPurchaseTableModel());
		JScrollPane scrollPane = new JScrollPane(table);

		basketPane.add(scrollPane, getBacketScrollPaneConstraints());

		return basketPane;
	}

	// purchase dialog
	private JComponent drawDialogPane() {

		// Create the panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));
		panel.setBorder(BorderFactory.createTitledBorder("Product"));

		// Initialize the textfields
		initStockItems();

		barCodeField = new JTextField();
		quantityField = new JTextField("1");
		nameField = new JTextField();
		priceField = new JTextField();

		dropdown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fillDialogFields();
			}
		});
		dropdown.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
			public void popupMenuCanceled(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			}

			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				updateStockItems();
			}
		});
		barCodeField.setEditable(false);
		nameField.setEditable(false);
		priceField.setEditable(false);

		// == Add components to the panel

		// - bar code
		panel.add(new JLabel("Item:"));
		panel.add(dropdown);

		panel.add(new JLabel("Bar code:"));
		panel.add(barCodeField);

		// - amount
		panel.add(new JLabel("Amount:"));
		panel.add(quantityField);

		quantityField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				removeUpdate(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (Utilities.isNumeric(quantityField.getText())) {
					addItemButton.setEnabled(true);
				} else {
					addItemButton.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		// - name
		panel.add(new JLabel("Name:"));
		panel.add(nameField);

		// - price
		panel.add(new JLabel("Price:"));
		panel.add(priceField);

		// Create and add the button
		addItemButton = new JButton("Add to cart");
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addItemEventHandler();
			}

		});
		panel.add(addItemButton);

		return panel;
	}

	// Update stockitems list JComboBox list
	private void initStockItems() {
		List<StockItem> stockItems = model.getWarehouseTableModel()
				.getTableRows();
		dropdownModel = new DefaultComboBoxModel<String>(new String[] {});
		for (StockItem item : stockItems) {
			dropdownModel.addElement(item.getName());
		}
		dropdown = new JComboBox<String>(dropdownModel);
	}

	// Update stockitems list JComboBox list
	private void updateStockItems() {
		List<StockItem> stockItems = model.getWarehouseTableModel()
				.getTableRows();
		for (StockItem item : stockItems) {
			if (dropdownModel.getIndexOf(item.getName()) == -1) {
				dropdownModel.addElement(item.getName());
			}
		}
	}

	// Fill dialog with data from the "database".
	public void fillDialogFields() {
		StockItem stockItem = getStockItemByName();

		if (stockItem != null) {
			nameField.setText(stockItem.getName());
			String priceString = String.valueOf(stockItem.getPrice());
			priceField.setText(priceString);
			barCodeField.setText(stockItem.getStockItemId().toString());
		} else {
			log.error("Couldn't find the item.");
			reset();
		}
	}

	// Search the warehouse for a StockItem with the bar code entered
	// to the barCode textfield.
	private StockItem getStockItemByBarcode() {
		try {
			int code = Integer.parseInt(barCodeField.getText());
			return model.getWarehouseTableModel().getItemById(code);
		} catch (NumberFormatException ex) {
			return null;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	private StockItem getStockItemByName() {
		try {
			String name = dropdown.getSelectedItem().toString();
			return model.getWarehouseTableModel().getItemByName(name);
		} catch (NumberFormatException ex) {
			return null;
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	/**
	 * Add new item to the cart.
	 */
	public void addItemEventHandler() {
		// add chosen item to the shopping cart.
		StockItem stockItem = getStockItemByBarcode();
		if (stockItem != null) {
			int quantity;
			try {
				quantity = Integer.parseInt(quantityField.getText());
			} catch (NumberFormatException ex) {
				quantity = 1;
			}
			int availableAmount = model.getWarehouseTableModel()
					.getItemById(stockItem.getStockItemId()).getQuantity();
			if (availableAmount >= quantity) {
				model.getCurrentPurchaseTableModel().addItem(
						new SoldItem(stockItem, quantity));
				model.getWarehouseTableModel().setItemQuantity(stockItem,
						availableAmount - quantity);
				HibernateDataService service = new HibernateDataService();
				service.updateStockItem(stockItem);
			} else {
				JOptionPane.showMessageDialog(null,
						"Requested item amount exceeds availability!",
						"Availability exceeded", JOptionPane.ERROR_MESSAGE);
				;
				log.info("Item: "
						+ stockItem.getName()
						+ " stock amount is lower than requested purchase amount");
			}
		}
	}

	/**
	 * Sets whether or not this component is enabled.
	 */
	@Override
	public void setEnabled(boolean enabled) {
		this.dropdown.setEnabled(enabled);
		this.addItemButton.setEnabled(enabled);
		this.barCodeField.setEnabled(enabled);
		this.quantityField.setEnabled(enabled);
	}

	/**
	 * Reset dialog fields.
	 */
	public void reset() {
		barCodeField.setText("");
		quantityField.setText("1");
		nameField.setText("");
		priceField.setText("");
	}

	/*
	 * === Ideally, UI's layout and behavior should be kept as separated as
	 * possible. If you work on the behavior of the application, you don't want
	 * the layout details to get on your way all the time, and vice versa. This
	 * separation leads to cleaner, more readable and better maintainable code.
	 * 
	 * In a Swing application, the layout is also defined as Java code and this
	 * separation is more difficult to make. One thing that can still be done is
	 * moving the layout-defining code out into separate methods, leaving the
	 * more important methods unburdened of the messy layout code. This is done
	 * in the following methods.
	 */

	// Formatting constraints for the dialogPane
	private GridBagConstraints getDialogPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 0d;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.NONE;

		return gc;
	}

	// Formatting constraints for the basketPane
	private GridBagConstraints getBasketPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.anchor = GridBagConstraints.WEST;
		gc.weightx = 0.2;
		gc.weighty = 1.0;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.fill = GridBagConstraints.BOTH;

		return gc;
	}

	private GridBagConstraints getBacketScrollPaneConstraints() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1.0;
		gc.weighty = 1.0;

		return gc;
	}

}
