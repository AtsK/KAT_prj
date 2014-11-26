package ee.ut.math.tvt.salessystem.ui.externalwindows;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.salessystem.domain.data.Order;
import ee.ut.math.tvt.salessystem.hibernate.HibernateDataService;
import ee.ut.math.tvt.salessystem.ui.Utilities;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

public class ConfirmationWindowUI extends JFrame {

	private static final Logger log = Logger
			.getLogger(ConfirmationWindowUI.class);
	private double sum;

	private JTextField sumField;
	private JTextField paymentField;
	private JTextField changeAmountField;
	private JButton confirmationButton;
	private JButton cancelButton;
	private JFrame mainFrame;
	private PurchaseTab purchaseTab;

	private SalesSystemModel model;
	
	private static final long serialVersionUID = 1L;

	public ConfirmationWindowUI(SalesSystemModel model, JFrame mainFrame, PurchaseTab purchaseTab) {
		this.model = model;
		this.sum = model.getCurrentPurchaseTableModel().getTotalSum();
		this.mainFrame = mainFrame;
		this.purchaseTab = purchaseTab;
		
		mainFrame.setEnabled(false);
		setTitle("Payment");
		int height = 200;
		int width = 300;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {

		}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener( new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
            	close();
            }
        } );

		drawThings();

	}

	private void drawThings() {

		sumField = new JTextField(String.valueOf(sum));
		paymentField = new JTextField();
		paymentField.setText(String.valueOf(sum));
		changeAmountField = new JTextField();
		changeAmountField.setText("0.0");

		paymentField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				removeUpdate(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (Utilities.isFloat(paymentField.getText())) {
					confirmationButton.setEnabled(true);
					changeAmountField.setText(String.valueOf(Utilities.round(
							Double.parseDouble(paymentField.getText()) - sum, 3)));
					if (Double.parseDouble(changeAmountField.getText()) < 0) {
						confirmationButton.setEnabled(false);
					}
				} else {
					confirmationButton.setEnabled(false);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		sumField.setEditable(false);
		paymentField.setEditable(true);
		changeAmountField.setEditable(false);

		JLabel sum = new JLabel("Sum: ");
		JLabel payment = new JLabel("Payment size: ");
		JLabel change = new JLabel("Change amount: ");

		setLayout(new GridLayout(4, 2));
		add(sum);
		add(sumField);

		add(payment);
		add(paymentField);

		add(change);
		add(changeAmountField);
		

		confirmationButton = new JButton("Confirm");
		confirmationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order newOrder = createOrder();
				model.getHistoryTableModel().addOrder(newOrder);
				HibernateDataService service = new HibernateDataService();
				service.addOrder(newOrder);
				purchaseTab.endSale();
				close();
			}
		});
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});

		add(confirmationButton);
		add(cancelButton);
	}
	
	private Order createOrder() {
		
		Order order = new Order(new Date(), model.getCurrentPurchaseTableModel().getTableRows());
		return order;
	}
	
	private void close() {
		mainFrame.setEnabled(true);
		dispose();
	}
	
}