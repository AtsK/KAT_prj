package ee.ut.math.tvt.KAT;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class IntroUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LogManager.getLogger(IntroUI.class
			.getName());

	public IntroUI() {
		setTitle("Introduction");

		// size & location
		int width = 600;
		int height = 400;
		setSize(width, height);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);
		
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {
			logger.warn(e1.getMessage());
		}
		

		drawWidgets();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	private void drawWidgets() {
		JLabel name = new JLabel("KAT");
		JLabel leader = new JLabel("puudub");
		JLabel email = new JLabel("Thomastoodo@msn.com");

		getContentPane().add(name);
		getContentPane().add(leader);
		getContentPane().add(email);
	}

	/*
	 * public static void main(String s[]) { JFrame frame = new
	 * JFrame("IntroUI"); frame.setSize(500, 500);
	 * //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); JLabel nimi = new
	 * JLabel("KAT"); JLabel liider = new JLabel("puudub"); JLabel email = new
	 * JLabel("Thomastoodo@msn.com");
	 * 
	 * 
	 * JPanel listPane = new JPanel(); listPane.setLayout(new
	 * BoxLayout(listPane, BoxLayout.PAGE_AXIS));
	 * 
	 * 
	 * listPane.add(nimi); listPane.add(liider); listPane.add(email);
	 * 
	 * listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
	 * Container container = new JPanel(); container.add(listPane,
	 * BorderLayout.CENTER);
	 * 
	 * 
	 * 
	 * 
	 * frame.setVisible(true); }
	 */

}
