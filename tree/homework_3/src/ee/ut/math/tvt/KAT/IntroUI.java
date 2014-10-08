package ee.ut.math.tvt.KAT;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class IntroUI {
	
	
	public static void main(String s[]) {
		JFrame frame = new JFrame("IntroUI");
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel nimi = new JLabel("KAT");
		JLabel liider = new JLabel("puudub");
		JLabel email = new JLabel("Thomastoodo@msn.com");
		
		
		JPanel listPane = new JPanel();
		listPane.setLayout(new BoxLayout(listPane, BoxLayout.PAGE_AXIS));
		
		
		listPane.add(nimi);
		listPane.add(liider);
		listPane.add(email);
		
		listPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		Container container = new JPanel();
		container.add(listPane, BorderLayout.CENTER);
		
	
		

		frame.setVisible(true);
	}
	
	
	
	
}
