package ee.ut.math.tvt.salessystem.paymentwindow;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

import ee.ut.math.tvt.KAT.Intro;
import ee.ut.math.tvt.KAT.IntroUI;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;



public class confirmationWindowUI extends JFrame{
	
	private JTextField sumField;
	private JTextField paymentField;
	private JTextField changeAmountField;
	private JButton confirmationButton;
	private JButton cancelButton;
	
	
	private static final long serialVersionUID = 1L;
	

	public confirmationWindowUI(){
		
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

		drawThings();

		
		
	}
	private void drawThings(){
		
		sumField = new JTextField();
        paymentField = new JTextField();
        changeAmountField = new JTextField();
        
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
		confirmationButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
                //siia meetod, mis lisab tellimuse history tabi
            }
        });
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				dispose();
			}
		});
		
		add(confirmationButton);
		add(cancelButton);
		
		
		
	}
}